[![Build Status](https://travis-ci.com/erikc5000/island-time.svg?branch=master)](https://travis-ci.com/erikc5000/island-time) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

# Island Time
A Kotlin Multiplatform library for working with dates and times, heavily inspired by the java.time library.

# Setup
Island Time is still early in development and the API is likely to change significantly. Snapshot builds are available on the Sonatype OSS Snapshot Repository.

Repository configuration: _(Kotlin Gradle DSL)_
```kotlin
repositories {
    maven {
        url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}
```

This project publishes Gradle metadata, so you can use the common artifact and it will automatically sort out the correct dependencies for each platform.

Common: _(Kotlin Gradle DSL)_
```
dependencies {
    implementation("io.islandtime:core:0.1.0-SNAPSHOT")
}
```

On Android specifically, you'll need to add the following:

Android: _(Kotlin Gradle DSL)_
```
dependencies {
    // Until java.time library desugaring is added to D8, Android relies on
    // ThreeTenABP (https://github.com/JakeWharton/ThreeTenABP) to supply the
    // time zone database
    implementation("io.islandtime:threetenabp-extensions:0.1.0-SNAPSHOT")
}
```

_**Important:**_ Due to the experimental status of inline classes, which are used in the public API, the version of Kotlin that you use in your project must match the version used by Island Time -- even for non-native targets.

Island Time 0.1.0-SNAPSHOT builds are based on Kotlin 1.3.60.
Also note that Island Time requires a JVM target of 1.8 or above.

Current supported platforms are JVM, Android, iOS ARM64/x64, watchOS ARM64/X86, tvOS ARM64/X64, and macOS x64.

# Usage

## Initialization

Prior to using Island Time, it may be initialized with a custom `TimeZoneRulesProvider`. The platform default provider will be used if this isn't specified explicitly.

On Android, using the default provider will trigger an exception at runtime when targeting an API version below 26 since it uses the java.time library internally. The `AndroidThreeTenProvider` in threetenabp-extensions can be used instead. Generally, initialization should be performed during `Application.onCreate()`.

```kotlin
// Note that it isn't necessary to call AndroidThreeTen.init() separately
IslandTime.initializeWith(AndroidThreeTenProvider(context))
```

For further information, see https://github.com/JakeWharton/ThreeTenABP. Once java.time desugaring is available, this step should no longer be required.

## The Basics

As Island Time draws heavily from the java.time library design, many of the classes should be familiar to anyone migrating over. The following table shows the relationship between a subset of the classes:

| java.time | Island Time | Description |
| --- | --- | --- |
| `LocalDate` | `Date` | A date in arbitrary region |
| `LocalTime` | `Time` | A time of day in arbitrary region |
| `LocalDateTime` | `DateTime` | A combined date and time of day in arbitrary region |
| `Instant` | `Instant` | An instant in time, represented by the number of seconds/nanoseconds relative to the Unix epoch (1970-01-01T00:00Z) |
| `OffsetTime` | `OffsetTime` | A time of day with UTC offset |
| `OffsetDateTime` | `OffsetDateTime` | A date and time of day with fixed UTC offset |
| `ZonedDateTime` | `ZonedDateTime` | A date and time of day in a particular time zone region |
| `ZoneOffset` | `UtcOffset` | An offset from UTC |
| `ZoneRegion` | `TimeZone` | An IANA time zone database region ID |
| `Duration` | `Duration` | A (potentially large) duration of time |
| `Period` | `Period` | A date-based period of time |

## Examples

These may get outdated as the library continues to get flushed out, but should give an idea as to how the library is intended to be used so as to help foster discussion on the design.

### Durations

```kotlin
// The minimum necessary unit granularity is preserved when combining different units
val totalSeconds: IntSeconds = 5.hours + 30.minutes + 1.seconds

// Math with Int values on sub-second units forcees a lengthing to Long due to overflow potential
val nanoseconds: LongNanoseconds = 5.seconds + 1.nanoseconds

// The generalized "Duration" class is suitable for dealing with potentially large durations (ie. >50 years)
// that might overflow an Int or Long representation
val duration: Duration = durationOf(5.hours + 30.minutes - 5.milliseconds)
val anotherDuration: Duration = 5.hours.asDuration() + 50.microseconds

// Durations (and each unit measure) can be broken down into individual unit components
duration.toComponents { hours, minutes, seconds, nanoseconds ->
    // ...
}
```

### Periods

```kotlin
val period = periodOf(5.years, 13.months, 10.days)
val normalizedPeriod = period.normalized() // periodOf(6.years, 1.months, 10.days)
val modifiedPeriod = period - 1.years - 15.days // periodOf(5.years, 1.months, (-5).days)
val invertedPeriod = -period // periodOf((-5).years, (-13).months, (-10).days)
```

### Date Manipulation

```kotlin
val today = Date.now()
val tomorrow = today + 1.days
val nextWednesday = today.next(DayOfWeek.WEDNESDAY)
val lastSundayOrToday = today.previousOrSame(DayOfWeek.SUNDAY)
val startOfMonth = today.startOfMonth
val endOfMonth = today.endOfMonth
```

### ISO-8601 Representation

```kotlin
val isoTimestamp = Instant.now().toString() // 2019-10-28T08:34:03.389Z
val isoDuration = durationOf(5.hours + 4.seconds).toString() // PT5H4S
```

### Parsing

```kotlin
// Parses ISO-8601 extended format strings by default
val offsetDateTime = "2001-08-09T12:45+04:00".toOffsetDateTime()

// Built-in parsers are also available for basic and basic/extended formats
val dateTime = "20000101 0909".toDateTime(DateTimeParsers.Iso.Basic.DATE_TIME)

// Custom parsers can also be defined, but must supply a combination of DateTimeFields that the type can interpret
val customParser = dateTimeParser {
    monthNumber(2)
    anyOf({ +'-' }, { +' ' })
    dayOfMonth(2)
    anyOf({ +'-' }, { +' ' })
    year(4)
}

val date = "10-01-2019".toDate(customParser)
```

### Date Ranges

```kotlin
val clock: Clock = SystemClock()
val today: Date = Date.now(clock)

// Step over each day in a range
for (date in today - 1.months..today) {
    val startOfDay: ZonedDateTime = date.startOfDayAt(clock.zone)
    val endOfDay: ZonedDateTime = date.endOfDayAt(clock.zone)
    // ...
}

// Step by months instead of days
for (date in today until today + 1.years step 1.months) {
   // ...
}

val randomDate = (today..today + 1.months).random()
val totalDays: IntDays = (today until today + 6.months).lengthInDays
val period: Period = (today..today + 1.months).asPeriod()
```

### Open Time Intervals

```kotlin
val instantInterval = "2008-09-01T04:00Z/..".toInstantInterval()

val isBounded = instantInterval.isBounded() // false
val hasUnboundedEnd = instantInterval.hasUnboundedEnd() // true
val duration = instantInterval.asDuration() // throws DateTimeException
```


### Daylight Savings Changes

```kotlin
// Gaining an hour (ie. overlap)
val zonedDateTime = "2019-11-03T01:00-04:00[America/New_York]".toZonedDateTime()
val laterOffset = zonedDateTime.withLaterOffsetAtOverlap() // 2019-11-03T01:00-05:00[America/New_York]
val anHourLater = zonedDateTime + 1.hours // 2019-11-03T01:00-5:00[America/New_York]
val nextDay = zonedDateTime + 1.days // 2019-11-04T01:00-05:00[America/New_York] (1 day = 25 hours in this case)

// Losing an hour (ie. gap)
val zonedDateTime = Date(2019, MARCH, 11) at Time(1, 30) at TimeZone("America/New_York") // -5 hours UTC

val plus30Mins = zonedDateTime + 1.hours
// 2019-03-11T03:30-04:00[America/New_York] (Note that 2:30AM doesn't exist and is bumped to 3:30AM)

val nextDay = zonedDateTime + 1.days // 2019-03-11T01:30-04:00[America/New_York] (1 day = 23 hours in this case)
```

### Interop

java.time / ThreeTenABP:
```kotlin
val javaLocalDate = Date(2019, OCTOBER, 24).toJavaLocalDate()
val islandDate = LocalDate.of(2019, OCTOBER, 24).toIslandDate()
```

iOS:
```kotlin
val nsDate = Instant.now().toNSDate()
val islandInstant = NSDate().toIslandInstant()
```

## Limitations

Currently, only the ISO calendar system is supported and the year range is limited to 1-9999. There's also no support for custom/localized date-time formats or week fields, so it's likely necessary to convert to a platform-specific type for presentation purposes. Addressing the ability to customize at least non-localized formats is high on the priority list.

## Notes on kotlin.time

An [experimental time API](https://github.com/Kotlin/KEEP/issues/190) has recently been added to the Kotlin standard library. Unfortunately, its design does't agree well with Island Time -- at least currently.

The Kotlin `Duration` class is based on a floating point number, which we steer clear of to avoid any accuracy issues that might come about during manipulation of floating point values and offer a fixed nanosecond precision across the entire supported time range. We also opt to preserve unit granularity. For example, `1.seconds` translates to `IntSeconds` rather than `Duration`. This allows you to specify a particular unit granularity in your code when required.

Currently, Kotlin's `WallClock` isn't available and doesn't offer time zone support in any case, so we have our own `Clock` implementation as well. Island Time takes an extension-oriented approach to clocks, enabling support for multiple implementations -- in fact, it's in envisioned that there will be separate millisecond and nanosecond precision clocks. In the future, we can concievably offer support for any standard library clock implementation as well.

# Feedback/Contributions

The goal of this project is not just to port the java.time library over to Kotlin Multiplatform, but to take full advantage of Kotlin language features to create a date-time DSL that feels natural to users of the language and encourages best practices where possible. To that end, any and all feedback would be much appreciated in helping to iron out the API.

If you're interested in contributing or have ideas on areas that can be improved (there are definitely many right now), please feel free to initiate a dialog by opening design-related issues or submitting pull requests.
