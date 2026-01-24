plugins {
    id("com.oriabova.lexico.kmpModule")
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.libraries.localstorage)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(compose.runtime)
        }
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.core.ktx)
        }
    }
}
