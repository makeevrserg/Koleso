@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.util.ProjectProperties.projectInfo


plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    js(IR) {
        moduleName = "web"
        browser {
            useCommonJs()
            commonWebpackConfig {
                outputFileName = "web.js"
            }
        }
        binaries.executable()
    }

    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                // Compose
                implementation(compose.material3)
                // Decompose
                implementation(libs.decompose.core)
                implementation(libs.decompose.compose.jetbrains)
                // klibs
                implementation(libs.klibs.kdi)
                implementation(libs.klibs.mikro.platform)
                // Coroutines
                implementation(libs.kotlin.coroutines.core)
                // Resources
                implementation(libs.moko.resources.core)
                // Local
                implementation(projects.modules.services.core)
                implementation(projects.modules.services.coreUi)
                implementation(projects.modules.features.root)
                implementation(projects.modules.services.dbApi)
                implementation(projects.modules.services.resources)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
            }
        }
    }
}

compose.experimental {
    web.application {}
}

multiplatformResources {
    resourcesPackage.set("${projectInfo.group}.app")
}
