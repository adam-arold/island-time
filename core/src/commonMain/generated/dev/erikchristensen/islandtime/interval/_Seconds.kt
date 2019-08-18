//
// This file is auto-generated by 'tools:code-generator'
//
@file:JvmMultifileClass
@file:JvmName("SecondsKt")

package dev.erikchristensen.islandtime.interval

import dev.erikchristensen.islandtime.internal.SECONDS_PER_DAY
import dev.erikchristensen.islandtime.internal.SECONDS_PER_HOUR
import dev.erikchristensen.islandtime.internal.SECONDS_PER_MICROSECOND
import dev.erikchristensen.islandtime.internal.SECONDS_PER_MILLISECOND
import dev.erikchristensen.islandtime.internal.SECONDS_PER_MINUTE
import dev.erikchristensen.islandtime.internal.SECONDS_PER_NANOSECOND
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
inline class IntSeconds(
  val value: Int
) : Comparable<IntSeconds> {
  val isZero: Boolean
    get() = this.value == 0

  val isNegative: Boolean
    get() = this.value < 0

  val isPositive: Boolean
    get() = this.value > 0

  val absoluteValue: IntSeconds
    get() = IntSeconds(this.value.absoluteValue)

  override fun compareTo(other: IntSeconds): Int = this.value.compareTo(other.value)

  override fun toString(): String = if (this.isZero) {
      "PT0S"
  } else {
      buildString {
          append('P')
          append(value)
          append('S')
      }
  }

  companion object {
    val MIN: IntSeconds = IntSeconds(Int.MIN_VALUE)

    val MAX: IntSeconds = IntSeconds(Int.MAX_VALUE)
  }
}

@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class LongSeconds(
  val value: Long
) : Comparable<LongSeconds> {
  val isZero: Boolean
    get() = this.value == 0L

  val isNegative: Boolean
    get() = this.value < 0L

  val isPositive: Boolean
    get() = this.value > 0L

  val absoluteValue: LongSeconds
    get() = LongSeconds(this.value.absoluteValue)

  override fun compareTo(other: LongSeconds): Int = this.value.compareTo(other.value)

  override fun toString(): String = if (this.isZero) {
      "PT0S"
  } else {
      buildString {
          append('P')
          append(value)
          append('S')
      }
  }

  companion object {
    val MIN: LongSeconds = LongSeconds(Long.MIN_VALUE)

    val MAX: LongSeconds = LongSeconds(Long.MAX_VALUE)
  }
}

val Int.seconds: IntSeconds
  get() = IntSeconds(this)

val Long.seconds: LongSeconds
  get() = LongSeconds(this)

fun IntSeconds.toLong() = LongSeconds(this.value.toLong())

fun LongSeconds.toInt() = IntSeconds(this.value.toInt())

operator fun IntSeconds.unaryPlus() = this

operator fun IntSeconds.unaryMinus() = IntSeconds(-value)

operator fun IntSeconds.plus(days: IntDays) = this + days.asSeconds()

operator fun IntSeconds.plus(days: LongDays) = this.toLong() + days.asSeconds()

operator fun IntSeconds.plus(hours: IntHours) = this + hours.asSeconds()

operator fun IntSeconds.plus(hours: LongHours) = this.toLong() + hours.asSeconds()

operator fun IntSeconds.plus(minutes: IntMinutes) = this + minutes.asSeconds()

operator fun IntSeconds.plus(minutes: LongMinutes) = this.toLong() + minutes.asSeconds()

operator fun IntSeconds.plus(seconds: IntSeconds) = IntSeconds(this.value + seconds.value)

operator fun IntSeconds.plus(seconds: LongSeconds) = LongSeconds(this.value.toLong() +
    seconds.value)

operator fun IntSeconds.plus(milliseconds: IntMilliseconds) = this.asMilliseconds() + milliseconds

operator fun IntSeconds.plus(milliseconds: LongMilliseconds) = this.toLong().asMilliseconds() +
    milliseconds

operator fun IntSeconds.plus(microseconds: IntMicroseconds) = this.asMicroseconds() + microseconds

operator fun IntSeconds.plus(microseconds: LongMicroseconds) = this.toLong().asMicroseconds() +
    microseconds

operator fun IntSeconds.plus(nanoseconds: IntNanoseconds) = this.asNanoseconds() + nanoseconds

operator fun IntSeconds.plus(nanoseconds: LongNanoseconds) = this.toLong().asNanoseconds() +
    nanoseconds

