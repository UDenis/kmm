package ru.den.bignumber

import platform.Foundation.NSDecimalNumber

actual class BigInteger(n: NSDecimalNumber) {
    val number: NSDecimalNumber = n

    actual fun multiply(a: BigInteger): BigInteger {
        return BigInteger(
            number.decimalNumberByMultiplyingBy(a.number)
        )
    }

    override fun toString(): String {
        return number.toString()
    }
}

actual object BigNumber {
    actual val one: BigInteger = BigInteger(NSDecimalNumber.one)
}

actual fun Long.toBigInteger(): BigInteger {
    return BigInteger(
        NSDecimalNumber.decimalNumberWithString(
            this.toString(), null
        )
    )
}