package com.oriabova.lexico.setup.domain.model

import kotlinx.serialization.Serializable
import lexico.libraries.designsystem.generated.resources.Res
import lexico.libraries.designsystem.generated.resources.setup_level_advanced_description
import lexico.libraries.designsystem.generated.resources.setup_level_advanced_examples
import lexico.libraries.designsystem.generated.resources.setup_level_advanced_name
import lexico.libraries.designsystem.generated.resources.setup_level_beginner_description
import lexico.libraries.designsystem.generated.resources.setup_level_beginner_examples
import lexico.libraries.designsystem.generated.resources.setup_level_beginner_name
import lexico.libraries.designsystem.generated.resources.setup_level_intermediate_description
import lexico.libraries.designsystem.generated.resources.setup_level_intermediate_examples
import lexico.libraries.designsystem.generated.resources.setup_level_intermediate_name
import lexico.libraries.designsystem.generated.resources.setup_level_preintermediate_description
import lexico.libraries.designsystem.generated.resources.setup_level_preintermediate_examples
import lexico.libraries.designsystem.generated.resources.setup_level_preintermediate_name
import lexico.libraries.designsystem.generated.resources.setup_level_upperintermediate_description
import lexico.libraries.designsystem.generated.resources.setup_level_upperintermediate_examples
import lexico.libraries.designsystem.generated.resources.setup_level_upperintermediate_name
import org.jetbrains.compose.resources.StringArrayResource
import org.jetbrains.compose.resources.StringResource

@Serializable
enum class SetupLevel(
    val levelNameRes: StringResource,
    val descriptionRes: StringResource,
    val examplesRes: StringArrayResource
) {
    BEGINNER(
        Res.string.setup_level_beginner_name,
        Res.string.setup_level_beginner_description,
        Res.array.setup_level_beginner_examples
    ),
    PREINTERMEDIATE(
        Res.string.setup_level_preintermediate_name,
        Res.string.setup_level_preintermediate_description,
        Res.array.setup_level_preintermediate_examples
    ),
    INTERMEDIATE(
        Res.string.setup_level_intermediate_name,
        Res.string.setup_level_intermediate_description,
        Res.array.setup_level_intermediate_examples
    ),
    UPPERINTERMEDIATE(
        Res.string.setup_level_upperintermediate_name,
        Res.string.setup_level_upperintermediate_description,
        Res.array.setup_level_upperintermediate_examples
    ),
    ADVANCED(
        Res.string.setup_level_advanced_name,
        Res.string.setup_level_advanced_description,
        Res.array.setup_level_advanced_examples
    )
}