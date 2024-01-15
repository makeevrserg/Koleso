@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.util.ProjectProperties.projectInfo


plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("dev.icerock.mobile.multiplatform-resources")
}

val copyJsResources = tasks.create("copyJsResourcesWorkaround", Copy::class.java) {
    from(projects.modules.services.resources.dependencyProject.file("build/generated/moko-resources/jsMain/res"))
    into("build/js/packages/web/kotlin")
    into(rootProject.file("build/js/packages/web/kotlin"))
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

    val jsBrowserDevelopmentExecutableDistributeResources by tasks.getting {
        finalizedBy(copyJsResources)
    }

    val jsProductionExecutableCompileSync by tasks.getting {
        finalizedBy(copyJsResources)
    }

    val jsBrowserProductionExecutableDistributeResources by tasks.getting {
        finalizedBy(copyJsResources)
    }

    val jsBrowserProductionWebpack by tasks.getting {
        dependsOn(copyJsResources)
        mustRunAfter(copyJsResources)
    }

    val jsBrowserDevelopmentWebpack by tasks.getting {
        dependsOn(copyJsResources)
        mustRunAfter(copyJsResources)
    }

    projects.modules.services.resources.dependencyProject.tasks.getByName("generateMRjsMain") {
        copyJsResources.dependsOn(this)
        copyJsResources.mustRunAfter(this)
    }
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
