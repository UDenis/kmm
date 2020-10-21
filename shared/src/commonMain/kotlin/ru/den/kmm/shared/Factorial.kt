package ru.den.kmm.shared

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import ru.den.bignumber.BigInteger
import ru.den.bignumber.BigNumber
import ru.den.bignumber.toBigInteger
import kotlin.math.min

class Factorial {

    private val availableProcessors = 5

    suspend fun calc(factorialArgument: Long): String = withContext(Dispatchers.Default) {
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
        return@withContext result.toString()
    }

    private fun calcRange(scope: CoroutineScope, from: Long, to: Long): Deferred<BigInteger> {
        return scope.async {
            var result: BigInteger = BigNumber.one
            for (i in from..to) {
                result = result.multiply(i.toBigInteger())
            }
            return@async result
        }
    }
}