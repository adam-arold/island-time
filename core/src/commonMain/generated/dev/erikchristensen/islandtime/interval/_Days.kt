//
// This file is auto-generated by 'tools:code-generator'
//
@file:JvmMultifileClass
@file:JvmName("DaysKt")

package dev.erikchristensen.islandtime.interval

import dev.erikchristensen.islandtime.internal.DAYS_PER_HOUR
import dev.erikchristensen.islandtime.internal.DAYS_PER_MICROSECOND
import dev.erikchristensen.islandtime.internal.DAYS_PER_MILLISECOND
import dev.erikchristensen.islandtime.internal.DAYS_PER_MINUTE
import dev.erikchristensen.islandtime.internal.DAYS_PER_NANOSECOND
import dev.erikchristensen.islandtime.internal.DAYS_PER_SECOND
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
  val isZero: Boolean
    get() = this.value == 0

  val isNegative: Boolean
    get() = this.value < 0

  val isPositive: Boolean
    get() = this.value > 0

  val absoluteValue: IntDays
    get() = IntDays(this.value.absoluteValue)

  override fun compareTo(other: IntDays): Int = this.value.compareTo(other.value)

  override fun toString(): String = if (this.isZero) {
      "P0D"
  } else {
      buildString {
          append("P")
          append(value)
          append('D')
      }
  }

  companion object {
    val MIN: IntDays = IntDays(Int.MIN_VALUE)

    val MAX: IntDays = IntDays(Int.MAX_VALUE)
  }
}

@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class LongDays(
  val value: Long
) : Comparable<LongDays> {
  val isZero: Boolean
    get() = this.value == 0L

  val isNegative: Boolean
    get() = this.value < 0L

  val isPositive: Boolean
    get() = this.value > 0L

  val absoluteValue: LongDays
    get() = LongDays(this.value.absoluteValue)

  override fun compareTo(other: LongDays): Int = this.value.compareTo(other.value)

  override fun toString(): String = if (this.isZero) {
      "P0D"
  } else {
      buildString {
          append("P")
          append(value)
          append('D')
      }
  }

  companion object {
    val MIN: LongDays = LongDays(Long.MIN_VALUE)

    val MAX: LongDays = LongDays(Long.MAX_VALUE)
  }
}

val Int.days: IntDays
  get() = IntDays(this)

val Long.days: LongDays
  get() = LongDays(this)

fun IntDays.toLong() = LongDays(this.value.toLong())

fun LongDays.toInt() = IntDays(this.value.toInt())

operator fun IntDays.unaryPlus() = this

operator fun IntDays.unaryMinus() = IntDays(-value)

operator fun IntDays.plus(days: IntDays) = IntDays(this.value + days.value)

operator fun IntDays.plus(days: LongDays) = LongDays(this.value.toLong() + days.value)

operator fun IntDays.plus(hours: IntHours) = this.asHours() + hours

operator fun IntDays.plus(hours: LongHours) = this.toLong().asHours() + hours

operator fun IntDays.plus(minutes: IntMinutes) = this.asMinutes() + minutes

operator fun IntDays.plus(minutes: LongMinutes) = this.toLong().asMinutes() + minutes

operator fun IntDays.plus(seconds: IntSeconds) = this.asSeconds() + seconds

operator fun IntDays.plus(seconds: LongSeconds) = this.toLong().asSeconds() + seconds

operator fun IntDays.plus(milliseconds: IntMilliseconds) = this.asMilliseconds() + milliseconds

operator fun IntDays.plus(milliseconds: LongMilliseconds) = this.toLong().asMilliseconds() +
    milliseconds

operator fun IntDays.plus(microseconds: IntMicroseconds) = this.asMicroseconds() + microseconds

operator fun IntDays.plus(microseconds: LongMicroseconds) = this.toLong().asMicroseconds() +
    microseconds

operator fun IntDays.plus(nanoseconds: IntNanoseconds) = this.asNanoseconds() + nanoseconds

operator fun IntDays.plus(nanoseconds: LongNanoseconds) = this.toLong().asNanoseconds() +
    nanoseconds

operator fun IntDays.minus(days: IntDays) = plus(-days)

operator fun IntDays.minus(days: LongDays) = plus(-days)

operator fun IntDays.minus(hours: IntHours) = plus(-hours)

operator fun IntDays.minus(hours: LongHours) = plus(-hours)

operator fun IntDays.minus(minutes: IntMinutes) = plus(-minutes)

operator fun IntDays.minus(minutes: LongMinutes) = plus(-minutes)

operator fun IntDays.minus(seconds: IntSeconds) = plus(-seconds)

operator fun IntDays.minus(seconds: LongSeconds) = plus(-seconds)

operator fun IntDays.minus(milliseconds: IntMilliseconds) = plus(-milliseconds)

operator fun IntDays.minus(milliseconds: LongMilliseconds) = plus(-milliseconds)

operator fun IntDays.minus(microseconds: IntMicroseconds) = plus(-microseconds)

operator fun IntDays.minus(microseconds: LongMicroseconds) = plus(-microseconds)

operator fun IntDays.minus(nanoseconds: IntNanoseconds) = plus(-nanoseconds)

