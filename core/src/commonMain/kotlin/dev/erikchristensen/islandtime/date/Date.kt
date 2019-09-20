package dev.erikchristensen.islandtime.date

import dev.erikchristensen.islandtime.*
import dev.erikchristensen.islandtime.internal.*
import dev.erikchristensen.islandtime.interval.*
import dev.erikchristensen.islandtime.parser.*

/**
 * A date in an arbitrary region
 * @constructor Create a [Date] from a year, month, and day of month
 * @param year the year
 * @param month the month
 * @param day the day of the month
 * @throws DateTimeException if the year or day is invalid
 */
class Date(
    val year: Int,
    val month: Month,
    private val day: Int
) : Comparable<Date> {

    init {
        checkValidYear(year)
        checkValidDayOfMonth(year, month, day)
    }

    /**
     * Create a [Date] from a year, ISO month number, and day of month
     * @param year the year
     * @param monthNumber the ISO-8601 month number
     * @param day the day of the month
     * @return a new [Date]
     * @throws DateTimeException if the year, month, or day is invalid
     */
    constructor(
        year: Int,
        monthNumber: Int,
        day: Int
    ): this(year, Month(monthNumber), day)

    /**
     * The day of the week
     */
    val dayOfWeek: DayOfWeek
        get() {
            val zeroIndexedDayOfWeek = (daysSinceUnixEpoch.value + 3) floorMod 7
            return DayOfWeek.values()[zeroIndexedDayOfWeek.toInt()]
        }

    /**
     * The day of the month
     */
    val dayOfMonth: Int get() = day

    /**
     * The day of the year
     */
    val dayOfYear: Int get() = month.firstDayOfYearIn(year) + dayOfMonth - 1

    /**
     * The ISO month number
     */
    inline val monthNumber: Int get() = month.number

    /**
     * true if this date falls within a leap year
     */
    val isInLeapYear: Boolean get() = isLeapYear(year)

    /**
     * true if this is a leap day
     */
    val isLeapDay: Boolean get() = month == Month.FEBRUARY && dayOfMonth == 29

    /**
     * The length of this date's month in days
     */
    val lengthOfMonth: IntDays get() = month.lengthIn(year)

    /**
     * The length of this date's year in days
     */
    val lengthOfYear: IntDays get() = lengthOfYear(year)

    /**
     * Get the year and month of this date
     */
    inline val yearMonth: YearMonth get() = YearMonth(year, month)

    //
    // Adapted from https://github.com/ThreeTen/threetenbp/blob/master/src/main/java/org/threeten/bp/LocalDate.java
    //

    /**
     * Get the number of days away from the Unix epoch that this date falls
     */
    val daysSinceUnixEpoch: LongDays get() = unixEpochDay.days

    /**
     * The Unix epoch day representing this date
     */
    val unixEpochDay: Long
        get() {
            var total = DAYS_IN_COMMON_YEAR * year

            if (year >= 0) {
                total += (year + 3) / 4 - (year + 99) / 100 + (year + 399) / 400
            } else {
                total -= year / -4 - year / -100 + year / -400
            }

            total += ((367 * monthNumber - 362) / MONTHS_IN_YEAR)
            total += dayOfMonth - 1

            if (monthNumber > 2) {
                total -= if (isInLeapYear) 1 else 2
            }

            return total - DAYS_FROM_0000_TO_1970
        }

    operator fun plus(years: IntYears) = plus(years.toLong())

    operator fun plus(years: LongYears): Date {
        return if (years.value == 0L) {
            this
        } else {
            val newYear = checkValidYear(year + years.value)
            copy(year = newYear, dayOfMonth = dayOfMonth.coerceAtMost(month.lastDayIn(newYear)))
        }
    }

    operator fun plus(months: IntMonths) = plus(months.toLong())

    operator fun plus(months: LongMonths): Date {
        return if (months.value == 0L) {
            this
        } else {
            val newMonthsSinceYear0 = monthsSinceYear0 + months.value
            val newYear = checkValidYear(newMonthsSinceYear0 floorDiv MONTHS_IN_YEAR)
            val newMonth = Month.values()[(newMonthsSinceYear0 floorMod MONTHS_IN_YEAR).toInt()]

            Date(newYear, newMonth, dayOfMonth.coerceAtMost(newMonth.lastDayIn(newYear)))
        }
    }

    operator fun plus(days: IntDays) = plus(days.toLong())

    operator fun plus(days: LongDays): Date {
        return if (days.value == 0L) {
            this
        } else {
            fromDaysSinceUnixEpoch(daysSinceUnixEpoch plusExact days)
        }
    }

    operator fun minus(years: IntYears) = plus(-years.toLong())

    operator fun minus(years: LongYears): Date {
        return if (years.value == Long.MIN_VALUE) {
            this + Long.MAX_VALUE.years + 1.years
        } else {
            plus(-years)
        }
    }

    operator fun minus(months: IntMonths) = plus(-months.toLong())

    operator fun minus(months: LongMonths): Date {
        return if (months.value == Long.MIN_VALUE) {
            this + Long.MAX_VALUE.months + 1.months
        } else {
            plus(-months)
        }
    }

    operator fun minus(days: IntDays) = plus(-days.toLong())

    operator fun minus(days: LongDays): Date {
        return if (days.value == Long.MIN_VALUE) {
            this + Long.MAX_VALUE.days + 1.days
        } else {
            plus(-days)
        }
    }

    operator fun rangeTo(other: Date) = DateRange(this, other)

    operator fun component1() = year
    operator fun component2() = month
    operator fun component3() = day

    override fun compareTo(other: Date): Int {
        val yearDiff = year - other.year

        return if (yearDiff != 0) {
            yearDiff
        } else {
            val monthDiff = month.ordinal - other.month.ordinal

            if (monthDiff != 0) {
                monthDiff
            } else {
                day - other.day
            }
        }
    }

    override fun toString() = buildString(MAX_DATE_STRING_LENGTH) { appendDate(this@Date) }

    override fun equals(other: Any?): Boolean {
        return this === other || (other is Date && year == other.year && month == other.month && day == other.day)
    }

    override fun hashCode(): Int {
        var result = year
        result = 31 * result + month.hashCode()
        result = 31 * result + day
        return result
    }

    /**
     * Return a new [Date], replacing the year, month, and day of month with new values, as desired
     */
    fun copy(
        year: Int = this.year,
        month: Month = this.month,
        dayOfMonth: Int = this.day
    ) = Date(year, month, dayOfMonth)

    /**
     * Return a new [Date], replacing the year, month, and day of month with new values, as desired
     */
    fun copy(
        year: Int = this.year,
        monthNumber: Int,
        dayOfMonth: Int = this.day
    ) = Date(year, monthNumber, dayOfMonth)

    /**
     * Return a new [Date], replacing the year and day of the year with new values, as desired
     */
    fun copy(
        year: Int = this.year,
        dayOfYear: Int = this.dayOfYear
    ) = Date(year, dayOfYear)

    companion object {
        val MIN = Date(Year.MIN_VALUE, 1)
        val MAX = Date(Year.MAX_VALUE, Year.MAX.lastDay)

        /**
         * Create the [Date] that falls a certain number of days from the Unix epoch of 1970-01-01
         * @param days the number of days relative to the Unix epoch
         * @return a new [Date]
         * @throws DateTimeException if outside of the supported date range
         */
        fun fromDaysSinceUnixEpoch(days: LongDays): Date {
            return fromUnixEpochDay(days.value)
        }

        //
        // Adapted from https://github.com/ThreeTen/threetenbp/blob/master/src/main/java/org/threeten/bp/LocalDate.java
        //

        /**
         * Create a [Date] from the day of the Unix epoch
         * @param day the day of the Unix epoch
         * @return a new [Date]
         * @throws DateTimeException if outside of the supported date range
         */
        fun fromUnixEpochDay(day: Long): Date {
            var zeroDay = day + DAYS_FROM_0000_TO_1970
            // find the march-based year
            zeroDay -= 60  // adjust to 0000-03-01 so leap day is at end of four year cycle
            var adjust: Long = 0
            if (zeroDay < 0) {
                // adjust negative years to positive for calculation
                val adjustCycles = (zeroDay + 1) / DAYS_PER_400_YEAR_CYCLE - 1
                adjust = adjustCycles * 400
                zeroDay += -adjustCycles * DAYS_PER_400_YEAR_CYCLE
            }
            var yearEst = (400 * zeroDay + 591) / DAYS_PER_400_YEAR_CYCLE
            var doyEst = zeroDay - (365 * yearEst + yearEst / 4 - yearEst / 100 + yearEst / 400)
            if (doyEst < 0) {
                // fix estimate
                yearEst--
                doyEst = zeroDay - (365 * yearEst + yearEst / 4 - yearEst / 100 + yearEst / 400)
            }
            yearEst += adjust  // reset any negative year
            val marchDoy0 = doyEst.toInt()

            // convert march-based values back to january-based
            val marchMonth0 = (marchDoy0 * 5 + 2) / 153
            val month = (marchMonth0 + 2) % 12 + 1
            val dom = marchDoy0 - (marchMonth0 * 306 + 5) / 10 + 1
            yearEst += (marchMonth0 / 10).toLong()

            return Date(yearEst.toInt(), Month(month), dom)
        }

        fun now() = now(systemClock())

        fun now(clock: Clock): Date {
            return clock.instant().toDateAt(clock.timeZone)
        }
    }
}

