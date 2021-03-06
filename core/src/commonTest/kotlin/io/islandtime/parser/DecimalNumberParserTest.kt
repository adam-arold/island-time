package io.islandtime.parser

import io.islandtime.base.DateTimeField
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class DecimalNumberParserTest {
    @Test
    fun `throws an exception if whole range is empty`() {
        assertFailsWith<IllegalArgumentException> {
            dateTimeParser { decimalNumber(wholeLength = IntRange.EMPTY) }
        }
    }

    @Test
    fun `throws an exception if fraction range is empty`() {
        assertFailsWith<IllegalArgumentException> {
            dateTimeParser { decimalNumber(fractionLength = IntRange.EMPTY) }
        }
    }

    @Test
    fun `throws an exception if whole range is outside of 1-19`() {
        assertFailsWith<IllegalArgumentException> {
            dateTimeParser { decimalNumber(wholeLength = 0..4) }
        }

        assertFailsWith<IllegalArgumentException> {
            dateTimeParser { decimalNumber(wholeLength = 5..20) }
        }
    }

    @Test
    fun `throws an exception if fraction range is outside of 0-9`() {
        assertFailsWith<IllegalArgumentException> {
            dateTimeParser { decimalNumber(fractionLength = -1..4) }
        }

        assertFailsWith<IllegalArgumentException> {
            dateTimeParser { decimalNumber(fractionLength = 5..10) }
        }
    }

    @Test
    fun `throws an exception if fraction scale is outside of 1-9`() {
        assertFailsWith<IllegalArgumentException> {
            dateTimeParser { decimalNumber(fractionScale = 0) }
        }

        assertFailsWith<IllegalArgumentException> {
            dateTimeParser { decimalNumber(fractionScale = 10) }
        }
    }

    @Test
    fun `parses decimal numbers into two components`() {
        val parser = dateTimeParser {
            decimalNumber {
                onParsed { whole, fraction ->
                    fields[DateTimeField.SECOND_OF_MINUTE] = whole
                    fields[DateTimeField.NANOSECOND_OF_SECOND] = fraction
                }
            }
        }

        val result1 = parser.parse("0.1")
        assertEquals(0L, result1.fields[DateTimeField.SECOND_OF_MINUTE])
        assertEquals(100_000_000L, result1.fields[DateTimeField.NANOSECOND_OF_SECOND])

        val result2 = parser.parse("-5.000000001")
        assertEquals(-5L, result2.fields[DateTimeField.SECOND_OF_MINUTE])
        assertEquals(-1L, result2.fields[DateTimeField.NANOSECOND_OF_SECOND])
    }

    @Test
    fun `enforces whole length`() {
        val parser = dateTimeParser {
            decimalNumber(wholeLength = 2..3)
        }

        assertFailsWith<DateTimeParseException> { parser.parse("4") }
        assertFailsWith<DateTimeParseException> { parser.parse("0.3") }
        assertFailsWith<DateTimeParseException> { parser.parse("4000.02") }
        assertFailsWith<DateTimeParseException> { parser.parse("-1.0004") }
    }

    @Test
    fun `enforces fraction length`() {
        val parser1 = dateTimeParser {
            decimalNumber(fractionLength = 1..3)
        }

        listOf(
            "45",
            "0.",
            ".",
            "0.0004",
            "0/0"
        ).forEach {
            assertFailsWith<DateTimeParseException> { parser1.parse(it) }
        }

        val parser2 = dateTimeParser {
            decimalNumber(fractionLength = 2..4)
        }

        assertFailsWith<DateTimeParseException> { parser2.parse("0.1") }
    }

    @Test
    fun `fractionScale controls the magnitude of the fractional part`() {
        val parser = dateTimeParser {
            decimalNumber(fractionScale = 3) {
                onParsed { _, fraction -> fields[DateTimeField.MILLISECOND_OF_SECOND] = fraction }
            }
        }

        assertEquals(300L, parser.parse("0.3").fields[DateTimeField.MILLISECOND_OF_SECOND])
    }

    @Test
    fun `reports an error when there are no characters to parse`() {
        val parser = dateTimeParser {
            +' '
            decimalNumber()
        }

        val exception = assertFailsWith<DateTimeParseException> { parser.parse(" ") }
        assertEquals(1, exception.errorIndex)
        assertEquals(" ", exception.parsedString)
    }

    @Test
    fun `throws an exception on overflow`() {
        val parser = dateTimeParser {
            +' '
            decimalNumber {
                onParsed { whole, _ ->
                    fields[DateTimeField.DURATION_OF_HOURS] = whole
                }
            }
        }

        listOf(
            " 9223372036854775808",
            " -9223372036854775809",
            " +9300000000000000000"
        ).forEach {
            val exception = assertFailsWith<DateTimeParseException> { parser.parse(it) }
            assertEquals(1, exception.errorIndex)
            assertEquals(it, exception.parsedString)
            assertTrue { exception.cause is ArithmeticException }
        }
    }
}