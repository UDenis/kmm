object V {
    val compileSdkVersion = 29
    val minSdkVersion = 24
    val targetSdkVersion = 29
}

object Deps {
    object Coroutines {
        val ver = "1.3.9-native-mt"
        val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$ver"
        val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$ver"
    }

    object x {
        object lifecycle {
            val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.2.0"
        }

        val appcompat = "androidx.appcompat:appcompat:1.2.0"
        val constraintlayout = "androidx.constraintlayout:constraintlayout:1.1.3"
    }

    object a {
        val material = "com.google.android.material:material:1.2.0"
    }

}