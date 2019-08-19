//
// This file is auto-generated by 'tools:code-generator'
//
@file:JvmMultifileClass
@file:JvmName("MillisecondsKt")

package dev.erikchristensen.islandtime.interval

import dev.erikchristensen.islandtime.internal.MILLISECONDS_PER_DAY
import dev.erikchristensen.islandtime.internal.MILLISECONDS_PER_HOUR
import dev.erikchristensen.islandtime.internal.MILLISECONDS_PER_MICROSECOND
import dev.erikchristensen.islandtime.internal.MILLISECONDS_PER_MINUTE
import dev.erikchristensen.islandtime.internal.MILLISECONDS_PER_NANOSECOND
import dev.erikchristensen.islandtime.internal.MILLISECONDS_PER_SECOND
import dev.erikchristensen.islandtime.internal.toZeroPaddedString
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
inline class IntMilliseconds(
  val value: Int
) : Comparable<IntMilliseconds> {
  val isZero: Boolean
    get() = this.value == 0

  val isNegative: Boolean
    get() = this.value < 0

  val isPositive: Boolean
    get() = this.value > 0

  val absoluteValue: IntMilliseconds
    get() = IntMilliseconds(this.value.absoluteValue)

  override fun compareTo(other: IntMilliseconds): Int = this.value.compareTo(other.value)

  override fun toString(): String = if (this.isZero) {
      "PT0S"
  } else {
      buildString {
          append("PT")
          val absValue = value.absoluteValue
          val wholePart = absValue / 1000
          val fractionalPart = absValue % 1000
          if (isNegative) { append('-') }
          append(wholePart)
          append('.')
          append(fractionalPart.toZeroPaddedString(3).dropLastWhile { it == '0' })
          append('S')
      }
  }

  companion object {
    val MIN: IntMilliseconds = IntMilliseconds(Int.MIN_VALUE)

    val MAX: IntMilliseconds = IntMilliseconds(Int.MAX_VALUE)
  }
}

@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class LongMilliseconds(
  val value: Long
) : Comparable<LongMilliseconds> {
  val isZero: Boolean
    get() = this.value == 0L

  val isNegative: Boolean
    get() = this.value < 0L

  val isPositive: Boolean
    get() = this.value > 0L

  val absoluteValue: LongMilliseconds
    get() = LongMilliseconds(this.value.absoluteValue)

  override fun compareTo(other: LongMilliseconds): Int = this.value.compareTo(other.value)

  override fun toString(): String = if (this.isZero) {
      "PT0S"
  } else {
      buildString {
          append("PT")
          val absValue = value.absoluteValue
          val wholePart = absValue / 1000
          val fractionalPart = (absValue % 1000).toInt()
          if (isNegative) { append('-') }
          append(wholePart)
          append('.')
          append(fractionalPart.toZeroPaddedString(3).dropLastWhile { it == '0' })
          append('S')
      }
  }

  companion object {
    val MIN: LongMilliseconds = LongMilliseconds(Long.MIN_VALUE)

    val MAX: LongMilliseconds = LongMilliseconds(Long.MAX_VALUE)
  }
}

val Int.milliseconds: IntMilliseconds
  get() = IntMilliseconds(this)

val Long.milliseconds: LongMilliseconds
  get() = LongMilliseconds(this)

fun IntMilliseconds.toLong() = LongMilliseconds(this.value.toLong())

fun LongMilliseconds.toInt() = IntMilliseconds(this.value.toInt())

operator fun IntMilliseconds.unaryPlus() = this

operator fun IntMilliseconds.unaryMinus() = IntMilliseconds(-value)

operator fun IntMilliseconds.plus(days: IntDays) = this.toLong() + days.asMilliseconds()

operator fun IntMilliseconds.plus(days: LongDays) = this.toLong() + days.asMilliseconds()

operator fun IntMilliseconds.plus(hours: IntHours) = this.toLong() + hours.asMilliseconds()

operator fun IntMilliseconds.plus(hours: LongHours) = this.toLong() + hours.asMilliseconds()

