application {
    mainClass.set("com.example.ParseJournalKt")
    // Needed for ShadowJar
    @Suppress("DEPRECATION")
    mainClassName = "com.example.ParseJournalKt"
}

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.20"
    id("com.github.johnrengelman.shadow") version "5.2.0"
    application
}

repositories {
    jcenter()
}

dependencies {
    implementation("com.vladsch.flexmark:flexmark-all:0.62.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}