package com.oriabova.lexico.setup.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SetupDetails(
    val languageToLearn: String = "",
    val level: SetupLevel = SetupLevel.BEGINNER,
    val frequency: SetupFrequency = SetupFrequency.LIGHT,
)