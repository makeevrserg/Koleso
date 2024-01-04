@file:Suppress("UnusedPrivateMember")

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}
val copyJsResources = tasks.create("copyJsResourcesWorkaround", Copy::class.java) {
    from( projects.modules.services.resources.dependencyProject.file("build/generated/libres/js/resources"))
    into("build/processedResources/js/main")
}

project.evaluationDependsOn(projects.modules.services.resources.dependencyProject.path)

afterEvaluate {
    val jsDevelopmentExecutableCompileSync by tasks.getting {
        dependsOn(copyJsResources)
        mustRunAfter(copyJsResources)
    }

    val jsProcessResources by tasks.getting {
        finalizedBy(copyJsResources)
    }

    projects.modules.services.resources.dependencyProject.tasks.getByName("libresGenerateImages") {
        finalizedBy(copyJsResources)
    }
}

kotlin {
    js(IR) {
        moduleName = "web"
        browser {
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
                // Coroutines
                implementation(libs.kotlin.coroutines.core)
                // Local
                implementation(projects.modules.services.core)
                implementation(projects.modules.services.coreUi)
                implementation(projects.modules.features.root)
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
