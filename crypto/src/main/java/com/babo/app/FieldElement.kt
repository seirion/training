package com.babo.app

data class FieldElement(var num: Int, val prime: Int) {
    init {
        require(num in 0 until prime)
    }

    fun add(other: FieldElement) = apply {
        require(prime == other.prime)
        num = (num + other.num) % prime
    }
}