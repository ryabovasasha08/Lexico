package de.psegroup.convention

import com.jraska.module.graph.assertion.GraphRulesExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureModuleGraphAssert() {
	pluginManager.apply("com.jraska.module.graph.assertion")

	configure<GraphRulesExtension> {
		allowed = arrayOf(
			// Libraries are allowed to know other libraries
			":libraries:[a-z:-]* -> :libraries:.*",

			// Features are allowed to know libraries, feature contracts and feature ui modules
			":features:[a-z:]* -> :libraries:.*",
			":features:[a-z:]* -> :features:.*-contract",
			":features:[a-z:]* -> :features:.*:contract",
			":features:[a-z:]* -> :features:.*:ui",

			// app is allowed to know libraries, features and feature contracts
			":app -> :libraries:.*",
			":app -> :features:.*",
		)
	}
}