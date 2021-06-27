/*
 * This file was generated by the Gradle "init" task.
 */

plugins {
    id("org.springframework.boot") version "2.4.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("com.github.node-gradle.node") version "3.0.1"
    id ("java")
    id ("maven-publish")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.5.0")
    implementation("org.springframework.boot:spring-boot-starter-security:2.5.0")
    implementation("org.springframework.boot:spring-boot-starter:2.5.0")
    implementation("com.jayway.jsonpath:json-path:2.4.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.5.0")
    implementation("org.postgresql:postgresql")
    implementation("io.jsonwebtoken:jjwt:0.9.0")
    runtimeOnly("org.springframework.boot:spring-boot-devtools:2.4.5")
    runtimeOnly("com.h2database:h2:1.4.200")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.0")
    testImplementation("org.springframework.security:spring-security-test:5.4.6")
    testImplementation("commons-beanutils:commons-beanutils:1.9.3")
    testImplementation("org.apache.commons:commons-lang3:3.12.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.seleniumhq.selenium:selenium-java:3.141.59")
}

group = "upce.nnpia"
version = "0.0.1-SNAPSHOT"
description = "blog"
java.targetCompatibility = JavaVersion.VERSION_11
java.sourceCompatibility = JavaVersion.VERSION_11

tasks.register<com.github.gradle.node.npm.task.NpmTask>("appNpmInstall") {
    description = "Installs all dependencies from package.json"
    workingDir.set(file("${project.projectDir}/../blog-fe"))
    args.set(listOf("install"))
}

tasks.register<com.github.gradle.node.npm.task.NpmTask>("appNpmBuild") {
    dependsOn("appNpmInstall")
    description = "Builds project"
    workingDir.set(file("${project.projectDir}/../blog-fe"))
    args.set(listOf("run", "build"))
}

tasks.register<Copy>("copyWebApp") {
    dependsOn("appNpmBuild")
    description = "Copies built project to where it will be served"
    from("./../blog-fe/build")
    into("build/resources/main/static/.")
}

node {
    download.set(true)
    version.set("12.18.3")
    npmVersion.set("")
    workDir.set(file("${project.buildDir}/nodejs"))
    npmWorkDir.set(file("${project.buildDir}/npm"))
}

tasks.withType<JavaCompile> {
    dependsOn("copyWebApp")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
