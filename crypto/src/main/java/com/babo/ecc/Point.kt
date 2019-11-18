package com.babo.ecc

data class Point(var x: Int?, var y: Int?, val a: Int, val b: Int) {
    init {
        require((x == null && y == null) || (x != null && y != null))
        if (x != null && y != null) {
            // y^2 = x^3 + ax + b
            require(y!! * y!! == x!! * x!! * x!! + a * x!! + b) {
                "Point($x, $y) is not on the curve"
            }
        }
    }

    fun add(other: Point) = apply {
        require(a == other.a && b == other.b) {
            "Points $this $other are not on the same curve"
        }
        if (x == null) return other
        if (other.x == null) return this
        if (y == -1 * other.y!!) {
            x = null
            y = null
        }
    }

    operator fun plus(other: Point): Point {
        require(a == other.a && b == other.b) {
            "Points $this $other are not on the same curve"
        }
        return when {
            x == null -> Point(other.x, other.y, other.a, other.b)
            other.x == null -> Point(x, y, a, b)
            y == -1 * other.y!! -> Point(null, null, a, b)
            else -> this
        }
    }
}