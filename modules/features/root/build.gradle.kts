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
    js {
        browser()
    }
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
                implementation(libs.klibs.mikro.platform)
                implementation(libs.klibs.mikro.core)
                // Coroutines
                implementation(libs.kotlin.coroutines.core)
                // Moko-resources
                implementation(libs.moko.resources.core)
                implementation(libs.moko.resources.compose)
                // Local
                implementation(projects.modules.services.core)
                implementation(projects.modules.services.coreUi)
                implementation(projects.modules.services.resources)
                implementation(projects.modules.services.dbApi)
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
    apply(plugin = "kotlin-parcelize")
    namespace = "${projectInfo.group}.core.ui"
}
