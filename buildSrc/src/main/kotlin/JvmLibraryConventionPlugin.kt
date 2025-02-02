import de.psegroup.convention.configureKotlinJvm
import de.psegroup.convention.ktx.implementation
import de.psegroup.convention.ktx.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class JvmLibraryConventionPlugin : Plugin<Project> {
	override fun apply(target: Project) = with(target) {
		with(pluginManager) {
			apply("org.jetbrains.kotlin.jvm")
		}

		configureKotlinJvm()

		target.task("testJvmOnly"){
			dependsOn("test")
		}

		dependencies {
			// Coroutines
			implementation(KotlinX.coroutines.core)

			// Testing
			testImplementation(Testing.junit4)
			testImplementation(Testing.mockK)
			testImplementation(Libs.Testing.junitParams) // deprecated
			testImplementation(Libs.Testing.testParameterInjector)
			testImplementation(KotlinX.coroutines.test)
			testImplementation(CashApp.turbine)
		}
	}
}