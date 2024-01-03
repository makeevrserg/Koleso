@file:Suppress("UnusedPrivateMember")

import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import ru.astrainteractive.gradleplugin.util.ProjectProperties.projectInfo


plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("ru.astrainteractive.gradleplugin.java.core")
}
kotlin {
    jvm {
        withJava()
    }
    targetHierarchy.default()
    sourceSets {
        val jvmMain by getting {
            resources.srcDirs("build/generated/moko/jvmMain/src")
            dependencies {
                // Compose
                implementation(compose.desktop.currentOs)
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
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "${projectInfo.group}.desktop.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "jvm"
            packageVersion = projectInfo.versionString
        }
    }
}
