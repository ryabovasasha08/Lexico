import de.fayard.refreshVersions.core.versionFor

repositories {
	mavenCentral()
	google()
}

plugins {
	`kotlin-dsl`
}

gradlePlugin {
	plugins {
		register("jvmLibrary") {
			id = "com.jvm.library"
			implementationClass = "JvmLibraryConventionPlugin"
		}
		register("androidLibrary") {
			id = "com.android.library"
			implementationClass = "AndroidLibraryConventionPlugin"
		}
		register("androidApp") {
			id = "com.android.app"
			implementationClass = "AndroidAppConventionPlugin"
		}
		register("androidRoom") {
			id = "com.android.room"
			implementationClass = "AndroidRoomConventionPlugin"
		}
		register("moshi") {
			id = "com.jvm.moshi"
			implementationClass = "JvmMoshiConventionPlugin"
		}
		register("dagger") {
			id = "com.jvm.dagger"
			implementationClass = "JvmDaggerConventionPlugin"
		}
		register("compose") {
			id = "com.android.compose"
			implementationClass = "AndroidComposeConventionPlugin"
		}
		register("paparazzi") {
			id = "com.android.paparazzi"
			implementationClass = "AndroidPaparazziConventionPlugin"
		}

	}
}

dependencies {
	implementation(Kotlin.gradlePlugin)
	implementation(Android.tools.build.gradlePlugin)
	implementation("com.google.devtools.ksp:symbol-processing-gradle-plugin:_")
	implementation("org.jetbrains.kotlinx:kover-gradle-plugin:_")
	implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:_")
	implementation("com.jraska.module.graph.assertion:plugin:_")
	implementation("com.dropbox.dependency-guard:dependency-guard:_")
	implementation("org.jetbrains.kotlin:compose-compiler-gradle-plugin:${versionFor(Kotlin.gradlePlugin)}")
}