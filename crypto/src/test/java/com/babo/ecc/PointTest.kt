package com.babo.ecc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import com.babo.utils.times

class PointTest {
    @Test
    fun testPointOnTheCurve() {
        val prime = 223L
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
            val x = FieldElement(it.first, prime)
            val y = FieldElement(it.second, prime)
            assertDoesNotThrow { Point(x, y, 0, 7) }
        }
        invalidPoints.forEach {
            val x = FieldElement(it.first, prime)
            val y = FieldElement(it.second, prime)
            assertThrows<Throwable> { Point(x, y, 0, 7) }
        }
    }

    @Test
    fun additionOfTwoDifferentPoints() {
        val p1 = Point(192, 105, 223, 0, 7)
        val p2 = Point(17, 56, 223, 0, 7)
        val p3 = p1 + p2
        assertEquals(Point(170, 142, 223L, 0, 7), p3)
    }

    @Test
    fun additionOfIdenticalPoints() {
        val prime = 223L
        val x1 = FieldElement(47, prime)
        val y1 = FieldElement(71, prime)

        val p1 = Point(x1, y1, 0, 7)
        val p3 = p1 + p1
        assertEquals(Point(36, 111, 223L, 0, 7), p3)
    }

    @Test
    fun scalarMultiplication() {
        val p = Point(47, 71, 223, 0, 7)
        assertEquals(Point(47, 71, 223L, 0, 7), p * 1)
        assertEquals(Point(36, 111, 223L, 0, 7), p * 2)
        assertEquals(Point(15, 137, 223L, 0, 7), p * 3)
        assertEquals(Point(194, 51, 223L, 0, 7), p * 4)
        assertEquals(Point(126, 96, 223L, 0, 7), p * 5)
        assertEquals(Point(139, 137, 223L, 0, 7), p * 6)
        assertEquals(Point(92, 47, 223L, 0, 7), p * 7)
        assertEquals(Point(116, 55, 223L, 0, 7), p * 8)
        assertEquals(Point(69, 86, 223L, 0, 7), p * 9)
        assertEquals(Point(154, 150, 223L, 0, 7), p * 10)
    }

    @Test
    fun testCommutative() {
        val p = Point(47, 71, 223, 0, 7)
        assertEquals(p * 23, 23 * p)
        assertEquals(p * 111, 111 * p)
        assertEquals(p * 3343, 3343 * p)
    }
}
