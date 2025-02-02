package de.psegroup.convention

import AndroidX
import CashApp
import KotlinX
import Libs
import Testing
import com.android.build.api.dsl.LibraryExtension
import de.psegroup.convention.ktx.implementation
import de.psegroup.convention.ktx.lintPublish
import de.psegroup.convention.ktx.testImplementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

internal fun Project.configureAndroidLibrary() {
	with(pluginManager) {
		apply("com.android.library")
		apply("kotlin-android")
		apply("org.jetbrains.kotlinx.kover")
	}

	configure<LibraryExtension> {
		configureKotlinAndroid(this)

		buildTypes {
			create("dev")
		}
	}

	dependencies {
		// Lint
		lintPublish(project(":code-analysis:lint"))

		// Coroutines
		implementation(KotlinX.coroutines.core)

		// Testing
		testImplementation(Testing.junit4)
		testImplementation(Testing.mockK)
		testImplementation(Libs.Testing.testParameterInjector)
		testImplementation(KotlinX.coroutines.test)
		testImplementation(AndroidX.archCore.testing)
		testImplementation(CashApp.turbine)
		testImplementation(project(":libraries:testing"))
	}
}