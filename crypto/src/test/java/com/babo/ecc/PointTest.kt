package com.babo.ecc

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
            assertDoesNotThrow {
                Point(x, y, 0, 7)
            }
        }
        invalidPoints.forEach {
            val xRaw = BigInteger.valueOf(it.first)
            val yRaw = BigInteger.valueOf(it.second)
            val x = FieldElement(xRaw, prime)
            val y = FieldElement(yRaw, prime)
            assertThrows<Throwable> { Point(x, y, 0, 7) }
        }
    }

}
