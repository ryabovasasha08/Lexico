package de.psegroup.convention

import Build
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask


/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(
	commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
	commonExtension.apply {
		compileSdk = Build.Versions.compileSdk

		defaultConfig {
			minSdk = Build.Versions.minSdk

			testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		}

		@Suppress("UnstableApiUsage")
		testOptions {
			animationsDisabled = true

			unitTests {
				isReturnDefaultValues = true
				isIncludeAndroidResources = true
			}
		}
	}

	configureKotlin()
}

/**
 * Configure base Kotlin options for JVM (non-Android)
 */
internal fun Project.configureKotlinJvm() {
	configureKotlin()
}

/**
 * Configure base Kotlin options
 */
private fun Project.configureKotlin() {
	extensions.configure<KotlinProjectExtension> {
		jvmToolchain(Build.Versions.java)
	}

	tasks.withType<KotlinCompilationTask<*>>().configureEach {
		compilerOptions {
			freeCompilerArgs.addAll(
				listOf(
					"-Xinline-classes",
					"-opt-in=kotlin.RequiresOptIn",
					"-Xjvm-default=all"
				)
			)

			if (name.contains("test", ignoreCase = true)) {
				freeCompilerArgs.add("-java-parameters")
			}
		}
	}
}
