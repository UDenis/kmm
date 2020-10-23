package ru.den.kmm.androidApp

import android.app.Application
import ru.den.logs.DebugLog4mLogger
import ru.den.logs.Log4m

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        Log4m.setup(DebugLog4mLogger())
    }
}
