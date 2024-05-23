pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "blockfrost-kotlin-sdk"

include(":sdk")
include(":composeApp")
// Set the directory for each module
project(":composeApp").projectDir = file("example/composeApp")
