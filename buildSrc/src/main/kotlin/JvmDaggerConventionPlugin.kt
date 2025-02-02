import de.psegroup.convention.ktx.implementation
import de.psegroup.convention.ktx.kapt
import de.psegroup.convention.ktx.kaptTest
import de.psegroup.convention.ktx.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

class JvmDaggerConventionPlugin : Plugin<Project> {

	override fun apply(target: Project) = with(target) {
		pluginManager.apply("org.jetbrains.kotlin.kapt")
		pluginManager.apply("com.google.dagger.hilt.android")

		configure<KaptExtension> {
			correctErrorTypes = true
		}

		dependencies {
			implementation(Google.Dagger.hilt.android)
			kapt(Google.Dagger.hilt.compiler)

			testImplementation(Google.Dagger.hilt.android)
			kaptTest(Google.Dagger.hilt.compiler)
		}
	}

}