operator fun IntMilliseconds.plus(minutes: IntMinutes) = this.toLong() + minutes.asMilliseconds()

operator fun IntMilliseconds.plus(minutes: LongMinutes) = this.toLong() + minutes.asMilliseconds()

operator fun IntMilliseconds.plus(seconds: IntSeconds) = this.toLong() + seconds.asMilliseconds()

operator fun IntMilliseconds.plus(seconds: LongSeconds) = this.toLong() + seconds.asMilliseconds()

operator fun IntMilliseconds.plus(milliseconds: IntMilliseconds) =
    LongMilliseconds(this.value.toLong() + milliseconds.value)

operator fun IntMilliseconds.plus(milliseconds: LongMilliseconds) =
    LongMilliseconds(this.value.toLong() + milliseconds.value)

operator fun IntMilliseconds.plus(microseconds: IntMicroseconds) = this.toLong().asMicroseconds() +
    microseconds.toLong()

operator fun IntMilliseconds.plus(microseconds: LongMicroseconds) = this.toLong().asMicroseconds() +
    microseconds

operator fun IntMilliseconds.plus(nanoseconds: IntNanoseconds) = this.toLong().asNanoseconds() +
    nanoseconds.toLong()

operator fun IntMilliseconds.plus(nanoseconds: LongNanoseconds) = this.toLong().asNanoseconds() +
    nanoseconds

operator fun IntMilliseconds.minus(days: IntDays) = plus(-days)

operator fun IntMilliseconds.minus(days: LongDays) = plus(-days)

operator fun IntMilliseconds.minus(hours: IntHours) = plus(-hours)

operator fun IntMilliseconds.minus(hours: LongHours) = plus(-hours)

operator fun IntMilliseconds.minus(minutes: IntMinutes) = plus(-minutes)

operator fun IntMilliseconds.minus(minutes: LongMinutes) = plus(-minutes)

operator fun IntMilliseconds.minus(seconds: IntSeconds) = plus(-seconds)

operator fun IntMilliseconds.minus(seconds: LongSeconds) = plus(-seconds)

operator fun IntMilliseconds.minus(milliseconds: IntMilliseconds) = plus(-milliseconds)

operator fun IntMilliseconds.minus(milliseconds: LongMilliseconds) = plus(-milliseconds)

operator fun IntMilliseconds.minus(microseconds: IntMicroseconds) = plus(-microseconds)

operator fun IntMilliseconds.minus(microseconds: LongMicroseconds) = plus(-microseconds)

operator fun IntMilliseconds.minus(nanoseconds: IntNanoseconds) = plus(-nanoseconds)

operator fun IntMilliseconds.minus(nanoseconds: LongNanoseconds) = plus(-nanoseconds)

operator fun IntMilliseconds.times(scalar: Int) = this.toLong() * scalar

operator fun IntMilliseconds.times(scalar: Long) = this.toLong() * scalar

operator fun IntMilliseconds.div(scalar: Int) = this.toLong() / scalar

operator fun IntMilliseconds.div(scalar: Long) = this.toLong() / scalar

operator fun IntMilliseconds.rem(scalar: Int) = this.toLong() % scalar

operator fun IntMilliseconds.rem(scalar: Long) = this.toLong() % scalar

fun IntMilliseconds.toWholeDays() = (this.value / MILLISECONDS_PER_DAY.toInt()).days

fun IntMilliseconds.toWholeHours() = (this.value / MILLISECONDS_PER_HOUR.toInt()).hours

fun IntMilliseconds.toWholeMinutes() = (this.value / MILLISECONDS_PER_MINUTE.toInt()).minutes

fun IntMilliseconds.toWholeSeconds() = (this.value / MILLISECONDS_PER_SECOND.toInt()).seconds

fun IntMilliseconds.asMicroseconds() = (this.value.toLong() *
    MILLISECONDS_PER_MICROSECOND.toInt()).microseconds

fun IntMilliseconds.asNanoseconds() = (this.value.toLong() *
    MILLISECONDS_PER_NANOSECOND.toInt()).nanoseconds

