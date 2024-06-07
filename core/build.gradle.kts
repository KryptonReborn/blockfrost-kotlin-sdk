import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import extensions.getLocalProperty
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnLockMismatchReport
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension
import javax.xml.parsers.DocumentBuilderFactory

plugins {
    id(libs.plugins.commonMppLib.get().pluginId)
    id(libs.plugins.commonMppPublish.get().pluginId)
    id(libs.plugins.buildKonfig.get().pluginId)
    id("org.jetbrains.kotlinx.kover") version "0.8.0"
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
                implementation(libs.ktorClientOkhttp)
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

buildkonfig {
    packageName = "dev.kryptonreborn.blockfrost.buildKonfig"
    defaultConfigs {
        buildConfigField(BOOLEAN, "IS_CI", isCiEnv())
        buildConfigField(STRING, "PROJECT_ID", getLocalProperty("projectId") ?: "<your project id>")
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.targets.native.tasks.KotlinNativeSimulatorTest> {
    if (!isCiEnv().toBoolean()) {
        standalone.set(false)
        device.set("your device ios simulator id")
    }
}

ktlint {
    filter {
        exclude("**/buildKonfig/**")
    }
}

tasks.dokkaHtml {
    outputDirectory.set(layout.buildDirectory.dir("documentation/html"))
}

tasks.register("printLineCoverage") {
    group = "verification" // Put into the same group as the `kover` tasks
    dependsOn("koverXmlReport")
    doLast {
        val report = file("$buildDir/reports/kover/report.xml")

        val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(report)
        val rootNode = doc.firstChild
        var childNode = rootNode.firstChild

        var coveragePercent = 0.0

        while (childNode != null) {
            if (childNode.nodeName == "counter") {
                val typeAttr = childNode.attributes.getNamedItem("type")
                if (typeAttr.textContent == "LINE") {
                    val missedAttr = childNode.attributes.getNamedItem("missed")
                    val coveredAttr = childNode.attributes.getNamedItem("covered")

                    val missed = missedAttr.textContent.toLong()
                    val covered = coveredAttr.textContent.toLong()

                    coveragePercent = (covered * 100.0) / (missed + covered)

                    break
                }
            }
            childNode = childNode.nextSibling
        }

        println("%.1f".format(coveragePercent))
    }
}

fun isCiEnv() = System.getenv()["CI"] ?: "false"