/**
 * Create a [Date] from a year and day of year
 * @param year the year
 * @param dayOfYear the day of the calendar year
 * @return a new [Date]
 * @throws DateTimeException if the year or day of year are invalid
 */
@Suppress("FunctionName")
fun Date(year: Int, dayOfYear: Int): Date {
    checkValidYear(year)
    checkValidDayOfYear(year, dayOfYear)

    val testMonth = ((dayOfYear - 1) / 31 + 1).toMonth()
    val month = if (dayOfYear > testMonth.lastDayOfYearIn(year)) testMonth + 1.months else testMonth
    val dayOfMonth = dayOfYear - month.firstDayOfYearIn(year) + 1

    return Date(year, month, dayOfMonth)
}

fun Instant.toDateAt(offset: UtcOffset): Date {
    var adjustedSeconds = secondsSinceUnixEpoch + offset.totalSeconds

    if (nanosecondAdjustment.isNegative) {
        adjustedSeconds -= 1.seconds
    }

    val unixEpochDay = adjustedSeconds.value floorDiv SECONDS_PER_DAY
    return Date.fromUnixEpochDay(unixEpochDay)
}

fun Instant.toDateAt(zone: TimeZone): Date {
    return this.toDateAt(zone.rules.offsetAt(this))
}

fun String.toDate() = toDate(Iso8601.Extended.CALENDAR_DATE_PARSER)

