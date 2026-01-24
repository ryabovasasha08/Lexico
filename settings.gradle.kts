rootProject.name = "Lexico"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

includeBuild("build-logic")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":composeApp")

// Libraries
include(":libraries:languageUtils")
include(":libraries:ai")
include(":libraries:designsystem")
include(":libraries:localstorage")
include(":libraries:newwordscheduler")
include(":libraries:notifications")
include(":libraries:permissions")
include(":libraries:serialization")
include(":libraries:setup")
include(":libraries:tts")

// Features
include(":feature:home")
include(":feature:setup")
