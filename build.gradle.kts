buildscript {
    dependencies {
        classpath("ru.astrainteractive.gradleplugin:convention:0.5.1")
        classpath("ru.astrainteractive.gradleplugin:android:0.5.1")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.gradle.buildconfig) apply false
    alias(libs.plugins.gradle.sqldelight) apply false
    alias(libs.plugins.gradle.moko.resources.generator) apply false
}
apply(plugin = "ru.astrainteractive.gradleplugin.detekt")

/**
 * This function will delete every ./build folder
 * ./gradlew :cleanProject
 */
tasks.register("cleanProject", Delete::class) {
    fun clearProject(project: Project) {
        project.childProjects.values.forEach(::clearProject)
        delete(project.buildDir)
    }
    clearProject(rootProject)
}
