## KmpMvi

This is a sample project that demonstrates the use of the MVI pattern in Kotlin Multiplatform.

#### Building for Android

`./gradlew :app-android:assembleDebug`

#### Building for iOS

First build the Framework:

- For iOS simulator: `./gradlew :shared:kittens:iosX64MainBinaries`
- For iOS device: `./gradlew :shared:kittens:iosArm64MainBinaries`

Then open the Xcode project located in the `app-ios` folder.

#### Related articles:
- MVI in Kotlin Multiplatform — part 1 (1 of 3) ([en](https://medium.com/@arkann1985/mvi-in-kotlin-multiplatform-part-1-1-of-3-205c6feb4ac7), [ru](https://habr.com/ru/company/badoo/blog/501968/))
