package com.babo.ecc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class FieldElementTest {

    @Test
    fun filedEquality() {
        val a = FieldElement(7, 13)
        val b = FieldElement(6, 13)
        assertNotEquals(a, b)
        assertEquals(a, a)
    }

    @Test
    fun addition() {
        val a = FieldElement(7, 13)
        val b = FieldElement(12, 13)
        val c = FieldElement(6, 13)
        assertEquals(c , a + b)
        assertEquals(c , a.add(b))
    }

    @Test
    fun subtract() {
        val a = FieldElement(7, 13)
        val b = FieldElement(12, 13)
        val c = FieldElement(8, 13)
        assertEquals(c , a - b)
        assertEquals(c , a.subtract(b))
    }

    @Test
    fun multiply() {
        val a = FieldElement(3, 13)
        val b = FieldElement(12, 13)
        val c = FieldElement(10, 13)
        assertEquals(c , a * b)
        assertEquals(c , a.multiply(b))
    }

    @Test
    fun power() {
        val a = FieldElement(3, 13)
        val b = FieldElement(1, 13)
        assertEquals(b , a.pow(3))

        val c = FieldElement(7, 13)
        val d = FieldElement(8, 13)
        assertEquals(d , c.pow(-3))
    }

    @Test
    fun perma_little_theorem() {
        val primes = arrayListOf(7, 11, 17, 31)
        primes.forEach { prime ->
            val one = FieldElement(1, prime)
            (1 until prime).forEach {
                assertEquals(one, FieldElement(it, prime).pow(prime - 1))
            }
        }
    }

    @Test
    fun division() {
        val a = FieldElement(9, 13)
        val b = FieldElement(3, 13)
        val c = FieldElement(3, 13)
        val d = FieldElement(7, 13)
        val e = FieldElement(5, 13)
        assertEquals(c , a / b)
        assertEquals(e , a / d)
    }

}