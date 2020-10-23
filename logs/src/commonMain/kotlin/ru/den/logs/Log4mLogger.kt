package ru.den.logs

interface Log4mLogger {
    fun log(
        level: LogLevel,
        tag: String,
        message: String?,
        e: Throwable?
    )
}