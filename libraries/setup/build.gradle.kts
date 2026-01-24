plugins {
    id("com.oriabova.lexico.kmpModule")
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.libraries.languageUtils)
            implementation(projects.libraries.designsystem)
            implementation(projects.libraries.localstorage)
            implementation(compose.runtime)
            implementation(compose.components.resources)
            implementation(libs.koin.core)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
