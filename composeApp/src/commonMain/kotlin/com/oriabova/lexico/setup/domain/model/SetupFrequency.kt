package com.oriabova.lexico.setup.domain.model

import kotlinx.serialization.Serializable
import lexico.composeapp.generated.resources.Res
import lexico.composeapp.generated.resources.setup_frequency_heavy_daily_description
import lexico.composeapp.generated.resources.setup_frequency_heavy_daily_name
import lexico.composeapp.generated.resources.setup_frequency_heavy_daily_pace
import lexico.composeapp.generated.resources.setup_frequency_light_daily_description
import lexico.composeapp.generated.resources.setup_frequency_light_daily_name
import lexico.composeapp.generated.resources.setup_frequency_light_daily_pace
import lexico.composeapp.generated.resources.setup_frequency_weekdays_description
import lexico.composeapp.generated.resources.setup_frequency_weekdays_name
import lexico.composeapp.generated.resources.setup_frequency_weekdays_pace
import lexico.composeapp.generated.resources.setup_frequency_weekender_description
import lexico.composeapp.generated.resources.setup_frequency_weekender_name
import lexico.composeapp.generated.resources.setup_frequency_weekender_pace
import org.jetbrains.compose.resources.StringResource

@Serializable
enum class SetupFrequency(
    val titleRes: StringResource,
    val descriptionRes: StringResource,
    val paceRes: StringResource,
) {
    LIGHT_DAILY(
        Res.string.setup_frequency_light_daily_name,
        Res.string.setup_frequency_light_daily_description,
        Res.string.setup_frequency_light_daily_pace
    ),
    HEAVY_DAILY(
        Res.string.setup_frequency_heavy_daily_name,
        Res.string.setup_frequency_heavy_daily_description,
        Res.string.setup_frequency_heavy_daily_pace
    ),
    WEEKDAYS(
        Res.string.setup_frequency_weekdays_name,
        Res.string.setup_frequency_weekdays_description,
        Res.string.setup_frequency_weekdays_pace
    ),
    WEEKENDER(
        Res.string.setup_frequency_weekender_name,
        Res.string.setup_frequency_weekender_description,
        Res.string.setup_frequency_weekender_pace
    ),
}
