plugins {
    alias(libs.plugins.commonMppCompose)
}

kotlin {
    dependencies {
        implementation(project(BuildModules.BLOCKFROST_SDK))
    }
}
ktlint {
    filter {
        exclude("**/generated/**")
        exclude("**/build/**")
        exclude("**/iosMain/**")
    }
}
