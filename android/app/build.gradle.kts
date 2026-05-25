plugins {
    id("com.android.application")
    id("kotlin-android")
    // The Flutter Gradle Plugin must be applied after the Android and Kotlin Gradle plugins.
    id("dev.flutter.flutter-gradle-plugin")
}

android {
    namespace = "com.example.multiverse_canon"
    compileSdk = flutter.compileSdkVersion
    ndkVersion = flutter.ndkVersion

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    defaultConfig {
        // TODO: Specify your own unique Application ID (https://developer.android.com/studio/build/application-id.html).
        applicationId = "com.example.multiverse_canon"
        // You can update the following values to match your application needs.
        // For more information, see: https://flutter.dev/to/review-gradle-config.
        minSdk = flutter.minSdkVersion
        targetSdk = flutter.targetSdkVersion
        versionCode = flutter.versionCode
        versionName = flutter.versionName
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }
        create("qa"){
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            // TODO: Add your own signing config for the release build.
            // Signing with the debug keys for now, so `flutter run --release` works.
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    flavorDimensions += "default"
    productFlavors{
        create("development"){
            dimension = "default"
            resValue(
                type = "string",
                name = "app_name",
                value = "Multiverse Canon development"
            )
            applicationIdSuffix = ".development"
        }
        create("staging"){
            dimension = "default"
            resValue(
                type = "string",
                name = "app_name",
                value = "Multiverse Canon staging"
            )
            applicationIdSuffix = ".staging"
        }
        create("production"){
            dimension = "default"
            resValue(
                type = "string",
                name = "app_name",
                value = "Multiverse Canon production"
            )
            applicationIdSuffix = ".production"
        }
    }
}

flutter {
    source = "../.."
}
