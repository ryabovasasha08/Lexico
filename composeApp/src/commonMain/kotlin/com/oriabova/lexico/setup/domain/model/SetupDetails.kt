package com.oriabova.lexico.setup.domain.model

import com.oriabova.lexico.utils.Language
import kotlinx.serialization.Serializable

@Serializable
data class SetupDetails(
    val languageToLearn: Language?,
    val level: SetupLevel?,
    val frequency: SetupFrequency?,
    val notificationPermissionGranted: Boolean = false,
) {
    fun isComplete(): Boolean {
        return languageToLearn != null &&
            level != null &&
            frequency != null &&
            notificationPermissionGranted
    }

    companion object {
        fun initial() = SetupDetails(
            languageToLearn = null,
            level = null,
            frequency = null,
            notificationPermissionGranted = false,
        )
    }
}
