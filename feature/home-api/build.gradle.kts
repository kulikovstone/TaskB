import org.gradle.kotlin.dsl.dependencies

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.mkulikov.taskb.feature.home.api"
    compileSdk = 36

    defaultConfig {
        minSdk = 28
    }
}

dependencies {
    api(project(":core:navigation"))
}