import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnLockMismatchReport
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

plugins {
    id(libs.plugins.commonMppLib.get().pluginId)
    id(libs.plugins.commonMppPublish.get().pluginId)
}

publishConfig {
    url = "https://maven.pkg.github.com/KryptonReborn/blockfrost-kotlin-sdk"
    groupId = "dev.kryptonreborn.blockfrost"
    artifactId = "core"
}

version = "0.0.2"

android {
    namespace = "dev.kryptonreborn.blockfrost.core"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                // ktor
                implementation(libs.ktorClientCore)
                implementation(libs.ktorJson)
                implementation(libs.ktorLogging)
                implementation(libs.ktorSerialization)
                implementation(libs.kotlinxCoroutinesCore)
                implementation(libs.kotlinxSerializationJson)
                implementation(libs.kermit)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.ktorClientOkhttp)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(libs.ktorClientDarwin)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.ktorClientOkhttp)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.ktorClientMock)
                implementation(libs.kotlinxCoroutinesTest)
            }
        }
        val integrationTest by creating {
            dependsOn(commonTest)
            kotlin.srcDir("src/integrationTest/kotlin")
            resources.srcDir("src/integrationTest/resources")
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmTest by getting {
            dependsOn(integrationTest)
        }
        val androidUnitTest by getting {
        }
    }
}

rootProject.plugins.withType<YarnPlugin> {
    rootProject.configure<YarnRootExtension> {
        yarnLockMismatchReport = YarnLockMismatchReport.WARNING
        yarnLockAutoReplace = true
    }
}
