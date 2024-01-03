@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.util.ProjectProperties.projectInfo


plugins {
    id("org.jetbrains.compose")
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
    id("ru.astrainteractive.gradleplugin.android.compose")
}
kotlin {
    jvm()
    androidTarget()
    targetHierarchy.default()
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Compose
                implementation(compose.material)
                implementation(compose.material3)
                // Decompose
                implementation(libs.decompose.core)
                implementation(libs.decompose.compose.jetbrains)
                // klibs
                implementation(libs.klibs.kdi)
                // Coroutines
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.kotlin.coroutines.swing)
                // Local
                implementation(projects.modules.services.core)
                implementation(projects.modules.services.coreUi)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "${projectInfo.group}.core.ui"
}
