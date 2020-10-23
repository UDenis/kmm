plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
}

group = "ru.den.kmm"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}

dependencies {
    implementation(project(":shared"))

    implementation(Deps.a.material)
    implementation(Deps.x.appcompat)
    implementation(Deps.x.constraintlayout)
    implementation(Deps.x.lifecycle.runtimeKtx)
    implementation(project(":logs"))
}

android {
    compileSdkVersion(V.compileSdkVersion)

    defaultConfig {
        applicationId = "ru.den.kmm.androidApp"
        minSdkVersion(V.minSdkVersion)
        targetSdkVersion(V.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}