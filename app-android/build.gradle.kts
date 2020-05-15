plugins {
    id("com.android.application")
    kotlin("android")
}

setupAndroidSdkVersions()

android {
    defaultConfig {
        applicationId = "com.badoo.kmpmvi.app.android"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    packagingOptions {
        exclude("META-INF/reaktive_debug.kotlin_module")
        exclude("META-INF/kotlinx-serialization-runtime.kotlin_module")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(Deps.Jetbrains.Kotlin.StdLib.Jdk7)
    implementation(Deps.AndroidX.AppCompat.AppCompat)
    implementation(Deps.AndroidX.RecyclerView.RecyclerView)
    implementation(Deps.AndroidX.ConstraintLayout.ConstraintLayout)
    implementation(Deps.AndroidX.SwypeRefreshLayout.SwypeRefreshLayout)
    implementation(Deps.AndroidX.Core.Ktx)
    implementation(Deps.Google.Android.Material.Material)
    implementation(Deps.Squareup.Picasso.Picasso)
    implementation(project(":shared:kittens"))
}

