plugins {
    kotlin("multiplatform") version "1.7.20"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.7.20"
    application
}

group = "tech.mrgreener"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    sourceSets {
        val exposedVersion: String by project
        val h2Version: String by project
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-netty:2.0.2")
                implementation("io.ktor:ktor-server-html-builder-jvm:2.0.2")
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.2")
                implementation("org.hibernate:hibernate-core:6.1.5.Final")
                implementation("org.postgresql:postgresql:42.5.0")

                implementation("org.slf4j:slf4j-api:1.7.25")
                implementation("org.slf4j:slf4j-log4j12:1.7.5")
            }
        }
        val jvmTest by getting
    }
}

application {
    mainClass.set("tech.mrgreener.application.ServerKt")
}

tasks.named<JavaExec>("run") {
    dependsOn(tasks.named<Jar>("jvmJar"))
    classpath(tasks.named<Jar>("jvmJar"))
}