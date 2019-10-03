package io.islandtime.ranges

import io.islandtime.*
import io.islandtime.Time.Companion.MIDNIGHT
import io.islandtime.measures.*
import io.islandtime.zone.PlatformDefault
import kotlin.test.*

class ZonedDateTimeIntervalTest {
    @BeforeTest
    fun setUp() {
        IslandTime.initialize(PlatformDefault)
    }

    @AfterTest
    fun tearDown() {
        IslandTime.tearDown()
    }

    @Test
    fun `EMPTY returns an empty interval`() {
        assertTrue { ZonedDateTimeInterval.EMPTY.isEmpty() }
        assertTrue { ZonedDateTimeInterval.EMPTY.isBounded }
        assertTrue { ZonedDateTimeInterval.EMPTY.hasBoundedStart }
        assertTrue { ZonedDateTimeInterval.EMPTY.hasBoundedEnd }
        assertFalse { ZonedDateTimeInterval.EMPTY.isUnbounded }
        assertFalse { ZonedDateTimeInterval.EMPTY.hasUnboundedStart }
        assertFalse { ZonedDateTimeInterval.EMPTY.hasUnboundedEnd }
    }

    @Test
    fun `UNBOUNDED returns an unbounded interval`() {
        assertFalse { ZonedDateTimeInterval.UNBOUNDED.isEmpty() }
        assertTrue { ZonedDateTimeInterval.UNBOUNDED.isUnbounded }
        assertTrue { ZonedDateTimeInterval.UNBOUNDED.hasUnboundedStart }
        assertTrue { ZonedDateTimeInterval.UNBOUNDED.hasUnboundedEnd }
        assertFalse { ZonedDateTimeInterval.UNBOUNDED.isBounded }
        assertFalse { ZonedDateTimeInterval.UNBOUNDED.hasBoundedStart }
        assertFalse { ZonedDateTimeInterval.UNBOUNDED.hasBoundedEnd }
    }

    @Test
    fun `contains() returns true for dates within bounded range`() {
        val start = Date(2019, Month.MARCH,10) at MIDNIGHT at "America/New_York".toTimeZone()
        val end = Date(2019, Month.MARCH,12) at MIDNIGHT at "America/New_York".toTimeZone()

        assertTrue { start in start..end }
        assertTrue { end in start..end }
        assertTrue { "2019-03-11T22:00-06:00[America/Denver]".toZonedDateTime() in start..end }
        assertTrue { "2019-03-10T05:00Z".toInstant() in start..end }
    }

    @Test
    fun `contains() returns true for dates within range with unbounded end`() {
        val start = Date(2019, Month.MARCH,10) at MIDNIGHT at "America/New_York".toTimeZone()
        val end = DateTime.MAX at "America/New_York".toTimeZone()

        assertTrue { start in start..end }
        assertTrue { DateTime.MAX at "Etc/UTC".toTimeZone() in start..end }
        assertTrue { DateTime.MAX at "America/Denver".toTimeZone() in start..end }
        assertTrue { Instant.MAX in start..end }
    }

    @Test
    fun `contains() returns true for dates within range with unbounded start`() {
        val start = DateTime.MIN at "America/New_York".toTimeZone()
        val end = Date(2019, Month.MARCH,10) at MIDNIGHT at "America/New_York".toTimeZone()

        assertTrue { start in start..end }
        assertTrue { end in start..end }
        assertTrue { DateTime.MIN at "Etc/UTC".toTimeZone() in start..end }
        assertTrue { DateTime.MIN at "America/Denver".toTimeZone() in start..end }
        assertTrue { Instant.MIN in start..end }
    }

    @Test
    fun `contains() returns false for out of range dates`() {
        val start = Date(2019, Month.MARCH,10) at MIDNIGHT at "America/New_York".toTimeZone()
        val end = Date(2019, Month.MARCH,12) at MIDNIGHT at "America/New_York".toTimeZone()

        assertFalse { start - 1.nanoseconds in start..end }
        assertFalse { end + 1.nanoseconds in start..end }
        assertFalse { "2019-03-11T23:00-06:00[America/Denver]".toZonedDateTime() in start..end }
        assertFalse { "2019-03-10T04:59:59Z".toInstant() in start..end }
    }

    @Test
    fun `until infix operator constructs an interval with non-inclusive end`() {
        val start = Date(2019, Month.MARCH,10) at MIDNIGHT at "America/New_York".toTimeZone()
        val end = Date(2019, Month.MARCH,12) at MIDNIGHT at "America/New_York".toTimeZone()
        val range = start until end

        assertEquals(start, range.first)
        assertEquals(start, range.start)
        assertEquals(end - 1.nanoseconds, range.last)
        assertEquals(end, range.endExclusive)
    }

    @Test
    fun `random() returns a zoned date-time within range`() {
        val start = Date(2019, Month.NOVEMBER,1) at MIDNIGHT at "America/New_York".toTimeZone()
        val end = Date(2019, Month.NOVEMBER,20) at MIDNIGHT at "America/New_York".toTimeZone()
        val range = start..end
        val randomInstant = range.random()
        assertTrue { randomInstant in range }
        assertEquals("America/New_York".toTimeZone(), randomInstant.zone)
    }

