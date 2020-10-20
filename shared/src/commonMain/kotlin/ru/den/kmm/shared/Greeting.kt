package ru.den.kmm.shared


class Greeting {
    fun greeting(): String {
        return "Hi from KMM, ${Platform().platform}! "
    }
}
