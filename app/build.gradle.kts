plugins {
    id("com.android.app")
    id("com.android.room")
    id("com.android.compose")
    id("com.jvm.dagger")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.oriabova.app"

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(AndroidX.activity.compose)
    implementation(AndroidX.navigation.compose)
    implementation(AndroidX.core.splashscreen)
    implementation(AndroidX.dataStore.preferences)
    implementation(KotlinX.serialization.json)
}

hilt {
    enableAggregatingTask = false
}