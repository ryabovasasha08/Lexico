package com.oriabova.lexico.setup.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SetupDetails(
    val languageToLearn: String?,
    val level: SetupLevel?,
    val frequency: SetupFrequency?,
) {
    fun isComplete(): Boolean {
        return languageToLearn != null && level != null && frequency != null
    }

    companion object {
        fun initial() = SetupDetails(
            languageToLearn = null,
            level = null,
            frequency = null,
        )
    }
}