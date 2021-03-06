//
// This file is auto-generated by 'tools:code-generator'
//
@file:JvmMultifileClass
@file:JvmName("DecadesKt")

package io.islandtime.measures

import io.islandtime.internal.DECADES_PER_CENTURY
import io.islandtime.internal.MONTHS_PER_DECADE
import io.islandtime.internal.YEARS_PER_DECADE
import io.islandtime.internal.minusExact
import io.islandtime.internal.negateExact
import io.islandtime.internal.plusExact
import io.islandtime.internal.timesExact
import io.islandtime.internal.toIntExact
import kotlin.Boolean
import kotlin.Comparable
import kotlin.Int
import kotlin.Long
import kotlin.PublishedApi
import kotlin.String
import kotlin.Suppress
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import kotlin.math.absoluteValue

/**
 * A number of decades.
 */
@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class IntDecades(
  /**
   * The underlying value.
   */
  val value: Int
) : Comparable<IntDecades> {
  /**
   * Get the absolute value.
   * @throws ArithmeticException if overflow occurs
   */
  val absoluteValue: IntDecades
    get() = if (value < 0) IntDecades(value.negateExact()) else this
  /**
   * Convert to months.
   * @throws ArithmeticException if overflow occurs
   */
  val inMonths: IntMonths
    get() = (value timesExact MONTHS_PER_DECADE).months

  /**
   * Convert to months without checking for overflow.
   */
  internal val inMonthsUnchecked: IntMonths
    get() = (value * MONTHS_PER_DECADE).months

  /**
   * Convert to years.
   * @throws ArithmeticException if overflow occurs
   */
  val inYears: IntYears
    get() = (value timesExact YEARS_PER_DECADE).years

  /**
   * Convert to years without checking for overflow.
   */
  internal val inYearsUnchecked: IntYears
    get() = (value * YEARS_PER_DECADE).years

  /**
   * Convert to whole centuries.
   */
  val inCenturies: IntCenturies
    get() = (value / DECADES_PER_CENTURY).centuries

  /**
   * Is this duration zero?
   */
  fun isZero(): Boolean = value == 0

  /**
   * Is this duration negative?
   */
  fun isNegative(): Boolean = value < 0

  /**
   * Is this duration positive?
   */
  fun isPositive(): Boolean = value > 0

  override fun compareTo(other: IntDecades): Int = value.compareTo(other.value)

  /**
   * Convert to an ISO-8601 time interval representation.
   */
  override fun toString(): String {
     return when {
       isZero() -> "P0Y"
       value == Int.MIN_VALUE -> "-P2147483648Y"
       else -> buildString {
         if (isNegative()) { append('-') }
         append("P")
         append(value.absoluteValue timesExact 10)
         append('Y')
       }
     }
  }

  /**
   * Negate the value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun unaryMinus() = IntDecades(value.negateExact())

  /**
   * Negate the value without checking for overflow.
   */
  internal fun negateUnchecked() = IntDecades(-value)

  /**
   * Multiply by a scalar value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun times(scalar: Int) = IntDecades(value timesExact scalar)

  /**
   * Multiply by a scalar value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun times(scalar: Long) = this.toLongDecades() * scalar

  /**
   * Divide by a scalar value.
   * @throws ArithmeticException if overflow occurs or the scalar is zero
   */
  operator fun div(scalar: Int): IntDecades {
     return if (scalar == -1) {
       -this
     } else {
       IntDecades(value / scalar)
     }
  }

  /**
   * Divide by a scalar value.
   * @throws ArithmeticException if the scalar is zero
   */
  operator fun div(scalar: Long): LongDecades = this.toLongDecades() / scalar
  operator fun rem(scalar: Int) = IntDecades(value % scalar)

  operator fun rem(scalar: Long) = this.toLongDecades() % scalar

  operator fun plus(months: IntMonths) = this.inMonths + months

  operator fun minus(months: IntMonths) = this.inMonths - months

  operator fun plus(months: LongMonths) = this.toLongDecades().inMonths + months

  operator fun minus(months: LongMonths) = this.toLongDecades().inMonths - months

  operator fun plus(years: IntYears) = this.inYears + years

  operator fun minus(years: IntYears) = this.inYears - years

  operator fun plus(years: LongYears) = this.toLongDecades().inYears + years

  operator fun minus(years: LongYears) = this.toLongDecades().inYears - years

  operator fun plus(decades: IntDecades) = IntDecades(value plusExact decades.value)

  operator fun minus(decades: IntDecades) = IntDecades(value minusExact decades.value)

  operator fun plus(decades: LongDecades) = LongDecades(value.toLong() plusExact decades.value)

  operator fun minus(decades: LongDecades) = LongDecades(value.toLong() minusExact decades.value)

  operator fun plus(centuries: IntCenturies) = this + centuries.inDecades

  operator fun minus(centuries: IntCenturies) = this - centuries.inDecades

  operator fun plus(centuries: LongCenturies) = this.toLongDecades() + centuries.inDecades

  operator fun minus(centuries: LongCenturies) = this.toLongDecades() - centuries.inDecades

  inline fun <T> toComponents(action: (centuries: IntCenturies, decades: IntDecades) -> T): T {
    val centuries = this.inCenturies
    val decades = (this - centuries)
    return action(centuries, decades)
  }

  /**
   * Convert to [LongDecades].
   */
  fun toLongDecades() = LongDecades(value.toLong())

  /**
   * Convert to a unit-less `Long` value.
   */
  fun toLong() = value.toLong()

  companion object {
    /**
     * The smallest supported value.
     */
    val MIN: IntDecades = IntDecades(Int.MIN_VALUE)

    /**
     * The largest supported value.
     */
    val MAX: IntDecades = IntDecades(Int.MAX_VALUE)
  }
}

