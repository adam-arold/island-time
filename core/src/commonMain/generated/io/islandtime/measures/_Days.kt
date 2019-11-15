//
// This file is auto-generated by 'tools:code-generator'
//
@file:JvmMultifileClass
@file:JvmName("DaysKt")

package io.islandtime.measures

import io.islandtime.internal.DAYS_PER_WEEK
import io.islandtime.internal.HOURS_PER_DAY
import io.islandtime.internal.MICROSECONDS_PER_DAY
import io.islandtime.internal.MILLISECONDS_PER_DAY
import io.islandtime.internal.MINUTES_PER_DAY
import io.islandtime.internal.NANOSECONDS_PER_DAY
import io.islandtime.internal.SECONDS_PER_DAY
import io.islandtime.internal.timesExact
import kotlin.Boolean
import kotlin.Comparable
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import kotlin.math.absoluteValue

@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class IntDays(
  val value: Int
) : Comparable<IntDays> {
  val inNanoseconds: LongNanoseconds
    get() = (this.value.toLong() * NANOSECONDS_PER_DAY).nanoseconds

  val inMicroseconds: LongMicroseconds
    get() = (this.value.toLong() * MICROSECONDS_PER_DAY).microseconds

  val inMilliseconds: LongMilliseconds
    get() = (this.value.toLong() * MILLISECONDS_PER_DAY).milliseconds

  val inSeconds: IntSeconds
    get() = (this.value * SECONDS_PER_DAY).seconds

  val inMinutes: IntMinutes
    get() = (this.value * MINUTES_PER_DAY).minutes

  val inHours: IntHours
    get() = (this.value * HOURS_PER_DAY).hours

  val inWeeks: IntWeeks
    get() = (this.value / DAYS_PER_WEEK).weeks

  fun isZero(): Boolean = value == 0

  val isZero: Boolean
    inline get() = this.value == 0

  val isNegative: Boolean
    inline get() = this.value < 0

  fun isPositive(): Boolean = value > 0

  override fun compareTo(other: IntDays): Int = value.compareTo(other.value)

  override fun toString(): String = if (isZero()) {
      "P0D"
  } else {
      buildString {
          if (isNegative()) { append('-') }
          append("P")
          append(value.absoluteValue)
          append('D')
      }
  }
  fun inNanosecondsExact() = (this.value.toLong() timesExact NANOSECONDS_PER_DAY).nanoseconds

  fun inMicrosecondsExact() = (this.value.toLong() timesExact MICROSECONDS_PER_DAY).microseconds

  fun inSecondsExact() = (this.value timesExact SECONDS_PER_DAY).seconds

  fun inMinutesExact() = (this.value timesExact MINUTES_PER_DAY).minutes

  fun inHoursExact() = (this.value timesExact HOURS_PER_DAY).hours

  companion object {
    val MIN: IntDays = IntDays(Int.MIN_VALUE)

    val MAX: IntDays = IntDays(Int.MAX_VALUE)
  }
}

@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class LongDays(
  val value: Long
) : Comparable<LongDays> {
  val inNanoseconds: LongNanoseconds
    get() = (this.value * NANOSECONDS_PER_DAY).nanoseconds

  val inMicroseconds: LongMicroseconds
    get() = (this.value * MICROSECONDS_PER_DAY).microseconds

  val inMilliseconds: LongMilliseconds
    get() = (this.value * MILLISECONDS_PER_DAY).milliseconds

  val inSeconds: LongSeconds
    get() = (this.value * SECONDS_PER_DAY).seconds

  val inMinutes: LongMinutes
    get() = (this.value * MINUTES_PER_DAY).minutes

  val inHours: LongHours
    get() = (this.value * HOURS_PER_DAY).hours

  val inMicroseconds: LongMicroseconds
    get() = (this.value * DAYS_PER_MICROSECOND).microseconds

  val inNanoseconds: LongNanoseconds
    get() = (this.value * DAYS_PER_NANOSECOND).nanoseconds

  fun isZero(): Boolean = value == 0L

  fun isNegative(): Boolean = value < 0L

  fun isPositive(): Boolean = value > 0L

  override fun compareTo(other: LongDays): Int = value.compareTo(other.value)

  override fun toString(): String = if (isZero()) {
      "P0D"
  } else {
      buildString {
          if (isNegative()) { append('-') }
          append("P")
          append(value.absoluteValue)
          append('D')
      }
  }
  fun inNanosecondsExact() = (this.value timesExact NANOSECONDS_PER_DAY).nanoseconds

  fun inMicrosecondsExact() = (this.value timesExact MICROSECONDS_PER_DAY).microseconds

  fun inMillisecondsExact() = (this.value timesExact MILLISECONDS_PER_DAY).milliseconds

  fun inSecondsExact() = (this.value timesExact SECONDS_PER_DAY).seconds

  fun inMinutesExact() = (this.value timesExact MINUTES_PER_DAY).minutes

  fun inHoursExact() = (this.value timesExact HOURS_PER_DAY).hours

  companion object {
    val MIN: LongDays = LongDays(Long.MIN_VALUE)

    val MAX: LongDays = LongDays(Long.MAX_VALUE)
  }
}

val Int.days: IntDays
  get() = IntDays(this)

val Long.days: LongDays
  get() = LongDays(this)
