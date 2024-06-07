# BLOCKFROST KOTLIN SDK

[![GitHub release](https://img.shields.io/badge/release-v0.0.2-blue.svg)](https://github.com/KryptonReborn/blockfrost-kotlin-sdk/releases/tag/v0.0.2) [![Kotlin Version](https://img.shields.io/badge/Kotlin-1.9.23-B125EA?logo=kotlin)](https://kotlinlang.org)
[![Build Status](https://github.com/saschpe/kase64/workflows/Main/badge.svg)](https://github.com/KryptonReborn/blockfrost-kotlin-sdk/actions)
[![License](http://img.shields.io/:License-Apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
![coverage badge](https://img.shields.io/endpoint?url=https://gist.githubusercontent.com/iTanChi/80bcc643522fc574144cb35911894d21/raw/blockfrost-kotlin-sdk-coverage-badge.json)


![badge-android](http://img.shields.io/badge/Platform-Android-brightgreen.svg?logo=android)
![badge-ios](http://img.shields.io/badge/Platform-iOS-orange.svg?logo=apple)
![badge-js](http://img.shields.io/badge/Platform-NodeJS-yellow.svg?logo=javascript)
![badge-jvm](http://img.shields.io/badge/Platform-JVM-red.svg?logo=openjdk)
![badge-linux](http://img.shields.io/badge/Platform-Linux-lightgrey.svg?logo=linux)
![badge-macos](http://img.shields.io/badge/Platform-macOS-orange.svg?logo=apple)
![badge-windows](http://img.shields.io/badge/Platform-Windows-blue.svg?logo=windows)

[//]: # (![badge-tvos]&#40;http://img.shields.io/badge/Platform-tvOS-orange.svg?logo=apple&#41;)

[//]: # (![badge-watchos]&#40;http://img.shields.io/badge/Platform-watchOS-orange.svg?logo=apple&#41;)

This is the template for initializing a repo based on Kotlin Multiplarform

## Download

You must use a personal access token (classic) with the appropriate scopes to publish and install
packages
in [GitHub Packages](https://docs.github.com/en/packages/learn-github-packages/introduction-to-github-packages#authenticating-to-github-packages).

Add the following repository to your settings.gradle.kts file

```kotlin
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/KryptoReborn/blockfrost-kotlin-sdk")
        credentials {
            username = "[your username]"
            password = "[your personal access token]"
        }
    }
}
```

Add the following dependency to your build.gradle.kts file

```build.gradle.kts
dependencies {
    implementation("dev.kryptonreborn.blockfrost-kotlin:blockfrost-kotlin:[version]")
}
```

## Integration

To perform integration tests, you need to obtain a project ID from Blockfrost. Make sure to set this
project ID in your test configurations.

For running tests with the target iosSimulatorArm64, you need to obtain the device ID using the
command:

``` shell
xcrun simctl list
```

Then, update your build.gradle.kts file with the following code to configure the iOS simulator:

``` build.gradle.kts
tasks.withType<org.jetbrains.kotlin.gradle.targets.native.tasks.KotlinNativeSimulatorTest> {
    val isCi = System.getenv()["CI"].toBoolean()
    if (!isCi) {
        standalone.set(false)
        device.set("your device ios simulator id")
    }
}
```

This configuration ensures that the integration tests run on the specified iOS simulator.

