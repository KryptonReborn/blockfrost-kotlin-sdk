package plugins

import BuildModules
import com.android.build.api.dsl.ApplicationExtension
import extensions.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.get
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class CommonMppComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPlugin("androidApplication").get().get().pluginId)
                apply(libs.findPlugin("kotlinMultiplatform").get().get().pluginId)
                apply(libs.findPlugin("jetbrainsCompose").get().get().pluginId)
                apply(libs.findPlugin("ktlint").get().get().pluginId)
            }

            extensions.configure<ApplicationExtension> {
                namespace = "org.example.project"
                compileSdk = libs.findVersion("androidCompileSdk").get().displayName.toInt()

                defaultConfig {
                    applicationId = "org.example.project"
                    minSdk = libs.findVersion("androidMinSdk").get().displayName.toInt()
                    targetSdk = libs.findVersion("androidTargetSdk").get().displayName.toInt()
                    versionCode = 1
                    versionName = "1.0"
                }

                sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
                sourceSets["main"].res.srcDirs("src/androidMain/res")
                sourceSets["main"].resources.srcDirs("src/commonMain/resources")

                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                    }
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }
            }
            extensions.configure<KotlinMultiplatformExtension> {
                androidTarget {
                    compilations.all {
                        kotlinOptions {
                            jvmTarget = "17"
                        }
                    }
                }
                listOf(
                    iosX64(),
                    iosArm64(),
                    iosSimulatorArm64()
                ).forEach { iosTarget ->
                    iosTarget.binaries.framework {
                        baseName = "ComposeApp"
                        isStatic = true
                    }
                }
                sourceSets.apply {
                    commonMain.get().dependencies {
                        implementation(libs.findLibrary("composeRuntime").get())
                        implementation(libs.findLibrary("composeFoundation").get())
                        implementation(libs.findLibrary("composeMaterial").get())
                        implementation(libs.findLibrary("composeUi").get())
                        implementation(libs.findLibrary("composeComponentsResources").get())
                        implementation(libs.findLibrary("composeNavigation").get())
                    }
                    androidMain.get().dependencies {
                        implementation(libs.findLibrary("androidxActivityCompose").get())
                    }
                }
            }
        }
    }
}