inline fun <T> IntMilliseconds.toComponents(action: (
  days: IntDays,
  hours: IntHours,
  minutes: IntMinutes,
  seconds: IntSeconds,
  milliseconds: IntMilliseconds
) -> T): T {
  val days = this.toWholeDays()
  val hours = (this - days).toInt().toWholeHours()
  val minutes = (this - days - hours).toInt().toWholeMinutes()
  val seconds = (this - days - hours - minutes).toInt().toWholeSeconds()
  val milliseconds = (this - days - hours - minutes - seconds).toInt()
  return action(days, hours, minutes, seconds, milliseconds)
}

inline fun <T> IntMilliseconds.toComponents(action: (
  hours: IntHours,
  minutes: IntMinutes,
  seconds: IntSeconds,
  milliseconds: IntMilliseconds
) -> T): T {
  val hours = this.toWholeHours()
  val minutes = (this - hours).toInt().toWholeMinutes()
  val seconds = (this - hours - minutes).toInt().toWholeSeconds()
  val milliseconds = (this - hours - minutes - seconds).toInt()
  return action(hours, minutes, seconds, milliseconds)
}

inline fun <T> IntMilliseconds.toComponents(action: (
  minutes: IntMinutes,
  seconds: IntSeconds,
  milliseconds: IntMilliseconds
) -> T): T {
  val minutes = this.toWholeMinutes()
  val seconds = (this - minutes).toInt().toWholeSeconds()
  val milliseconds = (this - minutes - seconds).toInt()
  return action(minutes, seconds, milliseconds)
}

inline fun <T> IntMilliseconds.toComponents(action: (seconds: IntSeconds,
    milliseconds: IntMilliseconds) -> T): T {
  val seconds = this.toWholeSeconds()
  val milliseconds = (this - seconds).toInt()
  return action(seconds, milliseconds)
}

operator fun LongMilliseconds.unaryPlus() = this

operator fun LongMilliseconds.unaryMinus() = LongMilliseconds(-value)

operator fun LongMilliseconds.plus(days: IntDays) = this + days.asMilliseconds()

operator fun LongMilliseconds.plus(days: LongDays) = this + days.asMilliseconds()

operator fun LongMilliseconds.plus(hours: IntHours) = this + hours.asMilliseconds()

operator fun LongMilliseconds.plus(hours: LongHours) = this + hours.asMilliseconds()

operator fun LongMilliseconds.plus(minutes: IntMinutes) = this + minutes.asMilliseconds()

operator fun LongMilliseconds.plus(minutes: LongMinutes) = this + minutes.asMilliseconds()

operator fun LongMilliseconds.plus(seconds: IntSeconds) = this + seconds.asMilliseconds()

operator fun LongMilliseconds.plus(seconds: LongSeconds) = this + seconds.asMilliseconds()

operator fun LongMilliseconds.plus(milliseconds: IntMilliseconds) = LongMilliseconds(this.value +
    milliseconds.value)

operator fun LongMilliseconds.plus(milliseconds: LongMilliseconds) = LongMilliseconds(this.value +
    milliseconds.value)

operator fun LongMilliseconds.plus(microseconds: IntMicroseconds) = this.asMicroseconds() +
    microseconds

operator fun LongMilliseconds.plus(microseconds: LongMicroseconds) = this.asMicroseconds() +
    microseconds

operator fun LongMilliseconds.plus(nanoseconds: IntNanoseconds) = this.asNanoseconds() + nanoseconds

operator fun LongMilliseconds.plus(nanoseconds: LongNanoseconds) = this.asNanoseconds() +
    nanoseconds

operator fun LongMilliseconds.minus(days: IntDays) = plus(-days)

operator fun LongMilliseconds.minus(days: LongDays) = plus(-days)

operator fun LongMilliseconds.minus(hours: IntHours) = plus(-hours)

operator fun LongMilliseconds.minus(hours: LongHours) = plus(-hours)

operator fun LongMilliseconds.minus(minutes: IntMinutes) = plus(-minutes)

operator fun LongMilliseconds.minus(minutes: LongMinutes) = plus(-minutes)

