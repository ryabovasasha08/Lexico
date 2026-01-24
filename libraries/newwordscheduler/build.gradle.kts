import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    iosArm64()
    iosSimulatorArm64()

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

android {
    namespace = "com.oriabova.lexico.newwordscheduler"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
