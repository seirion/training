package com.babo.ecc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger

class FieldElementTest {

    @Test
    fun filedEquality() {
        val a = FieldElement(BigInteger.valueOf(7), BigInteger.valueOf(13))
        val b = FieldElement(BigInteger.valueOf(6), BigInteger.valueOf(13))
        assertNotEquals(a, b)
        assertEquals(a, a)
    }

    @Test
    fun addition() {
        val a = FieldElement(BigInteger.valueOf(7), BigInteger.valueOf(13))
        val b = FieldElement(BigInteger.valueOf(12), BigInteger.valueOf(13))
        val c = FieldElement(BigInteger.valueOf(6), BigInteger.valueOf(13))
        assertEquals(c , a + b)
    }

    @Test
    fun subtract() {
        val a = FieldElement(BigInteger.valueOf(7), BigInteger.valueOf(13))
        val b = FieldElement(BigInteger.valueOf(12), BigInteger.valueOf(13))
        val c = FieldElement(BigInteger.valueOf(8), BigInteger.valueOf(13))
        assertEquals(c , a - b)
    }

    @Test
    fun multiply() {
        val a = FieldElement(BigInteger.valueOf(3), BigInteger.valueOf(13))
        val b = FieldElement(BigInteger.valueOf(12), BigInteger.valueOf(13))
        val c = FieldElement(BigInteger.valueOf(10), BigInteger.valueOf(13))
        assertEquals(c , a * b)
    }

    @Test
    fun power() {
        val a = FieldElement(BigInteger.valueOf(3), BigInteger.valueOf(13))
        val b = FieldElement(BigInteger.valueOf(1), BigInteger.valueOf(13))
        assertEquals(b , a.pow(BigInteger.valueOf(3)))

        val c = FieldElement(BigInteger.valueOf(7), BigInteger.valueOf(13))
        val d = FieldElement(BigInteger.valueOf(8), BigInteger.valueOf(13))
        assertEquals(d , c.pow(BigInteger.valueOf(-3)))
    }

    @Test
    fun perma_little_theorem() {
        val primes = arrayListOf(
                BigInteger.valueOf(7),
                BigInteger.valueOf(11),
                BigInteger.valueOf(17),
                BigInteger.valueOf(31))
        primes.forEach { prime ->
            val one = FieldElement(BigInteger.ONE, prime)
            (1 until prime.toLong()).map { BigInteger.valueOf(it) }. forEach {
                assertEquals(one, FieldElement(it, prime).pow(prime - BigInteger.ONE))
            }
        }
    }

    @Test
    fun division() {
        val a = FieldElement(BigInteger.valueOf(9), BigInteger.valueOf(13))
        val b = FieldElement(BigInteger.valueOf(3), BigInteger.valueOf(13))
        val c = FieldElement(BigInteger.valueOf(3), BigInteger.valueOf(13))
        val d = FieldElement(BigInteger.valueOf(7), BigInteger.valueOf(13))
        val e = FieldElement(BigInteger.valueOf(5), BigInteger.valueOf(13))
        assertEquals(c , a / b)
        assertEquals(e , a / d)
    }

}