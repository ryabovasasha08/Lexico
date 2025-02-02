package com.oriabova.lexico.setup.data

import kotlinx.serialization.Serializable

@Serializable
data class SetupDetails(
    val languageToLearn: String,
    val level: String,
    val frequency: String,
)