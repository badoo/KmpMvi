plugins {
    kotlin("plugin.serialization")
}

setupMultiplatform()

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":shared:mvi"))
                implementation(Deps.Badoo.Reaktive.Utils)
                implementation(Deps.Badoo.Reaktive.Reaktive)
                implementation(Deps.Badoo.Reaktive.Reaktive)
                implementation(Deps.Jetbrains.Kotlinx.Serialization.RuntimeCommon)
            }
        }

        androidMain {
            dependencies {
                implementation(Deps.Jetbrains.Kotlinx.Serialization.Runtime)
            }
        }

        iosCommonMain {
            dependencies {
                implementation(Deps.Jetbrains.Kotlinx.Serialization.RuntimeNative)
            }
        }
    }
}