/**
 * Convert to [IntDecades].
 */
val Int.decades: IntDecades
  get() = IntDecades(this)

/**
 * A number of decades.
 */
@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class LongDecades(
  /**
   * The underlying value.
   */
  val value: Long
) : Comparable<LongDecades> {
  /**
   * Get the absolute value.
   * @throws ArithmeticException if overflow occurs
   */
  val absoluteValue: LongDecades
    get() = if (value < 0) LongDecades(value.negateExact()) else this
  /**
   * Convert to months.
   * @throws ArithmeticException if overflow occurs
   */
  val inMonths: LongMonths
    get() = (value timesExact MONTHS_PER_DECADE).months

  /**
   * Convert to months without checking for overflow.
   */
  internal val inMonthsUnchecked: LongMonths
    get() = (value * MONTHS_PER_DECADE).months

  /**
   * Convert to years.
   * @throws ArithmeticException if overflow occurs
   */
  val inYears: LongYears
    get() = (value timesExact YEARS_PER_DECADE).years

  /**
   * Convert to years without checking for overflow.
   */
  internal val inYearsUnchecked: LongYears
    get() = (value * YEARS_PER_DECADE).years

  /**
   * Convert to whole centuries.
   */
  val inCenturies: LongCenturies
    get() = (value / DECADES_PER_CENTURY).centuries

  /**
   * Is this duration zero?
   */
  fun isZero(): Boolean = value == 0L

  /**
   * Is this duration negative?
   */
  fun isNegative(): Boolean = value < 0L

  /**
   * Is this duration positive?
   */
  fun isPositive(): Boolean = value > 0L

  override fun compareTo(other: LongDecades): Int = value.compareTo(other.value)

  /**
   * Convert to an ISO-8601 time interval representation.
   */
  override fun toString(): String {
     return when {
       isZero() -> "P0Y"
       value == Long.MIN_VALUE -> "-P9223372036854775808Y"
       else -> buildString {
         if (isNegative()) { append('-') }
         append("P")
         append(value.absoluteValue timesExact 10)
         append('Y')
       }
     }
  }

  /**
   * Negate the value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun unaryMinus() = LongDecades(value.negateExact())

  /**
   * Negate the value without checking for overflow.
   */
  internal fun negateUnchecked() = LongDecades(-value)

  /**
   * Multiply by a scalar value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun times(scalar: Int) = LongDecades(value timesExact scalar)

  /**
   * Multiply by a scalar value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun times(scalar: Long) = LongDecades(value timesExact scalar)

  /**
   * Divide by a scalar value.
   * @throws ArithmeticException if overflow occurs or the scalar is zero
   */
  operator fun div(scalar: Int): LongDecades {
     return if (scalar == -1) {
       -this
     } else {
       LongDecades(value / scalar)
     }
  }

  /**
   * Divide by a scalar value.
   * @throws ArithmeticException if overflow occurs or the scalar is zero
   */
  operator fun div(scalar: Long): LongDecades {
     return if (scalar == -1L) {
       -this
     } else {
       LongDecades(value / scalar)
     }
  }

  operator fun rem(scalar: Int) = LongDecades(value % scalar)

  operator fun rem(scalar: Long) = LongDecades(value % scalar)

  operator fun plus(months: IntMonths) = this.inMonths + months

  operator fun minus(months: IntMonths) = this.inMonths - months

  operator fun plus(months: LongMonths) = this.inMonths + months

  operator fun minus(months: LongMonths) = this.inMonths - months

  operator fun plus(years: IntYears) = this.inYears + years

  operator fun minus(years: IntYears) = this.inYears - years

  operator fun plus(years: LongYears) = this.inYears + years

  operator fun minus(years: LongYears) = this.inYears - years

  operator fun plus(decades: IntDecades) = LongDecades(value plusExact decades.value)

  operator fun minus(decades: IntDecades) = LongDecades(value minusExact decades.value)

  operator fun plus(decades: LongDecades) = LongDecades(value plusExact decades.value)

  operator fun minus(decades: LongDecades) = LongDecades(value minusExact decades.value)

  operator fun plus(centuries: IntCenturies) = this + centuries.inDecades

  operator fun minus(centuries: IntCenturies) = this - centuries.inDecades

  operator fun plus(centuries: LongCenturies) = this + centuries.inDecades

  operator fun minus(centuries: LongCenturies) = this - centuries.inDecades

  inline fun <T> toComponents(action: (centuries: LongCenturies, decades: IntDecades) -> T): T {
    val centuries = this.inCenturies
    val decades = (this - centuries).toIntDecadesUnchecked()
    return action(centuries, decades)
  }

  /**
   * Convert to [IntDecades].
   * @throws ArithmeticException if overflow occurs
   */
  fun toIntDecades() = IntDecades(value.toIntExact())

  /**
   * Convert to [IntDecades] without checking for overflow.
   */
  @PublishedApi
  internal fun toIntDecadesUnchecked() = IntDecades(value.toInt())

  /**
   * Convert to a unit-less `Int` value.
   * @throws ArithmeticException if overflow occurs
   */
  fun toInt() = value.toIntExact()

  /**
   * Convert to a unit-less `Int` value without checking for overflow.
   */
  internal fun toIntUnchecked() = value.toInt()

  companion object {
    /**
     * The smallest supported value.
     */
    val MIN: LongDecades = LongDecades(Long.MIN_VALUE)

    /**
     * The largest supported value.
     */
    val MAX: LongDecades = LongDecades(Long.MAX_VALUE)
  }
}

/**
 * Convert to [LongDecades].
 */
val Long.decades: LongDecades
  get() = LongDecades(this)
