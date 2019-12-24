package com.babo.ecc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import com.babo.utils.times

class Secp256k1Test {
    @Test
    fun initialPointOnTheCurve() {
        val x = FieldElement(Secp256k1.Gx, Secp256k1.p)
        val y = FieldElement(Secp256k1.Gy, Secp256k1.p)
        assertDoesNotThrow { Point(x, y, 0, 7) }
    }

    @Test
    fun testOrderOfCurve() {
        val x = FieldElement(Secp256k1.Gx, Secp256k1.p)
        val y = FieldElement(Secp256k1.Gy, Secp256k1.p)
        val p = Point(x, y, 0, 7)
        val inf = Point(null, null, 0, 7)
        assertEquals(inf, Secp256k1.n * p)
        //assertEquals(p, Secp256k1.n * p + p)
    }
}