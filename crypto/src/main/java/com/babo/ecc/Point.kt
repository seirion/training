package com.babo.ecc

import com.babo.utils.isOdd
import com.babo.utils.times
import java.math.BigInteger

data class Point(val x: FieldElement?, val y: FieldElement?, val a: Int, val b: Int) {
    constructor(x: Long, y: Long, p: Long, a: Int, b: Int) :
            this(FieldElement(x, p), FieldElement(y, p), a, b)

    constructor(x: BigInteger, y: BigInteger, p: BigInteger, a: Int, b: Int) :
            this(FieldElement(x, p), FieldElement(y, p), a, b)

    init {
        require((x == null && y == null) || (x != null && y != null))
        if (x != null && y != null) {
            val bigA = BigInteger.valueOf(a.toLong())
            val bigB = BigInteger.valueOf(b.toLong())
            val prime = x.prime
            // y^2 = x^3 + ax + b
            val lhs = y.num.pow(2) % prime
            val rhs = (x.num.pow(3) + bigA * x.num + bigB) % prime
            require(lhs == rhs) { "Point($x, $y) is not on the curve" }
        }
    }

    operator fun plus(other: Point): Point {
        require(a == other.a && b == other.b) {
            "Points $this and $other are not on the same curve"
        }
        return when {
            x == null -> other.copy()
            other.x == null -> this.copy()
            x == other.x && y!!.isAdditionalInverseOf(other.y) -> Point(null, null, a, b) // y == -y
            this == other -> addIdenticalPoints()
            else -> addDifferentPoints(other) // this != other
        }
    }

    operator fun times(number: Long) = times(BigInteger.valueOf(number))

    operator fun times(number: BigInteger): Point {
        require(number > BigInteger.ZERO) {
            "invalid param: number must be greater than zero"
        }

        if (number == BigInteger.ONE) return this.copy()
        val half = times(number / BigInteger.valueOf(2L))
        return if (number.isOdd()) {
            half + half + this
        } else {
            half + half
        }
    }

    fun verify(msg: ByteArray, signature: Signature): Boolean {
        val z = BigInteger(msg)
        val sInv = FieldElement(signature.s, Secp256k1.n).multiplicativeInverse()
        val u = z * sInv % Secp256k1.n
        val v = signature.r * sInv % Secp256k1.n
        val r = (u * Secp256k1.G) + (v * this)
        return r.x!!.num == signature.r
    }

    private fun addIdenticalPoints(): Point {
        if (y!!.num == BigInteger.ZERO) return Point(null, null, a, b) // infinite zero
        require(x != null)

        val three = BigInteger.valueOf(3)
        val two = BigInteger.valueOf(2)
        val s = (x * x * three) / (y * two) // s = (3x^2) / (2y)
        val x3 = s * s - (x * two)
        val y3 = s * (x - x3) - y
        return Point(x3, y3, a, b)
    }

    private fun addDifferentPoints(other: Point): Point {
        require(x != null && y != null && other.x != null && other.y != null)
        // require(this != other && !this.infinity && !other.infinity)
        val x1 = x
        val y1 = y
        val x2 = other.x
        val y2 = other.y

        val s = (y2 - y1) / (x2 - x1) // slop
        val x3 = s * s - x1 - x2
        val y3 = s * (x1 - x3) - y1
        return Point(x3, y3, a, b)
    }
}