    @Test
    fun `days property returns 0 when range is empty`() {
        assertEquals(0L.days, ZonedDateTimeInterval.EMPTY.days)
    }

    @Test
    fun `days property throws an exception when the interval is unbounded`() {
        assertFailsWith<UnsupportedOperationException> { ZonedDateTimeInterval.UNBOUNDED.days }
    }

    @Test
    fun `hours property returns 0 when range is empty`() {
        assertEquals(0L.hours, ZonedDateTimeInterval.EMPTY.hours)
    }

    @Test
    fun `hours property throws an exception when the interval is unbounded`() {
        assertFailsWith<UnsupportedOperationException> { ZonedDateTimeInterval.UNBOUNDED.hours }
    }

    @Test
    fun `minutes property returns 0 when range is empty`() {
        assertEquals(0L.minutes, ZonedDateTimeInterval.EMPTY.minutes)
    }

    @Test
    fun `minutes property throws an exception when the interval is unbounded`() {
        assertFailsWith<UnsupportedOperationException> { ZonedDateTimeInterval.UNBOUNDED.minutes }
    }

    @Test
    fun `seconds property returns 0 when range is empty`() {
        assertEquals(0L.seconds, ZonedDateTimeInterval.EMPTY.seconds)
    }

    @Test
    fun `seconds property throws an exception when the interval is unbounded`() {
        assertFailsWith<UnsupportedOperationException> { ZonedDateTimeInterval.UNBOUNDED.seconds }
    }

    @Test
    fun `milliseconds property returns 0 when range is empty`() {
        assertEquals(0L.milliseconds, ZonedDateTimeInterval.EMPTY.milliseconds)
    }

    @Test
    fun `milliseconds property throws an exception when the interval is unbounded`() {
        assertFailsWith<UnsupportedOperationException> { ZonedDateTimeInterval.UNBOUNDED.milliseconds }
    }

    @Test
    fun `microseconds property returns 0 when range is empty`() {
        assertEquals(0L.microseconds, ZonedDateTimeInterval.EMPTY.microseconds)
    }

    @Test
    fun `microseconds property throws an exception when the interval is unbounded`() {
        assertFailsWith<UnsupportedOperationException> { ZonedDateTimeInterval.UNBOUNDED.microseconds }
    }

    @Test
    fun `nanoseconds property returns 0 when range is empty`() {
        assertEquals(0L.nanoseconds, ZonedDateTimeInterval.EMPTY.nanoseconds)
    }

    @Test
    fun `nanoseconds property throws an exception when the interval is unbounded`() {
        assertFailsWith<UnsupportedOperationException> { ZonedDateTimeInterval.UNBOUNDED.nanoseconds }
    }

    @Test
    fun `nanoseconds property returns 1 when the start and end instant are the same`() {
        val instant = Date(2019, Month.MARCH,10) at MIDNIGHT at "America/New_York".toTimeZone()
        assertEquals(1L.nanoseconds, (instant..instant).nanoseconds)
    }

    @Test
    fun `period of months during daylight savings gap`() {
        val zone = "America/New_York".toTimeZone()
        val then = Date(2019, 3, 10) at Time(1, 0) at zone
        val now = Date(2019, 4, 10) at Time(1, 0) at zone

        assertEquals(periodOf(1.months), periodBetween(then, now))
        assertEquals(periodOf((-1).months), periodBetween(now, then))
        assertEquals(periodOf(30.days), periodBetween(then, now - 1.nanoseconds))
        assertEquals(periodOf((-30).days), periodBetween(now, then + 1.nanoseconds))
        assertEquals(periodOf(1.months), (then until now).asPeriod())
        assertEquals(periodOf(30.days), (then until now - 1.nanoseconds).asPeriod())
    }

    @Test
    fun `period of days during daylight savings gap`() {
        val zone = "America/New_York".toTimeZone()
        val then = Date(2019, 3, 10) at Time(1, 0) at zone
        val now = Date(2019, 3, 11) at Time(1, 0) at zone

        assertEquals(periodOf(1.days), periodBetween(then, now))
        assertEquals(periodOf((-1).days), periodBetween(now, then))
        assertEquals(Period.ZERO, periodBetween(then, now - 1.nanoseconds))
        assertEquals(Period.ZERO, periodBetween(now, then + 1.nanoseconds))
        assertEquals(periodOf(1.days), (then until now).asPeriod())
        assertEquals(Period.ZERO, (then until now - 1.nanoseconds).asPeriod())
    }

