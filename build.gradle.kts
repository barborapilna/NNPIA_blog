/*
 * This file was generated by the Gradle "init" task.
 */

plugins {
    id("org.springframework.boot") version "2.4.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("com.github.node-gradle.node") version "3.0.1"
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.5.0")
    implementation("org.springframework.boot:spring-boot-starter-security:2.5.0")
    implementation("org.springframework.boot:spring-boot-starter-web:2.5.0")
    implementation("org.springframework.boot:spring-boot-starter-data-rest:2.4.5")
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("io.jsonwebtoken:jjwt:0.9.0")
    implementation("org.springframework.boot:spring-boot-starter:2.5.0")
    implementation("org.seleniumhq.selenium:selenium-java:3.141.59")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("com.h2database:h2:1.4.199")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.apache.commons:commons-lang3:3.9")
    testImplementation("commons-beanutils:commons-beanutils:1.7.0")
    implementation("com.jayway.jsonpath:json-path:2.4.0")
    runtimeOnly("org.springframework.boot:spring-boot-devtools:2.4.5")
    testImplementation("org.springframework.security:spring-security-test:5.4.6")
}

group = "upce.nnpia"
version = "0.0.1-SNAPSHOT"
description = "blog"
java.targetCompatibility = JavaVersion.VERSION_11
java.sourceCompatibility = JavaVersion.VERSION_11

project.exec {
    commandLine = "rm -rf frontend 2>1 1>/dev/null | true".split(" ")
}

project.exec {
    commandLine = "git clone https://github.com/barborapilna/NNPIA_blog_fe.git frontend".split(" ")
}

tasks {

    register<com.github.gradle.node.npm.task.NpmTask>("appNpmInstall") {
        description = "Installs all dependencies from package.json"
        workingDir.set(file("${project.projectDir}/frontend"))
        args.set(listOf("install"))
    }

    register<com.github.gradle.node.npm.task.NpmTask>("appNpmBuild") {
        dependsOn("appNpmInstall")
        description = "Builds project"
        workingDir.set(file("${project.projectDir}/frontend"))
        args.set(listOf("run", "build"))
    }

    register<Copy>("copyWebApp") {
        dependsOn("appNpmBuild")
        description = "Copies built project to where it will be served"
        from("./frontend/build")
        into("build/resources/main/static/.")
    }

    node {
        download.set(true)
        version.set("12.18.3")
        npmVersion.set("")
        workDir.set(file("${project.buildDir}/nodejs"))
        npmWorkDir.set(file("${project.buildDir}/npm"))
    }

    withType<JavaCompile> {
        dependsOn("copyWebApp")
    }

    withType<Test> {
        useJUnitPlatform()
    }
}


