plugins {
    `multiplatform-library`
}

kotlin {
    jvm {
        withJava()
    }

    sourceSets {
        val commonMain by getting {
            kotlin.srcDirs("src/commonMain/generated")

            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(kotlin("reflect"))
                implementation("co.touchlab:stately:0.9.5")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
                implementation("com.google.truth:truth:1.0")
            }
        }

        val darwinMain by getting {
            dependencies {
                implementation("co.touchlab:stately-collections:0.9.5")
            }
        }
    }
}

tasks.withType<org.jetbrains.dokka.gradle.DokkaTask> {
    doFirst {
        multiplatform {
            create("global") {
                perPackageOption {
                    prefix = "io.islandtime.internal"
                    suppress = true
                }

                perPackageOption {
                    prefix = "io.islandtime.measures.internal"
                    suppress = true
                }

                perPackageOption {
                    prefix = "io.islandtime.parser.internal"
                    suppress = true
                }

                perPackageOption {
                    prefix = "io.islandtime.ranges.internal"
                    suppress = true
                }

                perPackageOption {
                    includes = listOf("packages.md")
                }
            }
        }
    }
}