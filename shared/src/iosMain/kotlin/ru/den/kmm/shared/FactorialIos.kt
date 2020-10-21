package ru.den.kmm.shared

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FactorialIos : CoroutineScope {

    private val factorial = Factorial()

    fun calc(arg: Long, callback: (String?, Throwable?) -> Unit) {
        wrap(callback) {
            factorial.calc(arg)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()
}

internal fun <T> CoroutineScope.wrap(
    callback: (result: T?, error: Throwable?) -> Unit,
    block: suspend () -> T
) {
    launch {
        try {
            callback(block(), null)
        } catch (e: Exception) {
            callback(null, e)
        }
    }
}
