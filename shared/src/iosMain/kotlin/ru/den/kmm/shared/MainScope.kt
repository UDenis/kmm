package ru.den.kmm.shared

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class MainScope(
    private val mainContext: CoroutineContext = Dispatchers.Main,
    private val job: Job = Job()
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = mainContext + job


    fun onDestroy() {
        job.cancel()
    }

}
