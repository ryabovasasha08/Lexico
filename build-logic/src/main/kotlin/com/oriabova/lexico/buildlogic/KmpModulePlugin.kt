package com.oriabova.lexico.buildlogic

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpModulePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            pluginManager.apply("org.jetbrains.kotlin.multiplatform")
            pluginManager.apply("com.android.library")

            val libs = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")

            extensions.configure<KotlinMultiplatformExtension> {
                androidTarget {
                    compilerOptions {
                        jvmTarget.set(JvmTarget.JVM_11)
                    }
                }
                iosArm64()
                iosSimulatorArm64()

                sourceSets.named("commonMain").configure {
                    dependencies {
                        implementation(libs.findLibrary("kotlinx.coroutines.core").get())
                    }
                }
                sourceSets.named("commonTest").configure {
                    dependencies {
                        implementation(libs.findLibrary("kotlin.test").get())
                    }
                }
            }

            extensions.configure<LibraryExtension> {
                namespace = namespaceFromPath(path)
                compileSdk = libs.findVersion("android.compileSdk").get().requiredVersion.toInt()
                defaultConfig {
                    minSdk = libs.findVersion("android.minSdk").get().requiredVersion.toInt()
                }
            }
        }
    }

    private fun namespaceFromPath(projectPath: String): String {
        val segments = projectPath
            .removePrefix(":")
            .split(":")
            .filter { it.isNotBlank() }
            .map { it.lowercase() }
        val suffix = segments.joinToString(".")
        return if (suffix.isBlank()) {
            "com.oriabova.lexico"
        } else {
            "com.oriabova.lexico.$suffix"
        }
    }
}
