plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.mkulikov.taskb.di"
    compileSdk = 36

    defaultConfig {
        minSdk = 28
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    api(libs.koin.android)
    api(libs.koin.androidx.compose)
}