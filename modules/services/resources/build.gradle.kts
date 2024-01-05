@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.util.ProjectProperties.projectInfo

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
    alias(libs.plugins.gradle.libres)
}

kotlin {
    js {
        browser()
    }
    jvm()
    androidTarget()
    targetHierarchy.default()
}

android {
    namespace = "${projectInfo.group}.resources"
    sourceSets {
        getByName("main").java.srcDirs("build/generated/moko/androidMain/src")
    }
}

libres {
    generatedClassName = "MR"
    generateNamedArguments = true
    baseLocaleLanguageCode = "en"
    camelCaseNamesForAppleFramework = false
}
