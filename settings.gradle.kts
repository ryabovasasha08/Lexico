pluginManagement {
	repositories {
		mavenCentral()
		google()
		gradlePluginPortal()
	}
}

plugins {
	id("de.fayard.refreshVersions") version "0.60.5"
}

buildscript {
	repositories { gradlePluginPortal() }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
