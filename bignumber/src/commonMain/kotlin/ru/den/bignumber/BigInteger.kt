package ru.den.bignumber

expect class BigInteger {
    fun multiply(a: BigInteger): BigInteger
}

expect object BigNumber {
    val one: BigInteger
}

expect fun Long.toBigInteger(): BigInteger