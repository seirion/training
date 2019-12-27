package com.babo.ecc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import com.babo.utils.times
import org.junit.jupiter.api.Assertions.assertTrue

class Secp256k1Test {

    @Test
    fun testOrderOfCurve() {
        val inf = Point(null, null, 0, 7)
        assertEquals(inf, Secp256k1.n * Secp256k1.G)
    }

    @Test
    fun signAndVerify() {
        val pk = Secp256k1()
        val msg = "this is the original message !!!"
        val signature = pk.sign(msg.toByteArray())
        println(signature)
        assertTrue(pk.pubKey.verify(msg.toByteArray(), signature))
    }
}