plugins {
    kotlin("jvm")
    application
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.squareup:kotlinpoet:1.4.3")
}

application {
    mainClassName = "io.islandtime.codegen.MainKt"
}