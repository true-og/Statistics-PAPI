plugins {
    id("java")
    id("java-library")
    eclipse
}

group = "com.extendedclip.papi.expansion.mcstatistics"
version = "2.0.2"
description = "PlaceholderAPI plugin for Minecraft statistic placeholders"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
        vendor.set(JvmVendorSpec.GRAAL_VM)
    }
}

tasks.named<ProcessResources>("processResources") {
    from("LICENSE") { into("/") }
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.purpurmc.org/snapshots") }
    maven { url = uri("https://repo.helpch.at/releases") }
}

dependencies {
    compileOnly("org.purpurmc.purpur:purpur-api:1.19.4-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.6")
}

apply(from = "eclipse.gradle.kts")

tasks.withType<AbstractArchiveTask>().configureEach {
    isPreserveFileTimestamps = false
    isReproducibleFileOrder = true
}

tasks.withType<Jar>().configureEach {
    archiveBaseName.set(rootProject.name)
    manifest {
        attributes(
            "Implementation-Title" to rootProject.name,
            "Implementation-Version" to project.version,
            "Specification-Title" to rootProject.name,
            "Specification-Version" to project.version,
        )
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release.set(17)
    options.isFork = true
    options.compilerArgs.add("-parameters")
}
