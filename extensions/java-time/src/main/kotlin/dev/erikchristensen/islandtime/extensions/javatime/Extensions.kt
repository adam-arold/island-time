@file:JvmName("IslandTime")

package dev.erikchristensen.islandtime.extensions.javatime

import dev.erikchristensen.islandtime.*
import dev.erikchristensen.islandtime.date.Date
import dev.erikchristensen.islandtime.interval.*
import java.time.*
import java.time.Duration
import java.time.Period

@JvmName("convertFromJava")
fun LocalDate.toIslandDate(): Date {
    return Date(year, monthValue.toMonth(), dayOfMonth)
}

@JvmName("convertToJava")
fun Date.toJavaLocalDate(): LocalDate {
    return LocalDate.of(year, month.number, dayOfMonth)
}

@JvmName("convertFromJava")
fun LocalTime.toIslandTime(): Time {
    return Time(hour, minute, second, nano)
}

@JvmName("convertToJava")
fun Time.toJavaLocalTime(): LocalTime {
    return LocalTime.of(hour, minute, second, nanoOfSecond)
}

@JvmName("convertFromJava")
fun LocalDateTime.toIslandDateTime(): DateTime {
    return DateTime(
        Date(year, monthValue.toMonth(), dayOfMonth),
        Time(hour, minute, second, nano)
    )
}

@JvmName("convertToJava")
fun DateTime.toJavaLocalDateTime(): LocalDateTime {
    return LocalDateTime.of(year, month.number, dayOfMonth, hour, minute, second, nanoOfSecond)
}

@JvmName("convertFromJava")
fun java.time.OffsetDateTime.toIslandOffsetDateTime(): dev.erikchristensen.islandtime.OffsetDateTime {
    return dev.erikchristensen.islandtime.OffsetDateTime(
        DateTime(
            Date(year, monthValue.toMonth(), dayOfMonth),
            Time(hour, minute, second, nano)
        ),
        UtcOffset(offset.totalSeconds.seconds)
    )
}

@JvmName("convertToJava")
fun dev.erikchristensen.islandtime.OffsetDateTime.toJavaOffsetDateTime(): java.time.OffsetDateTime {
    return java.time.OffsetDateTime.of(
        LocalDateTime.of(year, month.number, dayOfMonth, hour, minute, second, nanoOfSecond),
        ZoneOffset.ofTotalSeconds(offset.totalSeconds.value)
    )
}

@JvmName("convertFromJava")
fun ZoneOffset.toIslandUtcOffset(): UtcOffset {
    return UtcOffset(totalSeconds.seconds)
}

@JvmName("convertToJava")
fun UtcOffset.toJavaZoneOffset(): ZoneOffset {
    return ZoneOffset.ofTotalSeconds(totalSeconds.value)
}

@JvmName("convertFromJava")
fun Duration.toIslandDuration(): dev.erikchristensen.islandtime.interval.Duration {
    return durationOf(seconds.seconds, nano.nanoseconds)
}

@JvmName("convertToJava")
fun dev.erikchristensen.islandtime.interval.Duration.toJavaDuration(): Duration {
    return Duration.ofSeconds(seconds.value, nanoOfSeconds.value.toLong())
}

@JvmName("convertFromJava")
fun Period.toIslandPeriod(): dev.erikchristensen.islandtime.interval.Period {
    return periodOf(years.years, months.months, days.days)
}

@JvmName("convertToJava")
fun dev.erikchristensen.islandtime.interval.Period.toJavaPeriod(): Period {
    return Period.of(years.value, months.value, days.value)
}