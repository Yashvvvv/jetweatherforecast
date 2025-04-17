plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp") // Use KSP instead of kapt
    id("dagger.hilt.android.plugin") // Apply Hilt plugin
}

import java.util.Properties
import java.io.FileInputStream

// Read local.properties
val localProperties = Properties().apply {
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        load(FileInputStream(localPropertiesFile))
    }
}

android {
    namespace = "app.recruit.jettnote"
    compileSdk = 35

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {

        applicationId = "app.recruit.jettnote"
        minSdk = 33
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        
        // Add API key to BuildConfig
        buildConfigField("String", "WEATHER_API_KEY", "\"${localProperties.getProperty("WEATHER_API_KEY", "")}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
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
    }
}

dependencies {
    implementation(libs.androidx.navigation.compose)
    //ksp
    ksp(libs.androidx.room.compiler)

    //hilt using ksp
    implementation(libs.hilt.android)
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")
    ksp(libs.hilt.compiler)

    //room using ksp
    implementation(libs.androidx.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.androidx.room.ktx)

    //Coroutines
    implementation(libs.kotlinx.coroutines.android)

    //Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    //Retrofit
    implementation (libs.retrofit)

    //OkHttp
    implementation(libs.okhttp)

    //Material3
    implementation(libs.material3)

    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
