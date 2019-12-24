package com.babo.utils

import com.babo.ecc.Point
import java.math.BigInteger

// extensions for Point operations
operator fun Int.times(other: Point) = other * BigInteger.valueOf(this.toLong())
operator fun Long.times(other: Point) = other * BigInteger.valueOf(this)
operator fun BigInteger.times(other: Point) = other * this


// BigInteger operations
fun BigInteger.isOdd(): Boolean {
    return this.testBit(0)
}
