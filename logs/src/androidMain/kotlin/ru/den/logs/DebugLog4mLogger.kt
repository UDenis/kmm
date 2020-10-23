package ru.den.logs

import android.util.Log
import ru.den.logs.LogLevel.ASSERT
import ru.den.logs.LogLevel.DEBUG
import ru.den.logs.LogLevel.ERROR
import ru.den.logs.LogLevel.INFO
import ru.den.logs.LogLevel.VERBOSE
import ru.den.logs.LogLevel.WARNING

class DebugLog4mLogger : Log4mLogger {
    override fun log(level: LogLevel, tag: String, message: String?, e: Throwable?) {
        Log.println(level.toInt(), tag, buildMessage(message, e))
    }

    private fun buildMessage(message: String?, e: Throwable?): String {
        val builder = StringBuilder()
        if (message != null) {
            builder.append(message)
            builder.append(System.lineSeparator())
        }

        if (e != null) {
            builder.append(Log.getStackTraceString(e))
        }

        return builder.toString()
    }

    private fun LogLevel.toInt(): Int {
        return when (this) {
            VERBOSE -> Log.VERBOSE
            DEBUG -> Log.DEBUG
            INFO -> Log.INFO
            WARNING -> Log.WARN
            ERROR -> Log.ERROR
            ASSERT -> Log.ASSERT
        }
    }
}