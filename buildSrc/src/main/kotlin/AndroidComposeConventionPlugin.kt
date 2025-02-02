import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import de.psegroup.convention.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType

class AndroidComposeConventionPlugin : Plugin<Project> {
	override fun apply(target: Project): Unit = with(target) {
		target.plugins.apply("org.jetbrains.kotlin.plugin.compose")

		extensions.findByType<LibraryExtension>()?.let { configureCompose(it) }
		extensions.findByType<ApplicationExtension>()?.let { configureCompose(it) }
	}
}