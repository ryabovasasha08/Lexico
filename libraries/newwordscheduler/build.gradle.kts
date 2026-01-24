plugins {
    id("com.oriabova.lexico.kmpModule")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.libraries.setup)
            implementation(libs.koin.core)
        }
        androidMain.dependencies {
            implementation(projects.libraries.notifications)
            implementation(libs.androidx.work.runtime)
            implementation(libs.koin.android)
        }
        iosMain.dependencies {
            implementation(projects.libraries.notifications)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
