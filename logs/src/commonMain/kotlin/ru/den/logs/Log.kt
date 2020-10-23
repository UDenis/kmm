package ru.den.logs

import ru.den.logs.LogLevel.DEBUG
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object Log4m {

    private lateinit var logger: Log4mLogger

    fun setup(l: Log4mLogger) {
        logger = l
    }

    fun d(tag: String, message: String) {
        log(DEBUG, tag, message, null)
    }

    fun d(tag: String, e: Throwable) {
        log(DEBUG, tag, null, e)
    }

    fun d(tag: String, message: String, e: Throwable) {
        log(DEBUG, tag, message, e)
    }


    private fun log(
        level: LogLevel,
        tag: String,
        message: String?,
        e: Throwable?
    ) {
        logger.log(level, tag, message, e)
    }
}