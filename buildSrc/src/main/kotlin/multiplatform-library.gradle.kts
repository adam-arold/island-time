import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType
import org.jetbrains.kotlin.konan.target.HostManager

plugins {
    kotlin("multiplatform")
    id("published-library")
    jacoco
}

val ideaActive get() = System.getProperty("idea.active") == "true"

kotlin {
    jvm()

    val darwinTargets = if (ideaActive) {
        listOf(iosX64("darwin"))
    } else {
        listOf(iosArm64(), iosX64(), macosX64(), watchosArm64(), watchosX86(), tvosArm64(), tvosX64())
    }

    sourceSets {
        all {
            languageSettings.apply {
                enableLanguageFeature("InlineClasses")
                useExperimentalAnnotation("kotlin.Experimental")
                progressiveMode = true
            }
        }

        if (!ideaActive) {
            val commonMain by getting
            val commonTest by getting

            val darwinMain by creating {
                dependsOn(commonMain)
            }

            val darwinTest by creating {
                dependsOn(commonTest)
            }

            configure(darwinTargets) {
                compilations["main"].defaultSourceSet.dependsOn(darwinMain)
                compilations["test"].defaultSourceSet.dependsOn(darwinTest)
            }
        }
    }

    configure(darwinTargets) {
        compilations["main"].kotlinOptions.freeCompilerArgs += "-Xobjc-generics"
    }
}

if (HostManager.hostIsMac) {
    registerIosTestTask(if (ideaActive) "darwin" else "iosX64")
}

val emptySourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
}

publishing {
    val pomMppArtifactId: String? by project

    publications.withType<MavenPublication>().configureEach {
        if (name == "kotlinMultiplatform") {
            if (pomMppArtifactId != null) {
                artifactId = pomMppArtifactId
            }
            artifact(emptySourcesJar.get())
        } else {
            if (pomMppArtifactId != null) {
                artifactId = "${pomMppArtifactId}-$name"
            }
        }
    }
}

afterEvaluate {
    tasks.withType<DokkaTask>().configureEach {
        multiplatform {
            kotlin.targets.matching { it.name != "metadata" }.forEach { create(it.name) }
        }
    }

    tasks.withType<JacocoReport>().configureEach {
        classDirectories.setFrom(
            fileTree("${buildDir}/classes/kotlin/jvm/") {
                exclude("**/*Test*.*")
            }
        )

        sourceDirectories.setFrom(kotlin.sourceSets["commonMain"].kotlin.sourceDirectories)
        executionData.setFrom("${buildDir}/jacoco/jvmTest.exec")
    }
}

fun registerIosTestTask(iosTargetName: String) {
    val iosTest by tasks.registering(RunIosTestsTask::class) {
        val kotlinNativeTarget = kotlin.targets[iosTargetName] as KotlinNativeTarget
        val testBinariesTaskName = kotlinNativeTarget.compilations[SourceSet.TEST_SOURCE_SET_NAME].binariesTaskName
        dependsOn(tasks.named(testBinariesTaskName))
        binary = kotlinNativeTarget.binaries.getTest(NativeBuildType.DEBUG).outputFile
    }

    tasks.named(LifecycleBasePlugin.CHECK_TASK_NAME) {
        dependsOn(iosTest)
    }
}