package de.psegroup.convention.ktx

import org.gradle.api.artifacts.Dependency
import org.gradle.kotlin.dsl.DependencyHandlerScope

internal fun DependencyHandlerScope.implementation(notation: Any): Dependency? = add("implementation", notation)

internal fun DependencyHandlerScope.debugImplementation(notation: Any): Dependency? = add("debugImplementation", notation)

internal fun DependencyHandlerScope.testImplementation(notation: Any): Dependency? = add("testImplementation", notation)

internal fun DependencyHandlerScope.androidTestImplementation(notation: Any): Dependency? = add("androidTestImplementation", notation)

internal fun DependencyHandlerScope.coreLibraryDesugaring(notation: Any): Dependency? = add("coreLibraryDesugaring", notation)

internal fun DependencyHandlerScope.lintPublish(notation: Any): Dependency? = add("lintPublish", notation)

internal fun DependencyHandlerScope.ksp(notation: Any): Dependency? = add("ksp", notation)

internal fun DependencyHandlerScope.kapt(notation: Any): Dependency? = add("kapt", notation)

internal fun DependencyHandlerScope.kaptTest(notation: Any): Dependency? = add("kaptTest", notation)