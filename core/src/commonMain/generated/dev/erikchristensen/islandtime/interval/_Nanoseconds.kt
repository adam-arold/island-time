//
// This file is auto-generated by 'tools:code-generator'
//
@file:JvmMultifileClass
@file:JvmName("NanosecondsKt")

package dev.erikchristensen.islandtime.interval

import dev.erikchristensen.islandtime.internal.NANOSECONDS_PER_DAY
import dev.erikchristensen.islandtime.internal.NANOSECONDS_PER_HOUR
import dev.erikchristensen.islandtime.internal.NANOSECONDS_PER_MICROSECOND
import dev.erikchristensen.islandtime.internal.NANOSECONDS_PER_MILLISECOND
import dev.erikchristensen.islandtime.internal.NANOSECONDS_PER_MINUTE
import dev.erikchristensen.islandtime.internal.NANOSECONDS_PER_SECOND
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
inline class IntNanoseconds(
  val value: Int
) : Comparable<IntNanoseconds> {
  val isZero: Boolean
    get() = this.value == 0

  val isNegative: Boolean
    get() = this.value < 0

  val isPositive: Boolean
    get() = this.value > 0

  val absoluteValue: IntNanoseconds
    get() = IntNanoseconds(this.value.absoluteValue)

  override fun compareTo(other: IntNanoseconds): Int = this.value.compareTo(other.value)

  override fun toString(): String = if (this.isZero) {
      "PT0S"
  } else {
      buildString {
          append('P')
          val absValue = value.absoluteValue
          val wholePart = absValue / 1000000000
          val fractionalPart = absValue % 1000000000
          if (isNegative) { append('-') }
          append(wholePart)
          append('.')
          append(fractionalPart.toZeroPaddedString(9).dropLastWhile { it == '0' })
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
  val isZero: Boolean
    get() = this.value == 0L

  val isNegative: Boolean
    get() = this.value < 0L

  val isPositive: Boolean
    get() = this.value > 0L

  val absoluteValue: LongNanoseconds
    get() = LongNanoseconds(this.value.absoluteValue)

  override fun compareTo(other: LongNanoseconds): Int = this.value.compareTo(other.value)

  override fun toString(): String = if (this.isZero) {
      "PT0S"
  } else {
      buildString {
          append('P')
          val absValue = value.absoluteValue
          val wholePart = absValue / 1000000000
          val fractionalPart = (absValue % 1000000000).toInt()
          if (isNegative) { append('-') }
          append(wholePart)
          append('.')
          append(fractionalPart.toZeroPaddedString(9).dropLastWhile { it == '0' })
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

fun IntNanoseconds.toLong() = LongNanoseconds(this.value.toLong())

fun LongNanoseconds.toInt() = IntNanoseconds(this.value.toInt())

operator fun IntNanoseconds.unaryPlus() = this

operator fun IntNanoseconds.unaryMinus() = IntNanoseconds(-value)

operator fun IntNanoseconds.plus(days: IntDays) = this.toLong() + days.asNanoseconds()

operator fun IntNanoseconds.plus(days: LongDays) = this.toLong() + days.asNanoseconds()

operator fun IntNanoseconds.plus(hours: IntHours) = this.toLong() + hours.asNanoseconds()

operator fun IntNanoseconds.plus(hours: LongHours) = this.toLong() + hours.asNanoseconds()

operator fun IntNanoseconds.plus(minutes: IntMinutes) = this.toLong() + minutes.asNanoseconds()

operator fun IntNanoseconds.plus(minutes: LongMinutes) = this.toLong() + minutes.asNanoseconds()

operator fun IntNanoseconds.plus(seconds: IntSeconds) = this.toLong() + seconds.asNanoseconds()

operator fun IntNanoseconds.plus(seconds: LongSeconds) = this.toLong() + seconds.asNanoseconds()

operator fun IntNanoseconds.plus(milliseconds: IntMilliseconds) = this.toLong() +
    milliseconds.asNanoseconds()

operator fun IntNanoseconds.plus(milliseconds: LongMilliseconds) = this.toLong() +
    milliseconds.asNanoseconds()

operator fun IntNanoseconds.plus(microseconds: IntMicroseconds) = this.toLong() +
    microseconds.asNanoseconds()

operator fun IntNanoseconds.plus(microseconds: LongMicroseconds) = this.toLong() +
    microseconds.asNanoseconds()

operator fun IntNanoseconds.plus(nanoseconds: IntNanoseconds) =
    LongNanoseconds(this.value.toLong() + nanoseconds.value)

operator fun IntNanoseconds.plus(nanoseconds: LongNanoseconds) =
    LongNanoseconds(this.value.toLong() + nanoseconds.value)

operator fun IntNanoseconds.minus(days: IntDays) = plus(-days)

operator fun IntNanoseconds.minus(days: LongDays) = plus(-days)

operator fun IntNanoseconds.minus(hours: IntHours) = plus(-hours)

operator fun IntNanoseconds.minus(hours: LongHours) = plus(-hours)

operator fun IntNanoseconds.minus(minutes: IntMinutes) = plus(-minutes)

operator fun IntNanoseconds.minus(minutes: LongMinutes) = plus(-minutes)

operator fun IntNanoseconds.minus(seconds: IntSeconds) = plus(-seconds)

operator fun IntNanoseconds.minus(seconds: LongSeconds) = plus(-seconds)

operator fun IntNanoseconds.minus(milliseconds: IntMilliseconds) = plus(-milliseconds)

operator fun IntNanoseconds.minus(milliseconds: LongMilliseconds) = plus(-milliseconds)

operator fun IntNanoseconds.minus(microseconds: IntMicroseconds) = plus(-microseconds)

operator fun IntNanoseconds.minus(microseconds: LongMicroseconds) = plus(-microseconds)

operator fun IntNanoseconds.minus(nanoseconds: IntNanoseconds) = plus(-nanoseconds)

operator fun IntNanoseconds.minus(nanoseconds: LongNanoseconds) = plus(-nanoseconds)

operator fun IntNanoseconds.times(scalar: Int) = this.toLong() * scalar

operator fun IntNanoseconds.times(scalar: Long) = this.toLong() * scalar

operator fun IntNanoseconds.div(scalar: Int) = this.toLong() / scalar

operator fun IntNanoseconds.div(scalar: Long) = this.toLong() / scalar

operator fun IntNanoseconds.rem(scalar: Int) = this.toLong() % scalar

operator fun IntNanoseconds.rem(scalar: Long) = this.toLong() % scalar

fun IntNanoseconds.toWholeDays() = (this.value / NANOSECONDS_PER_DAY.toInt()).days

fun IntNanoseconds.toWholeHours() = (this.value / NANOSECONDS_PER_HOUR.toInt()).hours

fun IntNanoseconds.toWholeMinutes() = (this.value / NANOSECONDS_PER_MINUTE.toInt()).minutes

fun IntNanoseconds.toWholeSeconds() = (this.value / NANOSECONDS_PER_SECOND.toInt()).seconds

fun IntNanoseconds.toWholeMilliseconds() = (this.value /
    NANOSECONDS_PER_MILLISECOND.toInt()).milliseconds

fun IntNanoseconds.toWholeMicroseconds() = (this.value /
    NANOSECONDS_PER_MICROSECOND.toInt()).microseconds

inline fun <T> IntNanoseconds.toComponents(action: (
  days: IntDays,
  hours: IntHours,
  minutes: IntMinutes,
  seconds: IntSeconds,
  milliseconds: IntMilliseconds,
  microseconds: IntMicroseconds,
  nanoseconds: IntNanoseconds
) -> T): T {
  val days = this.toWholeDays()
  val hours = (this - days).toInt().toWholeHours()
  val minutes = (this - days - hours).toInt().toWholeMinutes()
  val seconds = (this - days - hours - minutes).toInt().toWholeSeconds()
  val milliseconds = (this - days - hours - minutes - seconds).toInt().toWholeMilliseconds()
  val microseconds = (this - days - hours - minutes - seconds -
      milliseconds).toInt().toWholeMicroseconds()
  val nanoseconds = (this - days - hours - minutes - seconds - milliseconds - microseconds).toInt()
  return action(days, hours, minutes, seconds, milliseconds, microseconds, nanoseconds)
}

inline fun <T> IntNanoseconds.toComponents(action: (
  hours: IntHours,
  minutes: IntMinutes,
  seconds: IntSeconds,
  milliseconds: IntMilliseconds,
  microseconds: IntMicroseconds,
  nanoseconds: IntNanoseconds
) -> T): T {
  val hours = this.toWholeHours()
  val minutes = (this - hours).toInt().toWholeMinutes()
  val seconds = (this - hours - minutes).toInt().toWholeSeconds()
  val milliseconds = (this - hours - minutes - seconds).toInt().toWholeMilliseconds()
  val microseconds = (this - hours - minutes - seconds - milliseconds).toInt().toWholeMicroseconds()
  val nanoseconds = (this - hours - minutes - seconds - milliseconds - microseconds).toInt()
  return action(hours, minutes, seconds, milliseconds, microseconds, nanoseconds)
}

inline fun <T> IntNanoseconds.toComponents(action: (
  minutes: IntMinutes,
  seconds: IntSeconds,
  milliseconds: IntMilliseconds,
  microseconds: IntMicroseconds,
  nanoseconds: IntNanoseconds
) -> T): T {
  val minutes = this.toWholeMinutes()
  val seconds = (this - minutes).toInt().toWholeSeconds()
  val milliseconds = (this - minutes - seconds).toInt().toWholeMilliseconds()
  val microseconds = (this - minutes - seconds - milliseconds).toInt().toWholeMicroseconds()
  val nanoseconds = (this - minutes - seconds - milliseconds - microseconds).toInt()
  return action(minutes, seconds, milliseconds, microseconds, nanoseconds)
}

inline fun <T> IntNanoseconds.toComponents(action: (
  seconds: IntSeconds,
  milliseconds: IntMilliseconds,
  microseconds: IntMicroseconds,
  nanoseconds: IntNanoseconds
) -> T): T {
  val seconds = this.toWholeSeconds()
  val milliseconds = (this - seconds).toInt().toWholeMilliseconds()
  val microseconds = (this - seconds - milliseconds).toInt().toWholeMicroseconds()
  val nanoseconds = (this - seconds - milliseconds - microseconds).toInt()
  return action(seconds, milliseconds, microseconds, nanoseconds)
}

inline fun <T> IntNanoseconds.toComponents(action: (
  milliseconds: IntMilliseconds,
  microseconds: IntMicroseconds,
  nanoseconds: IntNanoseconds
) -> T): T {
  val milliseconds = this.toWholeMilliseconds()
  val microseconds = (this - milliseconds).toInt().toWholeMicroseconds()
  val nanoseconds = (this - milliseconds - microseconds).toInt()
  return action(milliseconds, microseconds, nanoseconds)
}

inline fun <T> IntNanoseconds.toComponents(action: (microseconds: IntMicroseconds,
    nanoseconds: IntNanoseconds) -> T): T {
  val microseconds = this.toWholeMicroseconds()
  val nanoseconds = (this - microseconds).toInt()
  return action(microseconds, nanoseconds)
}

operator fun LongNanoseconds.unaryPlus() = this

operator fun LongNanoseconds.unaryMinus() = LongNanoseconds(-value)

operator fun LongNanoseconds.plus(days: IntDays) = this + days.asNanoseconds()

operator fun LongNanoseconds.plus(days: LongDays) = this + days.asNanoseconds()

operator fun LongNanoseconds.plus(hours: IntHours) = this + hours.asNanoseconds()

operator fun LongNanoseconds.plus(hours: LongHours) = this + hours.asNanoseconds()

operator fun LongNanoseconds.plus(minutes: IntMinutes) = this + minutes.asNanoseconds()

operator fun LongNanoseconds.plus(minutes: LongMinutes) = this + minutes.asNanoseconds()

operator fun LongNanoseconds.plus(seconds: IntSeconds) = this + seconds.asNanoseconds()

operator fun LongNanoseconds.plus(seconds: LongSeconds) = this + seconds.asNanoseconds()

operator fun LongNanoseconds.plus(milliseconds: IntMilliseconds) = this +
    milliseconds.asNanoseconds()

operator fun LongNanoseconds.plus(milliseconds: LongMilliseconds) = this +
    milliseconds.asNanoseconds()

operator fun LongNanoseconds.plus(microseconds: IntMicroseconds) = this +
    microseconds.asNanoseconds()

operator fun LongNanoseconds.plus(microseconds: LongMicroseconds) = this +
    microseconds.asNanoseconds()

operator fun LongNanoseconds.plus(nanoseconds: IntNanoseconds) = LongNanoseconds(this.value +
    nanoseconds.value)

operator fun LongNanoseconds.plus(nanoseconds: LongNanoseconds) = LongNanoseconds(this.value +
    nanoseconds.value)

operator fun LongNanoseconds.minus(days: IntDays) = plus(-days)

operator fun LongNanoseconds.minus(days: LongDays) = plus(-days)

operator fun LongNanoseconds.minus(hours: IntHours) = plus(-hours)

operator fun LongNanoseconds.minus(hours: LongHours) = plus(-hours)

operator fun LongNanoseconds.minus(minutes: IntMinutes) = plus(-minutes)

operator fun LongNanoseconds.minus(minutes: LongMinutes) = plus(-minutes)

operator fun LongNanoseconds.minus(seconds: IntSeconds) = plus(-seconds)

operator fun LongNanoseconds.minus(seconds: LongSeconds) = plus(-seconds)

operator fun LongNanoseconds.minus(milliseconds: IntMilliseconds) = plus(-milliseconds)

operator fun LongNanoseconds.minus(milliseconds: LongMilliseconds) = plus(-milliseconds)

operator fun LongNanoseconds.minus(microseconds: IntMicroseconds) = plus(-microseconds)

operator fun LongNanoseconds.minus(microseconds: LongMicroseconds) = plus(-microseconds)

operator fun LongNanoseconds.minus(nanoseconds: IntNanoseconds) = plus(-nanoseconds)

operator fun LongNanoseconds.minus(nanoseconds: LongNanoseconds) = plus(-nanoseconds)

operator fun LongNanoseconds.times(scalar: Int) = LongNanoseconds(this.value * scalar)

operator fun LongNanoseconds.times(scalar: Long) = LongNanoseconds(this.value * scalar)

operator fun LongNanoseconds.div(scalar: Int) = LongNanoseconds(this.value / scalar)

operator fun LongNanoseconds.div(scalar: Long) = LongNanoseconds(this.value / scalar)

operator fun LongNanoseconds.rem(scalar: Int) = LongNanoseconds(this.value % scalar)

operator fun LongNanoseconds.rem(scalar: Long) = LongNanoseconds(this.value % scalar)

fun LongNanoseconds.toWholeDays() = (this.value / NANOSECONDS_PER_DAY).days

fun LongNanoseconds.toWholeHours() = (this.value / NANOSECONDS_PER_HOUR).hours

fun LongNanoseconds.toWholeMinutes() = (this.value / NANOSECONDS_PER_MINUTE).minutes

fun LongNanoseconds.toWholeSeconds() = (this.value / NANOSECONDS_PER_SECOND).seconds

fun LongNanoseconds.toWholeMilliseconds() = (this.value / NANOSECONDS_PER_MILLISECOND).milliseconds

fun LongNanoseconds.toWholeMicroseconds() = (this.value / NANOSECONDS_PER_MICROSECOND).microseconds

inline fun <T> LongNanoseconds.toComponents(action: (
  days: LongDays,
  hours: IntHours,
  minutes: IntMinutes,
  seconds: IntSeconds,
  milliseconds: IntMilliseconds,
  microseconds: IntMicroseconds,
  nanoseconds: IntNanoseconds
) -> T): T {
  val days = this.toWholeDays()
  val hours = (this - days).toInt().toWholeHours()
  val minutes = (this - days - hours).toInt().toWholeMinutes()
  val seconds = (this - days - hours - minutes).toInt().toWholeSeconds()
  val milliseconds = (this - days - hours - minutes - seconds).toInt().toWholeMilliseconds()
  val microseconds = (this - days - hours - minutes - seconds -
      milliseconds).toInt().toWholeMicroseconds()
  val nanoseconds = (this - days - hours - minutes - seconds - milliseconds - microseconds).toInt()
  return action(days, hours, minutes, seconds, milliseconds, microseconds, nanoseconds)
}

inline fun <T> LongNanoseconds.toComponents(action: (
  hours: LongHours,
  minutes: IntMinutes,
  seconds: IntSeconds,
  milliseconds: IntMilliseconds,
  microseconds: IntMicroseconds,
  nanoseconds: IntNanoseconds
) -> T): T {
  val hours = this.toWholeHours()
  val minutes = (this - hours).toInt().toWholeMinutes()
  val seconds = (this - hours - minutes).toInt().toWholeSeconds()
  val milliseconds = (this - hours - minutes - seconds).toInt().toWholeMilliseconds()
  val microseconds = (this - hours - minutes - seconds - milliseconds).toInt().toWholeMicroseconds()
  val nanoseconds = (this - hours - minutes - seconds - milliseconds - microseconds).toInt()
  return action(hours, minutes, seconds, milliseconds, microseconds, nanoseconds)
}

inline fun <T> LongNanoseconds.toComponents(action: (
  minutes: LongMinutes,
  seconds: IntSeconds,
  milliseconds: IntMilliseconds,
  microseconds: IntMicroseconds,
  nanoseconds: IntNanoseconds
) -> T): T {
  val minutes = this.toWholeMinutes()
  val seconds = (this - minutes).toInt().toWholeSeconds()
  val milliseconds = (this - minutes - seconds).toInt().toWholeMilliseconds()
  val microseconds = (this - minutes - seconds - milliseconds).toInt().toWholeMicroseconds()
  val nanoseconds = (this - minutes - seconds - milliseconds - microseconds).toInt()
  return action(minutes, seconds, milliseconds, microseconds, nanoseconds)
}

inline fun <T> LongNanoseconds.toComponents(action: (
  seconds: LongSeconds,
  milliseconds: IntMilliseconds,
  microseconds: IntMicroseconds,
  nanoseconds: IntNanoseconds
) -> T): T {
  val seconds = this.toWholeSeconds()
  val milliseconds = (this - seconds).toInt().toWholeMilliseconds()
  val microseconds = (this - seconds - milliseconds).toInt().toWholeMicroseconds()
  val nanoseconds = (this - seconds - milliseconds - microseconds).toInt()
  return action(seconds, milliseconds, microseconds, nanoseconds)
}

inline fun <T> LongNanoseconds.toComponents(action: (
  milliseconds: LongMilliseconds,
  microseconds: IntMicroseconds,
  nanoseconds: IntNanoseconds
) -> T): T {
  val milliseconds = this.toWholeMilliseconds()
  val microseconds = (this - milliseconds).toInt().toWholeMicroseconds()
  val nanoseconds = (this - milliseconds - microseconds).toInt()
  return action(milliseconds, microseconds, nanoseconds)
}

inline fun <T> LongNanoseconds.toComponents(action: (microseconds: LongMicroseconds,
    nanoseconds: IntNanoseconds) -> T): T {
  val microseconds = this.toWholeMicroseconds()
  val nanoseconds = (this - microseconds).toInt()
  return action(microseconds, nanoseconds)
}
