@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.util.ProjectProperties.projectInfo


plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
    alias(libs.plugins.gradle.sqldelight)
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
                implementation(libs.klibs.mikro.platform)
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
        val jsMain by getting {
            dependencies {
                implementation(libs.sqldelight.driver.webworker)
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
