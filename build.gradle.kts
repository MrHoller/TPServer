plugins {
    `java-library`
    `maven-publish`
    idea
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenLocal()
    maven("https://repo.maven.apache.org/maven2/")
    maven("https://jitpack.io")
    maven("https://repo.opencollab.dev/maven-releases/")
    maven("https://repo.opencollab.dev/maven-snapshots/")
    maven { setUrl("https://jitpack.io") }
}

group = "ru.mrholler"
version = "1.0.2-SNAPSHOT"
description = "TPServer plugin"
java.sourceCompatibility = JavaVersion.VERSION_21

dependencies {
    compileOnly("com.github.PowerNukkitX:PowerNukkitX:357b0ce74a")
    implementation("commons-validator:commons-validator:1.8.0")
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = false
    }
}

java {
    withSourcesJar()
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

sourceSets {
    main {
        resources {
            srcDirs("src/main/resources")
        }
    }
}

tasks.compileJava {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-Xpkginfo:always")
    java.sourceCompatibility = JavaVersion.VERSION_21
    java.targetCompatibility = JavaVersion.VERSION_21
}

tasks.test {
    useJUnitPlatform()
    jvmArgs(listOf("--add-opens", "java.base/java.lang=ALL-UNNAMED"))
    jvmArgs(listOf("--add-opens", "java.base/java.io=ALL-UNNAMED"))
}

tasks.withType<AbstractCopyTask>() {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
