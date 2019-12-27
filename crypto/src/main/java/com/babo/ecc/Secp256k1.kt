package com.babo.ecc

import java.math.BigInteger
import com.babo.utils.times
import java.security.SecureRandom

// http://www.oid-info.com/get/1.3.132.0.10
class Secp256k1(private val priKey: BigInteger = rng()) {

    val pubKey: Point

    init {
        require(priKey < p) { "wrong private key" }
        pubKey = priKey * G
    }

    fun sign(msg: ByteArray): Signature {
        val z = BigInteger(msg)
        val k = rng()
        val r = (k * G).x!!.num
        val kInv = FieldElement(k, n).multiplicativeInverse()
        var s = (z + r * priKey) * kInv % n
        if (s > n / BigInteger.valueOf(2)) s = n - s
        return Signature(r, s)
    }

    companion object {
        val p = BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F", 16) // 2^256 - 2^32 - 977
        val n = BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141", 16) // order
        val a = BigInteger("0000000000000000000000000000000000000000000000000000000000000000", 16)
        val b = BigInteger("0000000000000000000000000000000000000000000000000000000000000007", 16)
        val Gx = BigInteger("79BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798", 16)
        val Gy = BigInteger("483ADA7726A3C4655DA4FBFC0E1108A8FD17B448A68554199C47D08FFB10D4B8", 16)

        val G = Point(Gx, Gy, p, 0, 7) // initial point

        private fun rng(byteSize: Int = 32): BigInteger {
            val randomBytes = ByteArray(byteSize)
            SecureRandom().nextBytes(randomBytes)
            return BigInteger(randomBytes).let {
                return@let if (it.signum() < 1) it.negate() else it
            }
        }
    }
}