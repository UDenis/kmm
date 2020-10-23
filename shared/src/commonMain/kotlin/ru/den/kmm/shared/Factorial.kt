package ru.den.kmm.shared

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ru.den.bignumber.BigInteger
import ru.den.bignumber.BigNumber
import ru.den.bignumber.toBigInteger
import ru.den.logs.Log4m
import kotlin.math.min

class Factorial {

    private val availableProcessors = 5

    suspend fun calc(factorialArgument: Long): String = withContext(Dispatchers.Default) {

        Log4m.d(
            message = "calc for $factorialArgument",
            tag = "Factorial"
        )

        val numberOfThreads = if (factorialArgument < 20) 1 else availableProcessors
        val computationRangeSize = factorialArgument / numberOfThreads
        var nextComputationRangeStart = 1L

        val computationRangeResultList = ArrayList<Deferred<BigInteger>>(numberOfThreads)
        for (i in 0 until numberOfThreads) {

            computationRangeResultList.add(
                calcRange(
                    this,
                    nextComputationRangeStart,
                    min(nextComputationRangeStart + computationRangeSize, factorialArgument)
                )
            )

            nextComputationRangeStart = nextComputationRangeStart + computationRangeSize + 1
        }

        var result: BigInteger = BigNumber.one
        for (i in 0 until numberOfThreads) {
            result = result.multiply(computationRangeResultList[i].await())
        }

        Log4m.d(
            message = "calc for $factorialArgument = $result",
            tag = "Factorial"
        )
        return@withContext result.toString()
    }

    private fun calcRange(scope: CoroutineScope, from: Long, to: Long): Deferred<BigInteger> {
        return scope.async {
            Log4m.d(
                message = "calcRange for $from - $to",
                tag = "Factorial"
            )
            delay(500)
            var result: BigInteger = BigNumber.one
            for (i in from..to) {
                result = result.multiply(i.toBigInteger())
            }
            Log4m.d(
                message = "calcRange for $from - $to = $result",
                tag = "Factorial"
            )
            return@async result
        }
    }
}