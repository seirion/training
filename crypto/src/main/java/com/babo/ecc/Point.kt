package com.babo.ecc

import java.math.BigInteger

data class Point(var x: BigInteger?, var y: BigInteger?, val a: Int, val b: Int) {
    init {
        require((x == null && y == null) || (x != null && y != null))
        if (x != null && y != null) {
            val bigA = BigInteger.valueOf(a.toLong())
            val bigB = BigInteger.valueOf(b.toLong())
            // y^2 = x^3 + ax + b
            require(y!!.pow(2) == x!!.pow(3) + bigA * x!! + bigB) {
                "Point($x, $y) is not on the curve"
            }
        }
    }

    fun infinity(): Boolean = (x == null) // point of inifinity

    operator fun plus(other: Point): Point {
        require(a == other.a && b == other.b) {
            "Points $this and $other are not on the same curve"
        }
        return when {
            x == null -> other.copy()
            other.x == null -> this.copy()
            y == other.y!!.negate() -> Point(null, null, a, b)
            else -> this.copy() // not implemented
        }
    }
}