operator fun IntDays.minus(nanoseconds: LongNanoseconds) = plus(-nanoseconds)

operator fun IntDays.times(scalar: Int) = IntDays(this.value * scalar)

operator fun IntDays.times(scalar: Long) = this.toLong() * scalar

operator fun IntDays.div(scalar: Int) = IntDays(this.value / scalar)

operator fun IntDays.div(scalar: Long) = this.toLong() / scalar

operator fun IntDays.rem(scalar: Int) = IntDays(this.value % scalar)

operator fun IntDays.rem(scalar: Long) = this.toLong() % scalar

fun IntDays.asHours() = (this.value * DAYS_PER_HOUR.toInt()).hours

fun IntDays.asMinutes() = (this.value * DAYS_PER_MINUTE.toInt()).minutes

fun IntDays.asSeconds() = (this.value * DAYS_PER_SECOND.toInt()).seconds

fun IntDays.asMilliseconds() = (this.value.toLong() * DAYS_PER_MILLISECOND.toInt()).milliseconds

fun IntDays.asMicroseconds() = (this.value.toLong() * DAYS_PER_MICROSECOND.toInt()).microseconds

fun IntDays.asNanoseconds() = (this.value.toLong() * DAYS_PER_NANOSECOND.toInt()).nanoseconds

operator fun LongDays.unaryPlus() = this

operator fun LongDays.unaryMinus() = LongDays(-value)

operator fun LongDays.plus(days: IntDays) = LongDays(this.value + days.value)

operator fun LongDays.plus(days: LongDays) = LongDays(this.value + days.value)

operator fun LongDays.plus(hours: IntHours) = this.asHours() + hours

operator fun LongDays.plus(hours: LongHours) = this.asHours() + hours

operator fun LongDays.plus(minutes: IntMinutes) = this.asMinutes() + minutes

operator fun LongDays.plus(minutes: LongMinutes) = this.asMinutes() + minutes

operator fun LongDays.plus(seconds: IntSeconds) = this.asSeconds() + seconds

operator fun LongDays.plus(seconds: LongSeconds) = this.asSeconds() + seconds

operator fun LongDays.plus(milliseconds: IntMilliseconds) = this.asMilliseconds() + milliseconds

operator fun LongDays.plus(milliseconds: LongMilliseconds) = this.asMilliseconds() + milliseconds

operator fun LongDays.plus(microseconds: IntMicroseconds) = this.asMicroseconds() + microseconds

operator fun LongDays.plus(microseconds: LongMicroseconds) = this.asMicroseconds() + microseconds

operator fun LongDays.plus(nanoseconds: IntNanoseconds) = this.asNanoseconds() + nanoseconds

operator fun LongDays.plus(nanoseconds: LongNanoseconds) = this.asNanoseconds() + nanoseconds

operator fun LongDays.minus(days: IntDays) = plus(-days)

operator fun LongDays.minus(days: LongDays) = plus(-days)

operator fun LongDays.minus(hours: IntHours) = plus(-hours)

operator fun LongDays.minus(hours: LongHours) = plus(-hours)

operator fun LongDays.minus(minutes: IntMinutes) = plus(-minutes)

operator fun LongDays.minus(minutes: LongMinutes) = plus(-minutes)

operator fun LongDays.minus(seconds: IntSeconds) = plus(-seconds)

operator fun LongDays.minus(seconds: LongSeconds) = plus(-seconds)

operator fun LongDays.minus(milliseconds: IntMilliseconds) = plus(-milliseconds)

operator fun LongDays.minus(milliseconds: LongMilliseconds) = plus(-milliseconds)

operator fun LongDays.minus(microseconds: IntMicroseconds) = plus(-microseconds)

operator fun LongDays.minus(microseconds: LongMicroseconds) = plus(-microseconds)

operator fun LongDays.minus(nanoseconds: IntNanoseconds) = plus(-nanoseconds)

operator fun LongDays.minus(nanoseconds: LongNanoseconds) = plus(-nanoseconds)

operator fun LongDays.times(scalar: Int) = LongDays(this.value * scalar)

operator fun LongDays.times(scalar: Long) = LongDays(this.value * scalar)

operator fun LongDays.div(scalar: Int) = LongDays(this.value / scalar)

operator fun LongDays.div(scalar: Long) = LongDays(this.value / scalar)

operator fun LongDays.rem(scalar: Int) = LongDays(this.value % scalar)

operator fun LongDays.rem(scalar: Long) = LongDays(this.value % scalar)

fun LongDays.asHours() = (this.value * DAYS_PER_HOUR).hours

fun LongDays.asMinutes() = (this.value * DAYS_PER_MINUTE).minutes

fun LongDays.asSeconds() = (this.value * DAYS_PER_SECOND).seconds

fun LongDays.asMilliseconds() = (this.value * DAYS_PER_MILLISECOND).milliseconds

fun LongDays.asMicroseconds() = (this.value * DAYS_PER_MICROSECOND).microseconds

fun LongDays.asNanoseconds() = (this.value * DAYS_PER_NANOSECOND).nanoseconds
