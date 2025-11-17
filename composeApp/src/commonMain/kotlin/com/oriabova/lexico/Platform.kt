package com.oriabova.lexico

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform