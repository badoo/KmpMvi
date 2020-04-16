plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
}

dependencies {
    implementation(Deps.Jetbrains.Kotlin.Plugin.Gradle)
    implementation(Deps.Jetbrains.Kotlin.Plugin.Serialization)
    implementation(Deps.Android.Tools.Build.Gradle)
    implementation(Deps.TouchLab.KotlinXcodeSync)
    implementation(Deps.Squareup.SqlDelight.GradlePlugin)
}

kotlin {
    // Add Deps to compilation, so it will become available in main project
    sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}
