package ru.den.kmm.shared

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class NativeScope(
    private val context: CoroutineContext = Dispatchers.Main,
    private val job: Job = Job()
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = context + job

    fun onDestroy() {
        job.cancel()
    }
}
