plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.formation.composecourse"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.formation.composecourse"
        minSdk = 30
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "default"
    productFlavors {
        create("dev") {
            dimension = "default"
            buildConfigField(
                "String",
                "TMDP_API_KEY",
                "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3YmEwNDEzNWFmZTJmZGIyMzRlZmY1YzZlZTNmZjRhNCIsIm5iZiI6MTY0MzYxODQ4MC43NCwic3ViIjoiNjFmN2EwYjAwNzIxNjYwMDQzMTcyNzVjIiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.D0GyMqDNbXwMjYF8i1m19Blvk_qtZMid1ZhH3RcdWuQ\""
            )
            buildConfigField("String", "TMDP_BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField(
                "String",
                "TMDP_IMAGE_BASE_URL",
                "\"https://image.tmdb.org/t/p/w220_and_h330_face\""
            )
            buildConfigField(
                "String",
                "TMDP_BACKDROP_IMAGE_BASE_URL",
                "\"https://image.tmdb.org/t/p/original\""
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.bundles.androidx)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.androidx.activity.compose)

    implementation(libs.bundles.hilt)
    ksp(libs.hilt.compiler)

    implementation(platform(libs.ktor.bom))
    implementation(libs.bundles.ktor)

    implementation(libs.coil)
}
