package ru.den.kmm.shared

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import platform.UIKit.UIDevice
import ru.den.logs.DebugLog4mLogger
import ru.den.logs.Log4m

actual class Platform actual constructor() {
    actual val platform: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}


object Logs {
    fun initLogs() {
        NativeScope(
            Dispatchers.Main
        ).launch {
            setupLog4m()
        }

        NativeScope(
            Dispatchers.Default
        ).launch {
            setupLog4m()
        }

        NativeScope(
            Dispatchers.Unconfined
        ).launch {
            setupLog4m()
        }
    }

    private fun setupLog4m() {
        Log4m.setup(DebugLog4mLogger())
        Log4m.d("FACTORIAL", "SETUP LOG")
    }
}
