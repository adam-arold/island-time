//
// This file is auto-generated by 'tools:code-generator'
//
@file:JvmMultifileClass
@file:JvmName("YearsKt")

package io.islandtime.measures

import io.islandtime.internal.MONTHS_PER_YEAR
import io.islandtime.internal.YEARS_PER_CENTURY
import io.islandtime.internal.YEARS_PER_DECADE
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
inline class IntYears(
  val value: Int
) : Comparable<IntYears> {
  val inMonths: IntMonths
    get() = (this.value * MONTHS_PER_YEAR).months

  val inDecades: IntDecades
    get() = (this.value / YEARS_PER_DECADE).decades

  val inCenturies: IntCenturies
    get() = (this.value / YEARS_PER_CENTURY).centuries

  fun isZero(): Boolean = value == 0

  fun isNegative(): Boolean = value < 0

  fun isPositive(): Boolean = value > 0

  override fun compareTo(other: IntYears): Int = value.compareTo(other.value)

  override fun toString(): String = if (isZero()) {
      "P0Y"
  } else {
      buildString {
          if (isNegative()) { append('-') }
          append("P")
          append(value.absoluteValue)
          append('Y')
      }
  }
  fun inMonthsExact() = (this.value timesExact MONTHS_PER_YEAR).months

  companion object {
    val MIN: IntYears = IntYears(Int.MIN_VALUE)

    val MAX: IntYears = IntYears(Int.MAX_VALUE)
  }
}

@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class LongYears(
  val value: Long
) : Comparable<LongYears> {
  val inMonths: LongMonths
    get() = (this.value * MONTHS_PER_YEAR).months

  val inDecades: LongDecades
    get() = (this.value / YEARS_PER_DECADE).decades

  val inCenturies: LongCenturies
    get() = (this.value / YEARS_PER_CENTURY).centuries

  fun isZero(): Boolean = value == 0L

  fun isNegative(): Boolean = value < 0L

  fun isPositive(): Boolean = value > 0L

  override fun compareTo(other: LongYears): Int = value.compareTo(other.value)

  override fun toString(): String = if (isZero()) {
      "P0Y"
  } else {
      buildString {
          if (isNegative()) { append('-') }
          append("P")
          append(value.absoluteValue)
          append('Y')
      }
  }
  fun inMonthsExact() = (this.value timesExact MONTHS_PER_YEAR).months

  companion object {
    val MIN: LongYears = LongYears(Long.MIN_VALUE)

    val MAX: LongYears = LongYears(Long.MAX_VALUE)
  }
}

val Int.years: IntYears
  get() = IntYears(this)

val Long.years: LongYears
  get() = LongYears(this)