operator fun IntSeconds.minus(days: IntDays) = plus(-days)

operator fun IntSeconds.minus(days: LongDays) = plus(-days)

operator fun IntSeconds.minus(hours: IntHours) = plus(-hours)

operator fun IntSeconds.minus(hours: LongHours) = plus(-hours)

operator fun IntSeconds.minus(minutes: IntMinutes) = plus(-minutes)

operator fun IntSeconds.minus(minutes: LongMinutes) = plus(-minutes)

operator fun IntSeconds.minus(seconds: IntSeconds) = plus(-seconds)

operator fun IntSeconds.minus(seconds: LongSeconds) = plus(-seconds)

operator fun IntSeconds.minus(milliseconds: IntMilliseconds) = plus(-milliseconds)

operator fun IntSeconds.minus(milliseconds: LongMilliseconds) = plus(-milliseconds)

operator fun IntSeconds.minus(microseconds: IntMicroseconds) = plus(-microseconds)

operator fun IntSeconds.minus(microseconds: LongMicroseconds) = plus(-microseconds)

operator fun IntSeconds.minus(nanoseconds: IntNanoseconds) = plus(-nanoseconds)

operator fun IntSeconds.minus(nanoseconds: LongNanoseconds) = plus(-nanoseconds)

operator fun IntSeconds.times(scalar: Int) = IntSeconds(this.value * scalar)

operator fun IntSeconds.times(scalar: Long) = this.toLong() * scalar

operator fun IntSeconds.div(scalar: Int) = IntSeconds(this.value / scalar)

operator fun IntSeconds.div(scalar: Long) = this.toLong() / scalar

operator fun IntSeconds.rem(scalar: Int) = IntSeconds(this.value % scalar)

operator fun IntSeconds.rem(scalar: Long) = this.toLong() % scalar

fun IntSeconds.toWholeDays() = (this.value / SECONDS_PER_DAY.toInt()).days

fun IntSeconds.toWholeHours() = (this.value / SECONDS_PER_HOUR.toInt()).hours

fun IntSeconds.toWholeMinutes() = (this.value / SECONDS_PER_MINUTE.toInt()).minutes

fun IntSeconds.asMilliseconds() = (this.value.toLong() *
    SECONDS_PER_MILLISECOND.toInt()).milliseconds

fun IntSeconds.asMicroseconds() = (this.value.toLong() *
    SECONDS_PER_MICROSECOND.toInt()).microseconds

fun IntSeconds.asNanoseconds() = (this.value.toLong() * SECONDS_PER_NANOSECOND.toInt()).nanoseconds

inline fun <T> IntSeconds.toComponents(action: (
  days: IntDays,
  hours: IntHours,
  minutes: IntMinutes,
  seconds: IntSeconds
) -> T): T {
  val days = this.toWholeDays()
  val hours = (this - days).toWholeHours()
  val minutes = (this - days - hours).toWholeMinutes()
  val seconds = (this - days - hours - minutes)
  return action(days, hours, minutes, seconds)
}

inline fun <T> IntSeconds.toComponents(action: (
  hours: IntHours,
  minutes: IntMinutes,
  seconds: IntSeconds
) -> T): T {
  val hours = this.toWholeHours()
  val minutes = (this - hours).toWholeMinutes()
  val seconds = (this - hours - minutes)
  return action(hours, minutes, seconds)
}

inline fun <T> IntSeconds.toComponents(action: (minutes: IntMinutes, seconds: IntSeconds) -> T): T {
  val minutes = this.toWholeMinutes()
  val seconds = (this - minutes)
  return action(minutes, seconds)
}

operator fun LongSeconds.unaryPlus() = this

operator fun LongSeconds.unaryMinus() = LongSeconds(-value)

operator fun LongSeconds.plus(days: IntDays) = this + days.asSeconds()

operator fun LongSeconds.plus(days: LongDays) = this + days.asSeconds()

operator fun LongSeconds.plus(hours: IntHours) = this + hours.asSeconds()

operator fun LongSeconds.plus(hours: LongHours) = this + hours.asSeconds()

operator fun LongSeconds.plus(minutes: IntMinutes) = this + minutes.asSeconds()

operator fun LongSeconds.plus(minutes: LongMinutes) = this + minutes.asSeconds()

operator fun LongSeconds.plus(seconds: IntSeconds) = LongSeconds(this.value + seconds.value)

