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
- MVI in Kotlin Multiplatform — part 1 (1 of 3) ([en](https://badootech.badoo.com/mvi-in-kotlin-multiplatform-part-1-1-of-3-205c6feb4ac7?source=friends_link&sk=0fbf033393db72797fcbb6b0e0d5c320), [ru](https://habr.com/ru/company/badoo/blog/501968/))
- MVI in Kotlin Multiplatform — part 2 (2 of 3) ([en](https://badootech.badoo.com/mvi-in-kotlin-multiplatform-part-2-2-of-3-3faab535de02?source=friends_link&sk=a7a347e49202e139d5cd7533d2a97141), [ru](https://habr.com/ru/company/badoo/blog/510304/))
