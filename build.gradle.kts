plugins {
    kotlin("jvm") version "1.9.10"
    application
    id("org.openjfx.javafxplugin") version "0.0.13"
}

group = "com.hirad"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}

javafx {
    version = "17"
    modules = listOf("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("com.hirad.genus.Main")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}
