plugins {
    id("com.oriabova.lexico.kmpModule")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.libraries.serialization)
            implementation(libs.androidx.datastore)
            implementation(libs.androidx.datastore.preferences)
            implementation(libs.koin.core)
        }
        androidMain.dependencies {
            implementation(libs.koin.android)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
