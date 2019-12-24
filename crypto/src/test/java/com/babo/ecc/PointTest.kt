package com.babo.ecc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.math.BigInteger
import com.babo.utils.times

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

    @Test
    fun scalarMultiplication() {
        val p = pointOf(47, 71, 223, 0, 7)
        assertEquals(pointOf(47, 71, 223L, 0, 7), p * 1)
        assertEquals(pointOf(36, 111, 223L, 0, 7), p * 2)
        assertEquals(pointOf(15, 137, 223L, 0, 7), p * 3)
        assertEquals(pointOf(194, 51, 223L, 0, 7), p * 4)
        assertEquals(pointOf(126, 96, 223L, 0, 7), p * 5)
        assertEquals(pointOf(139, 137, 223L, 0, 7), p * 6)
        assertEquals(pointOf(92, 47, 223L, 0, 7), p * 7)
        assertEquals(pointOf(116, 55, 223L, 0, 7), p * 8)
        assertEquals(pointOf(69, 86, 223L, 0, 7), p * 9)
        assertEquals(pointOf(154, 150, 223L, 0, 7), p * 10)
    }

    @Test
    fun testCommutative() {
        val p = pointOf(47, 71, 223, 0, 7)
        assertEquals(p * 23, 23 * p)
        assertEquals(p * 111, 111 * p)
        assertEquals(p * 3343, 3343 * p)
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