fun String.toDate(parser: DateTimeParser): Date {
    val result = parser.parse(this)
    return result.toDate() ?: raiseParserFieldResolutionException("Date", this)
}

internal fun DateTimeParseResult.toDate(): Date? {
    val year = this[DateTimeField.YEAR]

    if (year != null) {
        val month = this[DateTimeField.MONTH_OF_YEAR]
        val dayOfMonth = this[DateTimeField.DAY_OF_MONTH]

        try {
            if (month != null && dayOfMonth != null) {
                return Date(year.toIntExact(), month.toIntExact().toMonth(), dayOfMonth.toIntExact())
            }

            val dayOfYear = this[DateTimeField.DAY_OF_YEAR]

            if (dayOfYear != null) {
                return Date(year.toIntExact(), dayOfYear.toIntExact())
            }
        } catch (e: ArithmeticException) {
            throw DateTimeException(e.message, e)
        }
    }

    return null
}

internal const val MAX_DATE_STRING_LENGTH = 10

internal fun StringBuilder.appendDate(date: Date): StringBuilder {
    with(date) {
        appendZeroPadded(year, 4)
        append('-')
        appendZeroPadded(monthNumber, 2)
        append('-')
        appendZeroPadded(dayOfMonth, 2)
    }
    return this
}

fun periodBetween(start: Date, endExclusive: Date): Period {
    var totalMonths = endExclusive.monthsSinceYear0.toLong() - start.monthsSinceYear0
    val dayDiff = (endExclusive.dayOfMonth - start.dayOfMonth).days

    val days = when {
        totalMonths > 0 && dayDiff.value < 0 -> {
            totalMonths--
            val testDate = start + totalMonths.months
            daysBetween(testDate, endExclusive).toInt()
        }
        totalMonths < 0 && dayDiff.value > 0 -> {
            totalMonths++
            dayDiff - endExclusive.lengthOfMonth
        }
        else -> dayDiff
    }
    val years = (totalMonths / MONTHS_IN_YEAR).years.toInt()
    val months = (totalMonths % MONTHS_IN_YEAR).months.toInt()

    return periodOf(years, months, days)
}

fun daysBetween(start: Date, endExclusive: Date): LongDays {
    return endExclusive.daysSinceUnixEpoch - start.daysSinceUnixEpoch
}

fun monthsBetween(start: Date, endExclusive: Date): IntMonths {
    val startDays = start.monthsSinceYear0 * 32L + start.dayOfMonth
    val endDays = endExclusive.monthsSinceYear0 * 32L + endExclusive.dayOfMonth
    return ((endDays - startDays) / 32).toInt().months
}

fun yearsBetween(start: Date, endExclusive: Date): IntYears {
    return monthsBetween(start, endExclusive).inWholeYears
}

private inline val Date.monthsSinceYear0 get() = year * 12 + month.ordinal

private fun checkValidDayOfMonth(year: Int, month: Month, dayOfMonth: Int): Int {
    if (dayOfMonth !in month.dayRangeIn(year)) {
        throw DateTimeException("The day '$dayOfMonth' doesn't exist in $month of $year")
    }
    return dayOfMonth
}

private fun checkValidDayOfYear(year: Int, dayOfYear: Int): Int {
    if (dayOfYear !in 1..lastDayOfYear(year)) {
        if (dayOfYear == 366) {
            throw DateTimeException("Day of year '$dayOfYear' is invalid since '$year' isn't a leap year")
        } else {
            throw DateTimeException("'$dayOfYear' is not a valid day of the year")
        }
    }
    return dayOfYear
}