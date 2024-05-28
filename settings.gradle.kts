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

include(":core")
// Include the 'example' directory and any specific projects within it
include(":example")
include(":composeApp") // Assuming 'composeApp' is a project under 'example'

// Optionally, specify project directories if they are not located directly under the named folder
project(":composeApp").projectDir = file("example/composeApp")