operator fun LongMilliseconds.minus(seconds: IntSeconds) = plus(-seconds)

operator fun LongMilliseconds.minus(seconds: LongSeconds) = plus(-seconds)

operator fun LongMilliseconds.minus(milliseconds: IntMilliseconds) = plus(-milliseconds)

operator fun LongMilliseconds.minus(milliseconds: LongMilliseconds) = plus(-milliseconds)

operator fun LongMilliseconds.minus(microseconds: IntMicroseconds) = plus(-microseconds)

operator fun LongMilliseconds.minus(microseconds: LongMicroseconds) = plus(-microseconds)

operator fun LongMilliseconds.minus(nanoseconds: IntNanoseconds) = plus(-nanoseconds)

operator fun LongMilliseconds.minus(nanoseconds: LongNanoseconds) = plus(-nanoseconds)

operator fun LongMilliseconds.times(scalar: Int) = LongMilliseconds(this.value * scalar)

operator fun LongMilliseconds.times(scalar: Long) = LongMilliseconds(this.value * scalar)

operator fun LongMilliseconds.div(scalar: Int) = LongMilliseconds(this.value / scalar)

operator fun LongMilliseconds.div(scalar: Long) = LongMilliseconds(this.value / scalar)

operator fun LongMilliseconds.rem(scalar: Int) = LongMilliseconds(this.value % scalar)

operator fun LongMilliseconds.rem(scalar: Long) = LongMilliseconds(this.value % scalar)

fun LongMilliseconds.toWholeDays() = (this.value / MILLISECONDS_PER_DAY).days

fun LongMilliseconds.toWholeHours() = (this.value / MILLISECONDS_PER_HOUR).hours

fun LongMilliseconds.toWholeMinutes() = (this.value / MILLISECONDS_PER_MINUTE).minutes

fun LongMilliseconds.toWholeSeconds() = (this.value / MILLISECONDS_PER_SECOND).seconds

fun LongMilliseconds.asMicroseconds() = (this.value * MILLISECONDS_PER_MICROSECOND).microseconds

fun LongMilliseconds.asNanoseconds() = (this.value * MILLISECONDS_PER_NANOSECOND).nanoseconds

inline fun <T> LongMilliseconds.toComponents(action: (
  days: LongDays,
  hours: IntHours,
  minutes: IntMinutes,
  seconds: IntSeconds,
  milliseconds: IntMilliseconds
) -> T): T {
  val days = this.toWholeDays()
  val hours = (this - days).toInt().toWholeHours()
  val minutes = (this - days - hours).toInt().toWholeMinutes()
  val seconds = (this - days - hours - minutes).toInt().toWholeSeconds()
  val milliseconds = (this - days - hours - minutes - seconds).toInt()
  return action(days, hours, minutes, seconds, milliseconds)
}

inline fun <T> LongMilliseconds.toComponents(action: (
  hours: LongHours,
  minutes: IntMinutes,
  seconds: IntSeconds,
  milliseconds: IntMilliseconds
) -> T): T {
  val hours = this.toWholeHours()
  val minutes = (this - hours).toInt().toWholeMinutes()
  val seconds = (this - hours - minutes).toInt().toWholeSeconds()
  val milliseconds = (this - hours - minutes - seconds).toInt()
  return action(hours, minutes, seconds, milliseconds)
}

inline fun <T> LongMilliseconds.toComponents(action: (
  minutes: LongMinutes,
  seconds: IntSeconds,
  milliseconds: IntMilliseconds
) -> T): T {
  val minutes = this.toWholeMinutes()
  val seconds = (this - minutes).toInt().toWholeSeconds()
  val milliseconds = (this - minutes - seconds).toInt()
  return action(minutes, seconds, milliseconds)
}

inline fun <T> LongMilliseconds.toComponents(action: (seconds: LongSeconds,
    milliseconds: IntMilliseconds) -> T): T {
  val seconds = this.toWholeSeconds()
  val milliseconds = (this - seconds).toInt()
  return action(seconds, milliseconds)
}
