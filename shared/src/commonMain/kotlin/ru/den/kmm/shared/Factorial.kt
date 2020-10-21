package ru.den.kmm.shared

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.math.min

class Factorial {

    private val availableProcessors = 5

    suspend fun calc(factorialArgument: Long): String = withContext(Dispatchers.Default) {
        val numberOfThreads = if (factorialArgument < 20) 1 else availableProcessors
        val computationRangeSize = factorialArgument / numberOfThreads
        var nextComputationRangeStart = 1L

        val computationRangeResultList = ArrayList<Deferred<Long>>(numberOfThreads)
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

        var result = 1L
        for (i in 0 until numberOfThreads) {
            result *= computationRangeResultList[i].await()
        }
        return@withContext result.toString()
    }

    private suspend fun calcRange(scope: CoroutineScope, from: Long, to: Long) : Deferred<Long> {
        return scope.async {
            var result = 1L
            for (i in from..to) {
                result *= i
            }
            return@async result
        }
    }
}