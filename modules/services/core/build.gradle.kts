@file:Suppress("UnusedPrivateMember")

plugins {
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.core")
}

kotlin {
    jvm()
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
