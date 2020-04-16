setupMultiplatform()

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Deps.Badoo.Reaktive.Utils)
                implementation(Deps.Badoo.Reaktive.Reaktive)
            }
        }
    }
}
