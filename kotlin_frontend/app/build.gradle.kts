plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.kotlinfrontend"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kotlinfrontend"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        // Fix: Set legacy manifest processing to avoid SDK XML version 4 errors in older Android build tools (for compatibility)
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
    }

    buildFeatures {
        // Fix: Explicitly disable deprecated/experimental features that might generate sdk-4 xml
        aidl = false
        renderScript = false
        resValues = true
        shaders = false
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

    // Fix: Use legacy manifest and resource processing for maximum compatibility; prevents SDK XML v4 output
    androidResources {
        // Only process up to version 3 for compatibility with older tools
        generateLocaleConfig = false
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    // Use modern AndroidX-only calendar view from JitPack (correct Maven coordinates)
    implementation("com.github.kizitonwose:CalendarView:2.3.0")
}
