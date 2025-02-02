import com.android.build.gradle.LibraryExtension
import de.psegroup.convention.ktx.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.attributes.java.TargetJvmEnvironment
import org.gradle.api.plugins.JavaBasePlugin.VERIFICATION_GROUP
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.withType
import java.util.Locale

class AndroidPaparazziConventionPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		val isPaparazzi = target.gradle.startParameter.taskNames.any { it.contains("Snapshots") }

		target.dependencies {
			testImplementation(project(":libraries:testing-snapshot"))
		}

		target.createSnapshotTasks()

		if (isPaparazzi) {
			target.plugins.apply("app.cash.paparazzi")
			target.fixGuavaIssue()
		}

		target.configureTests(isPaparazzi)
	}

	private fun Project.createSnapshotTasks() {
		val extension = extensions.getByType(LibraryExtension::class.java)

		val verifyAnchorTask = tasks.register("verifySnapshots") {
			group = VERIFICATION_GROUP
			description = "Run screenshot tests for all variants"
		}

		val recordAnchorTask = tasks.register("recordSnapshots") {
			group = VERIFICATION_GROUP
			description = "Record golden images for all variants"
		}

		extension.libraryVariants.all {
			val variantName = this.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
			createTask(
				"verify",
				"Run screenshot tests",
				verifyAnchorTask,
				variantName
			)
			createTask(
				"record",
				"Record golden images",
				recordAnchorTask,
				variantName
			)
		}
	}
}

private fun Project.createTask(
	taskPrefix: String,
	taskDescription: String,
	anchorTask: TaskProvider<Task>,
	variantName: String
) {
	val taskName = "${taskPrefix}Snapshots$variantName"
	val paparazziTask = "${taskPrefix}Paparazzi$variantName"

	val verifyTask = project.tasks.register(taskName) {
		group = VERIFICATION_GROUP
		description = "$taskDescription for variant $variantName"
	}
	anchorTask.configure { dependsOn(verifyTask) }
	verifyTask.configure { dependsOn(paparazziTask) }
}

private fun Project.fixGuavaIssue() {
	dependencies {
		// https://github.com/cashapp/paparazzi/issues/1231
		constraints {
			"testImplementation"("com.google.guava:guava") {
				attributes {
					attribute(
						TargetJvmEnvironment.TARGET_JVM_ENVIRONMENT_ATTRIBUTE,
						objects.named<TargetJvmEnvironment>(TargetJvmEnvironment.STANDARD_JVM)
					)
				}
				because(
					"LayoutLib and sdk-common depend on Guava's -jre published variant." +
							"See https://github.com/cashapp/paparazzi/issues/906"
				)
			}
		}
	}
}

private fun Project.configureTests(isPaparazzi: Boolean) {
	tasks.withType<Test>().configureEach {
		useJUnit {
			if (isPaparazzi) {
				includeCategories("de.parshipgroup.testing.snapshot.SnapshotTestCategory")
			} else {
				excludeCategories("de.parshipgroup.testing.snapshot.SnapshotTestCategory")
			}
		}
	}
}
