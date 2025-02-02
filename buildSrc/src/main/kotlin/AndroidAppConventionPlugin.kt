import de.psegroup.convention.configureAndroidApp
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidAppConventionPlugin : Plugin<Project> {
	override fun apply(target: Project) = with(target) {
		configureAndroidApp()
	}
}