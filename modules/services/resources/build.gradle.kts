@file:Suppress("UnusedPrivateMember")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import ru.astrainteractive.gradleplugin.util.ProjectProperties.projectInfo
import ru.astrainteractive.gradleplugin.util.ProjectProperties.jinfo

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("dev.icerock.mobile.multiplatform-resources")
    // TODO not working with moko-resources
//    id("ru.astrainteractive.gradleplugin.java.core")
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
                implementation(libs.moko.resources.core)
            }
        }
    }
}

android {
    namespace = "${projectInfo.group}.resources"
}

multiplatformResources {
    resourcesPackage.set("${projectInfo.group}.resources")
}

tasks
    .withType<KotlinCompile>()
    .configureEach {
        kotlinOptions.jvmTarget = jinfo.ktarget.majorVersion
    }

configure<JavaPluginExtension> {
    sourceCompatibility = jinfo.jsource
    targetCompatibility = jinfo.jtarget
}
