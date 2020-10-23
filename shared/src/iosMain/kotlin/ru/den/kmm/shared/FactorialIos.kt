package ru.den.kmm.shared

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.den.logs.Log4m

class FactorialIos {

    private val scope: CoroutineScope = NativeScope(
        context = Dispatchers.Main
    )

    private val factorial = Factorial()

    fun calc(arg: Long, callback: (String?, Throwable?) -> Unit) {
        scope.wrap(callback) {
            Log4m.d("Factorial", "start 2")
            factorial.calc(arg)
        }
    }
}

internal fun <T> CoroutineScope.wrap(
    callback: (result: T?, error: Throwable?) -> Unit,
    block: suspend () -> T
) {
    launch {
        try {
            Log4m.d("Factorial", "start 1")
            callback(block(), null)
        } catch (e: Exception) {
            Log4m.d("Factorial", e)
            callback(null, e)
        }
    }
}
