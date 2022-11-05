val ktor_version: String by project
val logback_version: String by project

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
                implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
                implementation("io.ktor:ktor-server-auth-jvm:$ktor_version")
                implementation("io.ktor:ktor-server-cors-jvm:$ktor_version")
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-cio:$ktor_version")
                implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
                implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
                implementation("io.ktor:ktor-serialization-gson-jvm:$ktor_version")
                implementation("io.ktor:ktor-server-auth-jwt-jvm:$ktor_version")
                implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
                implementation("ch.qos.logback:logback-classic:$logback_version")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
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