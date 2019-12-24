package com.babo.ecc

import java.math.BigInteger

data class FieldElement(var num: BigInteger, val prime: BigInteger) {
    init {
        require(BigInteger.ZERO <= num && num < prime)
    }

    operator fun plus(other: FieldElement): FieldElement {
        require(prime == other.prime)
        return FieldElement((num + other.num) % prime, prime)
    }

    operator fun plus(other: BigInteger): FieldElement {
        return FieldElement((num + other) % prime, prime)
    }

    operator fun minus(other: FieldElement): FieldElement {
        require(prime == other.prime)
        return FieldElement((prime + num - other.num) % prime, prime)
    }

    operator fun times(other: FieldElement): FieldElement {
        require(prime == other.prime)
        return FieldElement((num * other.num) % prime, prime)
    }

    operator fun times(other: BigInteger): FieldElement {
        return FieldElement((num * other) % prime, prime)
    }

    fun pow(exponent: BigInteger) = apply {
        var n = exponent % prime.subtract(BigInteger.ONE)
        if (n < BigInteger.ZERO) n += prime.subtract(BigInteger.ONE)
        num = this.pow(num, n)
    }

    private fun pow(base: BigInteger, exponent: BigInteger) : BigInteger = when {
        exponent == BigInteger.ZERO -> BigInteger.ONE
        exponent.mod(TWO) == BigInteger.ZERO -> pow(base, exponent.divide(TWO)).let { it * it % prime }
        else -> base * pow(base, exponent - BigInteger.ONE) % prime
    }

    operator fun div(other: FieldElement): FieldElement {
        require(prime == other.prime)
        val inverse = pow(other.num, prime - TWO)
        return FieldElement((num * inverse) % prime, prime)
    }

    companion object {
        private val TWO = BigInteger.valueOf(2L)
    }
}