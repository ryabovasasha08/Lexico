import de.psegroup.convention.ktx.implementation
import de.psegroup.convention.ktx.ksp
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class JvmMoshiConventionPlugin : Plugin<Project> {

	override fun apply(target: Project) {
		with(target) {
			pluginManager.apply("com.google.devtools.ksp")

			dependencies {
				implementation(Square.moshi)
				ksp(Square.moshi.kotlinCodegen)
			}
		}
	}

}