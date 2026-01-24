package com.oriabova.lexico.utils

import kotlinx.serialization.Serializable

@Serializable
data class Language(
    val code: String,
    val name: String,
)