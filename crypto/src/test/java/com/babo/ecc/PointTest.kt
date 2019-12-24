package com.babo.ecc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.math.BigInteger

class PointTest {
    @Test
    fun testPointOnTheCurve() {
        val prime = BigInteger.valueOf(223L)
        val validPoints = listOf(
                192L to 105L,
                17L to 56L,
                1L to 193L
        )
        val invalidPoints = listOf(
                200L to 119L,
                42L to 99L
        )

        validPoints.forEach {
            val xRaw = BigInteger.valueOf(it.first)
            val yRaw = BigInteger.valueOf(it.second)
            val x = FieldElement(xRaw, prime)
            val y = FieldElement(yRaw, prime)
            assertDoesNotThrow { Point(x, y, 0, 7) }
        }
        invalidPoints.forEach {
            val xRaw = BigInteger.valueOf(it.first)
            val yRaw = BigInteger.valueOf(it.second)
            val x = FieldElement(xRaw, prime)
            val y = FieldElement(yRaw, prime)
            assertThrows<Throwable> { Point(x, y, 0, 7) }
        }
    }

    @Test
    fun additionOfTwoDifferentPoints() {
        val p1 = pointOf(192, 105, 223, 0, 7)
        val p2 = pointOf(17, 56, 223, 0, 7)
        val p3 = p1 + p2
        assertEquals(pointOf(170, 142, 223L, 0, 7), p3)
    }

    @Test
    fun additionOfIdenticalPoints() {
        val prime = BigInteger.valueOf(223L)
        val x1 = FieldElement(BigInteger.valueOf(47L), prime)
        val y1 = FieldElement(BigInteger.valueOf(71L), prime)

        val p1 = Point(x1, y1, 0, 7)
        val p3 = p1 + p1
        assertEquals(pointOf(36, 111, 223L, 0, 7), p3)
    }

    private fun pointOf(x: Long, y: Long, prime: Long, a: Int, b: Int): Point {
        val bigPrime = BigInteger.valueOf(prime)
        return Point(
                FieldElement(BigInteger.valueOf(x), bigPrime),
                FieldElement(BigInteger.valueOf(y), bigPrime),
                a, b
        )
    }
}
