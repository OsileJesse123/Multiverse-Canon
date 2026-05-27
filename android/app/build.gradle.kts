import java.util.Properties

plugins {
    id("com.android.application")
    // START: FlutterFire Configuration
    id("com.google.gms.google-services")
    // END: FlutterFire Configuration
    id("kotlin-android")
    // The Flutter Gradle Plugin must be applied after the Android and Kotlin Gradle plugins.
    id("dev.flutter.flutter-gradle-plugin")
}

base {
    archivesName.set("multiverse_canon_v${flutter.versionName}_${flutter.versionCode}")
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

    val keystoreProperties = Properties()
    val keystorePropertiesFile = rootProject.file("key.properties")

    if(keystorePropertiesFile.exists()){
        keystoreProperties.load(keystorePropertiesFile.inputStream())
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

    signingConfigs {
        create("release"){
            keyAlias = keystoreProperties.getProperty("keyAlias")
            keyPassword = keystoreProperties.getProperty("keyPassword")
            storePassword = keystoreProperties.getProperty("storePassword")

            val storeFilePath = keystoreProperties.getProperty("storeFile")
            if(!storeFilePath.isNullOrEmpty()){
                storeFile = file(storeFilePath)
            }
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
        }
        create("qa"){
            isMinifyEnabled = true
            isDebuggable = false
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            signingConfig = signingConfigs.getByName("release")
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
