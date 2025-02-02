package de.psegroup.convention

import Android
import Build
import com.android.build.api.dsl.ApplicationExtension
import de.psegroup.convention.ktx.coreLibraryDesugaring
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidApp() {
	with(pluginManager) {
		apply("com.android.application")
		apply("kotlin-android")
	}

	configure<ApplicationExtension> {
		configureKotlinAndroid(this)

		defaultConfig {
			targetSdk = Build.Versions.targetSdk
			versionName = properties["appVersionName"] as String
			versionCode = (properties["appVersionCode"] as String).toInt()

			multiDexEnabled = true

			ndk {
				abiFilters.addAll(listOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64"))
			}
		}

		buildFeatures {
			buildConfig = true
		}

		packaging {
			resources.excludes.add("META-INF/AL2.0")
			resources.excludes.add("META-INF/LGPL2.1")
			resources.excludes.add("/META-INF/gradle/incremental.annotation.processors")

		}

		configureLint(this)

		compileOptions {
			isCoreLibraryDesugaringEnabled = true
		}
	}

	configureModuleGraphAssert()

	dependencies {
		coreLibraryDesugaring(Android.tools.desugarJdkLibs)
	}
}

private fun Project.configureLint(applicationExtension: ApplicationExtension) = with(applicationExtension) {
	lint {
		checkDependencies = true
		baseline = project.file("lint-baseline.xml")
		disable += listOf(
			"MissingTranslation",
			"RtlHardcoded",
			"RtlCompat",
			"RtlEnabled",
			"XmlEscapeNeeded",
			"NullSafeMutableLiveData" //Error https://issuetracker.google.com/issues/169249668#comment8
		)
	}
}