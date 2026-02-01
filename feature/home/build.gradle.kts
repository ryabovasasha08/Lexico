plugins {
    id("com.oriabova.lexico.kmpModule")
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.libraries.languageUtils)
            implementation(projects.libraries.ai)
            implementation(projects.libraries.localstorage)
            implementation(projects.libraries.designsystem)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.ktor.client.core)

//            implementation(libs.coil3.compose.core)
//            implementation(libs.coil3.mp)
            implementation(libs.coil3.compose)
            implementation(libs.coil3.network.ktor)
            implementation(libs.koin.compose)
            implementation(libs.koin.composeVM)
        }
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}
