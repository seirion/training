package com.babo.ecc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import java.math.BigInteger

class PointTest {
    @Test
    fun testPointOnTheCurve() {
        assertDoesNotThrow {
            Point(Big_M1, Big_M1, 5, 7)
        }
        assertThrows<IllegalArgumentException> {
            Point(Big_M1, Big_M2, 5, 7)
        }
    }

    @Test
    fun additionOfInf() {
        val p1 = Point(null, null, 5, 7)
        val p2 = Point(Big_M1, Big_M1, 5, 7)
        assertEquals(p2, p1 + p2)
    }

    @Test
    fun additionalInverse() {
        val p1 = Point(Big_M1, Big1, 5, 7)
        val p2 = Point(Big_M1, Big_M1, 5, 7)
        assertEquals(Point(null, null, 5, 7), p1 + p2)
    }

    companion object {
        private val Big_M1 = BigInteger.valueOf(-1L)
        private val Big_M2 = BigInteger.valueOf(-2L)
        private val Big1 = BigInteger.ONE
    }
}
