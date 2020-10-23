package ru.den.logs

import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSThread

class DebugLog4mLogger(
    private val defaultTag: String = "app"
) : Log4mLogger {

    private val dateFormatter = NSDateFormatter().apply {
        dateFormat = "MM-dd HH:mm:ss.SSS"
    }

    private val tagMap: HashMap<LogLevel, String> = hashMapOf(
        LogLevel.VERBOSE to "💜 VERBOSE",
        LogLevel.DEBUG to "💚 DEBUG",
        LogLevel.INFO to "💙 INFO",
        LogLevel.WARNING to "💛 WARN",
        LogLevel.ERROR to "❤️ ERROR",
        LogLevel.ASSERT to "💞 ASSERT"
    )

    override fun log(level: LogLevel, tag: String, message: String?, e: Throwable?) {
        println(
            buildLog(level, tag, message)
        )
        if (e != null) {
            println(e)
        }
    }

    private fun buildLog(level: LogLevel, tag: String?, message: String?): String {
        return "${getCurrentTime()} ${tagMap[level]} ${tag ?: performTag(defaultTag)} - $message"
    }

    private fun getCurrentTime() = dateFormatter.stringFromDate(NSDate())

    // find stack trace
    private fun performTag(tag: String): String {
        val thread = NSThread.callStackSymbols

        return if (thread.size >= CALL_STACK_INDEX) {
            createStackElementTag(thread[CALL_STACK_INDEX] as String)
        } else {
            tag
        }
    }

    private fun createStackElementTag(string: String): String {
        var tag = string
        tag = tag.substringBeforeLast('$')
        tag = tag.substringBeforeLast('(')
        tag = tag.substring(tag.lastIndexOf(".", tag.lastIndexOf(".") - 1) + 1)
        tag = tag.replace("$", "")
        tag = tag.replace("COROUTINE", "")
        return tag
    }
}

private const val CALL_STACK_INDEX = 8