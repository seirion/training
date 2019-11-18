package com.babo.ecc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class PointTest {
    @Test
    fun testPointOnTheCurve() {
        assertDoesNotThrow {
            Point(-1, -1, 5, 7)
        }
        assertThrows<IllegalArgumentException> {
            Point(-1, -2, 5, 7)
        }
    }

    @Test
    fun additionOfInf() {
        val p1 = Point(null, null, 5, 7)
        val p2 = Point(-1, -1, 5, 7)
        assertEquals(p2, p1 + p2)
    }
}
