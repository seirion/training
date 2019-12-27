package com.babo.ecc

import com.babo.utils.isOdd
import java.math.BigInteger

data class FieldElement(var num: BigInteger, val prime: BigInteger) {

    constructor(num: Long, prime: Long) : this(BigInteger.valueOf(num), BigInteger.valueOf(prime))

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
        exponent.isOdd() -> base * pow(base, exponent - BigInteger.ONE) % prime
        else -> pow(base, exponent / TWO).let { it * it % prime }
    }

    operator fun div(other: FieldElement): FieldElement {
        require(prime == other.prime)
        return FieldElement((num * other.multiplicativeInverse()) % prime, prime)
    }

    fun isAdditionalInverseOf(other: FieldElement?) : Boolean {
        require(other == null || prime == other.prime)
        if (other == null) return false
        return (num + other.num) % prime == BigInteger.ZERO
    }

    fun multiplicativeInverse() = pow(num, prime - TWO)

    companion object {
        private val TWO = BigInteger.valueOf(2L)
    }
}