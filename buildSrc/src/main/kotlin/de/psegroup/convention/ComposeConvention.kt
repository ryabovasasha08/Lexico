package de.psegroup.convention

import AndroidX
import com.android.build.api.dsl.CommonExtension
import de.psegroup.convention.ktx.androidTestImplementation
import de.psegroup.convention.ktx.debugImplementation
import de.psegroup.convention.ktx.implementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Configure Compose-specific options
 */
internal fun Project.configureCompose(
	commonExtension: CommonExtension<*, *, *, *, *,*>
) {
	commonExtension.apply {
		buildFeatures {
			compose = true
		}

		dependencies {
			// Bill of materials
			implementation(platform(AndroidX.compose.bom))
			androidTestImplementation(platform(AndroidX.compose.bom))

			// "Core" compose dependencies
			implementation(AndroidX.compose.material3)
			implementation(AndroidX.compose.material.icons.extended)
			implementation(AndroidX.compose.ui.tooling)
			implementation(AndroidX.compose.runtime)
			implementation(AndroidX.lifecycle.runtime.compose)

			// Test dependencies
			debugImplementation(AndroidX.compose.ui.testManifest)
			androidTestImplementation(AndroidX.compose.ui.testJunit4)
		}
	}
}
