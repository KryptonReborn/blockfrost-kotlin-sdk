plugins {
    alias(libs.plugins.commonMppCompose)
}
kotlin {
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(libs.ktorClientOkhttp)
                implementation(libs.kotlinxCoroutinesAndroid)
            }
        }
        val commonMain by getting {
            dependencies {
                implementation(project(BuildModules.BLOCKFROST_SDK))
                implementation(libs.koinCore)
                api(libs.koinCompose)
                implementation(libs.ktorClientCore)
                implementation(libs.kotlinxCoroutinesCore)
                implementation(libs.androidxLifecycleViewmodelCompose)
            }
        }
    }
}

ktlint {
    filter {
        exclude("**/generated/**")
        exclude("**/build/**")
        exclude("**/iosMain/**")
    }
}
