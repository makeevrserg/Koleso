@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.util.ProjectProperties.projectInfo


plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
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
                // klibs
                implementation(libs.klibs.mikro.core)
                implementation(libs.klibs.mikro.platform)
                implementation(libs.klibs.kstorage)
                implementation(libs.klibs.kdi)
                // Decompose
                implementation(libs.decompose.core)
                // Coroutines
                implementation(libs.kotlin.coroutines.core)
            }
        }
    }
}

android {
    namespace = "${projectInfo.group}.core"
}
