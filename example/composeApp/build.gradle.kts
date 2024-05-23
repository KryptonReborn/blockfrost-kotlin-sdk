plugins {
    alias(libs.plugins.commonMppCompose)
}

ktlint {
    filter {
        exclude("**/generated/**")
        exclude("**/build/**")
        exclude("**/iosMain/**")
    }
}
