plugins {
    `kotlin-dsl`
}

group = "com.oriabova.lexico.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        create("kmpModule") {
            id = "com.oriabova.lexico.kmpModule"
            implementationClass = "com.oriabova.lexico.buildlogic.KmpModulePlugin"
        }
    }
}
