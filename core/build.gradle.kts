import com.android.build.gradle.internal.tasks.factory.dependsOn
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
                kotlin.srcDir("build/generated/kotlin")
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
        val commonTest by getting {
            dependencies {
                implementation(libs.ktorClientMock)
                implementation(libs.kotlinxCoroutinesTest)
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktorClientOkhttp)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.ktorClientCio)
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(libs.ktorClientJs)
            }
        }
        val appleMain by getting {
            dependencies {
                implementation(libs.ktorClientIos)
            }
        }
        val linuxX64Main by getting {
            dependencies {
                implementation(libs.ktorClientCio)
            }
        }
        val linuxArm64Main by getting {
            dependencies {
                implementation(libs.ktorClientCio)
            }
        }
        val mingwX64Main by getting {
            dependencies {
                implementation(libs.ktorClientWinhttp)
            }
        }
    }
}

rootProject.plugins.withType<YarnPlugin> {
    rootProject.configure<YarnRootExtension> {
        yarnLockMismatchReport = YarnLockMismatchReport.WARNING
        yarnLockAutoReplace = true
    }
}

fun generateBuildConfigFile(
    outputDir: String,
    fields: List<Triple<String, String, Any?>>,
) {
    val outputFile = file("$outputDir/BuildConfig.kt")
    if (!outputFile.exists()) {
        outputFile.parentFile.mkdirs()
        outputFile.createNewFile()
    }

    val content =
        buildString {
            appendLine("package dev.kryptonreborn.blockfrost")
            appendLine()
            appendLine("object BuildConfig {")
            for ((name, type, value) in fields) {
                appendLine("    val $name: $type = $value")
            }
            appendLine("}")
        }
    outputFile.writeText(content)
}

tasks.register("updateBuildConfig") {
    doLast {
        val fields =
            listOf(
                Triple("IS_CI", "Boolean", System.getenv("CI").toBoolean()),
            )
        val outputDir = "build/generated/kotlin/dev/kryptonreborn/blockfrost"
        generateBuildConfigFile(outputDir, fields)
    }
}
kotlin.targets.all {
    compilations.all {
        compileTaskProvider.dependsOn(tasks.named("updateBuildConfig"))
    }
}
