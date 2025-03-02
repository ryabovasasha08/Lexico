package com.oriabova.lexico.setup.data

import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import com.oriabova.app.R
import kotlinx.serialization.Serializable

@Serializable
enum class SetupLevel(
    @StringRes val levelNameRes: Int,
    @StringRes val descriptionRes: Int,
    @ArrayRes val examplesRes: Int
) {
    BEGINNER(
        R.string.setup_level_beginner_name,
        R.string.setup_level_beginner_description,
        R.array.setup_level_beginner_examples
    ),
    PREINTERMEDIATE(
        R.string.setup_level_preintermediate_name,
        R.string.setup_level_preintermediate_description,
        R.array.setup_level_preintermediate_examples
    ),
    INTERMEDIATE(
        R.string.setup_level_intermediate_name,
        R.string.setup_level_intermediate_description,
        R.array.setup_level_intermediate_examples
    ),
    UPPERINTERMEDIATE(
        R.string.setup_level_upperintermediate_name,
        R.string.setup_level_upperintermediate_description,
        R.array.setup_level_upperintermediate_examples
    ),
    ADVANCED(
        R.string.setup_level_advanced_name,
        R.string.setup_level_advanced_description,
        R.array.setup_level_advanced_examples
    )
}