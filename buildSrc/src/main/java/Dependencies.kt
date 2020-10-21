object Deps {

    private fun kotlinx(d: String, v: String): String {
        return "org.jetbrains.kotlinx:$d:$v"
    }

    object Coroutines {
        val ver = "1.3.9-native-mt"
        val core = kotlinx("kotlinx-coroutines-core", ver)
        val android = kotlinx("kotlinx-coroutines-android", ver)
    }

    object x {
        object lifecycle {
            val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.2.0"
        }
    }
}