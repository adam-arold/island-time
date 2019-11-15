//
// This file is auto-generated by 'tools:code-generator'
//
@file:JvmMultifileClass
@file:JvmName("NanosecondsKt")

package io.islandtime.measures

import io.islandtime.internal.NANOSECONDS_PER_DAY
import io.islandtime.internal.NANOSECONDS_PER_HOUR
import io.islandtime.internal.NANOSECONDS_PER_MICROSECOND
import io.islandtime.internal.NANOSECONDS_PER_MILLISECOND
import io.islandtime.internal.NANOSECONDS_PER_MINUTE
import io.islandtime.internal.NANOSECONDS_PER_SECOND
import io.islandtime.internal.toZeroPaddedString
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
inline class IntNanoseconds(
  val value: Int
) : Comparable<IntNanoseconds> {
  val inMicroseconds: IntMicroseconds
    get() = (this.value / NANOSECONDS_PER_MICROSECOND).microseconds

  val inMilliseconds: IntMilliseconds
    get() = (this.value / NANOSECONDS_PER_MILLISECOND).milliseconds

  val inSeconds: IntSeconds
    get() = (this.value / NANOSECONDS_PER_SECOND).seconds

  val inMinutes: IntMinutes
    get() = (this.value / NANOSECONDS_PER_MINUTE).toInt().minutes

  val inHours: IntHours
    get() = (this.value / NANOSECONDS_PER_HOUR).toInt().hours

  val inDays: IntDays
    get() = (this.value / NANOSECONDS_PER_DAY).toInt().days

  fun isZero(): Boolean = value == 0

  val inSeconds: IntSeconds
    get() = (this.value / NANOSECONDS_PER_SECOND).seconds

  val inMilliseconds: IntMilliseconds
    get() = (this.value / NANOSECONDS_PER_MILLISECOND).milliseconds

  fun isPositive(): Boolean = value > 0

  override fun compareTo(other: IntNanoseconds): Int = value.compareTo(other.value)

  override fun toString(): String = if (isZero()) {
      "NANOSECONDS.isoPeriodZeroString"
  } else {
      buildString {
          val absValue = value.absoluteValue
          val wholePart = absValue / 1000000000
          val fractionalPart = absValue % 1000000000
          if (isNegative) { append('-') }
          append("PT")
          append(wholePart)
          if (fractionalPart != 0) {
              append('.')
              append(fractionalPart.toZeroPaddedString(9).dropLastWhile { it == '0' })
          }
          append('S')
      }
  }
  companion object {
    val MIN: IntNanoseconds = IntNanoseconds(Int.MIN_VALUE)

    val MAX: IntNanoseconds = IntNanoseconds(Int.MAX_VALUE)
  }
}

@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class LongNanoseconds(
  val value: Long
) : Comparable<LongNanoseconds> {
  val inMicroseconds: LongMicroseconds
    get() = (this.value / NANOSECONDS_PER_MICROSECOND).microseconds

  val inMilliseconds: LongMilliseconds
    get() = (this.value / NANOSECONDS_PER_MILLISECOND).milliseconds

  val inSeconds: LongSeconds
    get() = (this.value / NANOSECONDS_PER_SECOND).seconds

  val inMinutes: LongMinutes
    get() = (this.value / NANOSECONDS_PER_MINUTE).minutes

  val inHours: LongHours
    get() = (this.value / NANOSECONDS_PER_HOUR).hours

  val inDays: LongDays
    get() = (this.value / NANOSECONDS_PER_DAY).days

  val inHours: LongHours
    get() = (this.value / NANOSECONDS_PER_HOUR).hours

  val inMinutes: LongMinutes
    get() = (this.value / NANOSECONDS_PER_MINUTE).minutes

  fun isZero(): Boolean = value == 0L

  fun isNegative(): Boolean = value < 0L

  fun isPositive(): Boolean = value > 0L

  override fun compareTo(other: LongNanoseconds): Int = value.compareTo(other.value)

  override fun toString(): String = if (isZero()) {
      "NANOSECONDS.isoPeriodZeroString"
  } else {
      buildString {
          val absValue = value.absoluteValue
          val wholePart = absValue / 1000000000
          val fractionalPart = (absValue % 1000000000).toInt()
          if (isNegative) { append('-') }
          append("PT")
          append(wholePart)
          if (fractionalPart != 0) {
              append('.')
              append(fractionalPart.toZeroPaddedString(9).dropLastWhile { it == '0' })
          }
          append('S')
      }
  }
  companion object {
    val MIN: LongNanoseconds = LongNanoseconds(Long.MIN_VALUE)

    val MAX: LongNanoseconds = LongNanoseconds(Long.MAX_VALUE)
  }
}

val Int.nanoseconds: IntNanoseconds
  get() = IntNanoseconds(this)

val Long.nanoseconds: LongNanoseconds
  get() = LongNanoseconds(this)
