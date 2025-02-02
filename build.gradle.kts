buildscript {
	repositories {
		mavenCentral()
		google()
		maven("https://plugins.gradle.org/m2/")
		maven("https://maven.fabric.io/public")
	}
	dependencies {
		classpath(Android.tools.build.gradlePlugin)
		classpath(Google.playServicesGradlePlugin)
		classpath(AndroidX.navigation.safeArgsGradlePlugin)
		classpath(Firebase.crashlyticsGradlePlugin)
		classpath(Google.android.versionMatcherPlugin)
		classpath(Kotlin.gradlePlugin)
		classpath(Google.dagger.hilt.android.gradlePlugin)
	}
}

allprojects {
	repositories {
		mavenCentral()
		google()
		maven("https://jitpack.io")
	}
}

tasks.register<Delete>("clean") {
	delete(layout.buildDirectory)
}