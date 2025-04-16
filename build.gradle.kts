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
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.mindrot:jbcrypt:0.4")
    implementation ("org.jline:jline:3.23.0")

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
