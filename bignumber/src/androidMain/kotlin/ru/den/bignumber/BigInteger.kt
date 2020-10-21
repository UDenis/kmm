package ru.den.bignumber

actual typealias BigInteger = java.math.BigInteger

actual fun Long.toBigInteger(): BigInteger {
    return BigInteger.valueOf(this)
}

actual object BigNumber {
    actual val one: BigInteger = BigInteger.ONE
}