    @Test
    fun `duration during daylight savings gap`() {
        val zone = "America/New_York".toTimeZone()
        val then = Date(2019, 3, 10) at Time(1, 0) at zone
        val now = Date(2019, 3, 11) at Time(1, 0) at zone

        assertEquals(durationOf(23.hours), durationBetween(then, now))
        assertEquals(durationOf((-23).hours), durationBetween(now, then))
        assertEquals(durationOf(23.hours), (then until now).asDuration())
        assertEquals(durationOf(23.hours.inSeconds, 1.nanoseconds), (then..now).asDuration())
    }

    @Test
    fun `period of days during daylight savings overlap`() {
        val zone = "America/New_York".toTimeZone()
        val then = Date(2019, 11, 3) at Time(1, 0) at zone
        val now = Date(2019, 11, 4) at Time(1, 0) at zone

        assertEquals(periodOf(1.days), periodBetween(then, now))
        assertEquals(Period.ZERO, periodBetween(then, now - 1.nanoseconds))
        assertEquals(periodOf(1.days), (then until now).asPeriod())
        assertEquals(Period.ZERO, (then until now - 1.nanoseconds).asPeriod())
    }

    @Test
    fun `duration during daylight savings overlap`() {
        val zone = "America/New_York".toTimeZone()
        val then = Date(2019, 11, 3) at Time(1, 0) at zone
        val now = Date(2019, 11, 4) at Time(1, 0) at zone

        assertEquals(durationOf(25.hours), durationBetween(then, now))
        assertEquals(durationOf(25.hours), (then until now).asDuration())
        assertEquals(durationOf(25.hours.inSeconds, 1.nanoseconds), (then..now).asDuration())
    }

    @Test
    fun years() {
        val zone = "America/New_York".toTimeZone()
        val then = Date(2019, 3, 10).atStartOfDayIn(zone)
        val now = Date(2020, 3, 10).atStartOfDayIn(zone)

        assertEquals(1.years, yearsBetween(then, now))
        assertEquals(0.years, yearsBetween(then, now - 1.nanoseconds))
        assertEquals(1.years, (then until now).years)
        assertEquals(0.years, (then until now - 1.nanoseconds).years)
    }

    @Test
    fun `months during daylight savings gap`() {
        val zone = "America/New_York".toTimeZone()
        val then = Date(2019, 3, 10).atStartOfDayIn(zone)
        val now = Date(2019, 4, 10).atStartOfDayIn(zone)

        assertEquals(1.months, monthsBetween(then, now))
        assertEquals(0.months, monthsBetween(then, now - 1.nanoseconds))
        assertEquals(1.months, (then until now).months)
        assertEquals(0.months, (then until now - 1.nanoseconds).months)
    }

    @Test
    fun `days during daylight savings gap`() {
        val zone = "America/New_York".toTimeZone()
        val then = Date(2019, 3, 10).atStartOfDayIn(zone)
        val now = Date(2019, 3, 11).atStartOfDayIn(zone)

        assertEquals(1L.days, daysBetween(then, now))
        assertEquals(0L.days, daysBetween(then, now - 1.nanoseconds))
        assertEquals(1L.days, (then until now).days)
        assertEquals(0L.days, (then until now - 1.nanoseconds).days)
    }

    @Test
    fun `hours during daylight savings gap`() {
        val zone = "America/New_York".toTimeZone()
        val then = Date(2019, 3, 10).atStartOfDayIn(zone)
        val now = Date(2019, 3, 10) at Time(5, 0) at zone

        assertEquals(4L.hours, hoursBetween(then, now))
        assertEquals(3L.hours, hoursBetween(then, now - 1.nanoseconds))
        assertEquals(4L.hours, (then until now).hours)
        assertEquals(3L.hours, (then until now - 1.nanoseconds).hours)
    }

//    @Test
//    fun `day progression`() {
//        val zone = "America/New_York".toTimeZone()
//        val then = Date(2019, 3, 10) at MIDNIGHT at zone
//        val now = Date(2019, 3, 11) at Time(0, 0, 0, 1) at zone
//
//        val progression = then until now step 1.days
//        assertEquals(then, progression.first)
//        assertEquals(
//            Date(2019, 3, 11) at MIDNIGHT at zone,
//            progression.last
//        )
//        assertFalse { progression.isEmpty() }
//        assertEquals(2, progression.count())
//    }

//    @Test
//    fun `month progression with positive step`() {
//        val zone = "America/New_York".toTimeZone()
//        val then = Date(2019, 3, 10) at MIDNIGHT at zone
//        val now = Date(2019, 5, 10) at MIDNIGHT at zone
//
//        val progression1 = then..now step 1.months
//        assertEquals(then, progression1.first)
//        assertEquals(
//            Date(2019, 5, 10) at MIDNIGHT at zone,
//            progression1.last
//        )
//        assertFalse { progression1.isEmpty() }
//        assertEquals(3, progression1.count())
//
//        val progression2 = then until now step 1.months
//        assertEquals(then, progression2.first)
//        assertEquals(
//            Date(2019, 4, 10) at MIDNIGHT at zone,
//            progression2.last
//        )
//        assertFalse { progression2.isEmpty() }
//        assertEquals(2, progression2.count())
//    }
}