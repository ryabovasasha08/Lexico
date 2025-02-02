import com.google.devtools.ksp.gradle.KspExtension
import de.psegroup.convention.ktx.implementation
import de.psegroup.convention.ktx.ksp
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidRoomConventionPlugin : Plugin<Project> {

	override fun apply(target: Project) = with(target) {
		pluginManager.apply("com.google.devtools.ksp")

		configure<KspExtension> {
			arg("room.schemaLocation", "$projectDir/schemas")
		}

		dependencies {
			implementation(AndroidX.room.ktx)
			ksp(AndroidX.room.compiler)
		}
	}

}