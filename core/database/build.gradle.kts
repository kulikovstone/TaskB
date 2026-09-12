plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.mkulikov.taskb.core.database"
    compileSdk = 37

    defaultConfig {
        minSdk = 28
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":feature:task-api"))
    implementation(project(":feature:group-api"))

    implementation(libs.room.runtime)
    ksp(libs.room.compiler)

    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    implementation(libs.kotlinx.datetime)
    implementation(libs.compose.ui.graphics)
}
