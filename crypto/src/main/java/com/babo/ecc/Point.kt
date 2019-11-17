package com.babo.ecc

data class Point(var x: Int, var y: Int, val a: Int, val b: Int) {
    init {
        // y^2 = x^3 + ax + b
        require(y * y == x * x * x + a * x + b) {
            "point($x, $y) is not on the curve"
        }
    }
}