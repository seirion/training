package com.babo.app

data class FieldElement(var num: Int, val prime: Int) {
    init {
        require(num in 0 until prime)
    }

    fun add(other: FieldElement) = apply {
        require(prime == other.prime)
        num = (num + other.num) % prime
    }

    operator fun plus(other: FieldElement): FieldElement {
        require(prime == other.prime)
        return FieldElement((num + other.num) % prime, prime)
    }

    fun subtract(other: FieldElement) = apply {
        require(prime == other.prime)
        num = (prime + num - other.num) % prime
    }

    operator fun minus(other: FieldElement): FieldElement {
        require(prime == other.prime)
        return FieldElement((prime + num - other.num) % prime, prime)
    }
}