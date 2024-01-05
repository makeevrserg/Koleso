@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.util.ProjectProperties.projectInfo


plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
    alias(libs.plugins.gradle.sqldelight)
    alias(libs.plugins.kotlin.serialization)
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
                // kotlin
                implementation(libs.kotlin.serialization.json)
                // klibs
                implementation(libs.klibs.mikro.platform)
                implementation(libs.klibs.mikro.core)
                implementation(libs.klibs.kstorage)
                // settings
                implementation(libs.mppsettings)
                // Coroutines
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.sqldelight.coroutines.extensions)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.sqldelight.driver.android)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.sqldelight.driver.sqlite)
            }
        }
    }
}

android {
    namespace = "${projectInfo.group}.db.api"
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("${projectInfo.group}.db.api")
            generateAsync = true
        }
    }
}
