plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.mkulikov.taskb.feature.task_data"
    compileSdk = 36

    defaultConfig {
        minSdk = 28
    }
}

dependencies {
    implementation(project(":base"))

    implementation(libs.room.runtime)
    implementation(libs.kotlinx.datetime)

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui.graphics)
}