operator fun LongSeconds.plus(seconds: LongSeconds) = LongSeconds(this.value + seconds.value)

operator fun LongSeconds.plus(milliseconds: IntMilliseconds) = this.asMilliseconds() + milliseconds

operator fun LongSeconds.plus(milliseconds: LongMilliseconds) = this.asMilliseconds() + milliseconds

operator fun LongSeconds.plus(microseconds: IntMicroseconds) = this.asMicroseconds() + microseconds

operator fun LongSeconds.plus(microseconds: LongMicroseconds) = this.asMicroseconds() + microseconds

operator fun LongSeconds.plus(nanoseconds: IntNanoseconds) = this.asNanoseconds() + nanoseconds

operator fun LongSeconds.plus(nanoseconds: LongNanoseconds) = this.asNanoseconds() + nanoseconds

operator fun LongSeconds.minus(days: IntDays) = plus(-days)

operator fun LongSeconds.minus(days: LongDays) = plus(-days)

operator fun LongSeconds.minus(hours: IntHours) = plus(-hours)

operator fun LongSeconds.minus(hours: LongHours) = plus(-hours)

operator fun LongSeconds.minus(minutes: IntMinutes) = plus(-minutes)

operator fun LongSeconds.minus(minutes: LongMinutes) = plus(-minutes)

operator fun LongSeconds.minus(seconds: IntSeconds) = plus(-seconds)

operator fun LongSeconds.minus(seconds: LongSeconds) = plus(-seconds)

operator fun LongSeconds.minus(milliseconds: IntMilliseconds) = plus(-milliseconds)

operator fun LongSeconds.minus(milliseconds: LongMilliseconds) = plus(-milliseconds)

operator fun LongSeconds.minus(microseconds: IntMicroseconds) = plus(-microseconds)

operator fun LongSeconds.minus(microseconds: LongMicroseconds) = plus(-microseconds)

operator fun LongSeconds.minus(nanoseconds: IntNanoseconds) = plus(-nanoseconds)

operator fun LongSeconds.minus(nanoseconds: LongNanoseconds) = plus(-nanoseconds)

operator fun LongSeconds.times(scalar: Int) = LongSeconds(this.value * scalar)

operator fun LongSeconds.times(scalar: Long) = LongSeconds(this.value * scalar)

operator fun LongSeconds.div(scalar: Int) = LongSeconds(this.value / scalar)

operator fun LongSeconds.div(scalar: Long) = LongSeconds(this.value / scalar)

operator fun LongSeconds.rem(scalar: Int) = LongSeconds(this.value % scalar)

operator fun LongSeconds.rem(scalar: Long) = LongSeconds(this.value % scalar)

fun LongSeconds.toWholeDays() = (this.value / SECONDS_PER_DAY).days

fun LongSeconds.toWholeHours() = (this.value / SECONDS_PER_HOUR).hours

fun LongSeconds.toWholeMinutes() = (this.value / SECONDS_PER_MINUTE).minutes

fun LongSeconds.asMilliseconds() = (this.value * SECONDS_PER_MILLISECOND).milliseconds

fun LongSeconds.asMicroseconds() = (this.value * SECONDS_PER_MICROSECOND).microseconds

fun LongSeconds.asNanoseconds() = (this.value * SECONDS_PER_NANOSECOND).nanoseconds

inline fun <T> LongSeconds.toComponents(action: (
  days: LongDays,
  hours: IntHours,
  minutes: IntMinutes,
  seconds: IntSeconds
) -> T): T {
  val days = this.toWholeDays()
  val hours = (this - days).toInt().toWholeHours()
  val minutes = (this - days - hours).toInt().toWholeMinutes()
  val seconds = (this - days - hours - minutes).toInt()
  return action(days, hours, minutes, seconds)
}

inline fun <T> LongSeconds.toComponents(action: (
  hours: LongHours,
  minutes: IntMinutes,
  seconds: IntSeconds
) -> T): T {
  val hours = this.toWholeHours()
  val minutes = (this - hours).toInt().toWholeMinutes()
  val seconds = (this - hours - minutes).toInt()
  return action(hours, minutes, seconds)
}

inline fun <T> LongSeconds.toComponents(action: (minutes: LongMinutes, seconds: IntSeconds) -> T):
    T {
  val minutes = this.toWholeMinutes()
  val seconds = (this - minutes).toInt()
  return action(minutes, seconds)
}
