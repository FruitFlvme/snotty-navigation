import org.jetbrains.kotlin.konan.properties.loadProperties
import java.nio.file.NoSuchFileException

plugins {
    id("com.fruitflvme.snotty_navigation.android.application")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.google.services)
    alias(libs.plugins.google.firebase.crashlytics)
    alias(libs.plugins.google.dagger.hilt.android)
}

private val PROPERTIES_FILE_PATH = "app/upload_keystore.properties"
private val PROPERTIES_KEY_ALIAS = "keyAlias"
private val PROPERTIES_KEY_PASSWORD = "keyPassword"
private val PROPERTIES_KEY_STORE_FILE = "storeFile"
private val PROPERTIES_KEY_STORE_PASSWORD = "storePassword"

android {
    namespace = "com.fruitflvme.snotty_navigation"

    signingConfigs {
        create("release") {
            try {
                val uploadKeystoreProperties = loadProperties(PROPERTIES_FILE_PATH)
                storeFile = file(uploadKeystoreProperties.getProperty(PROPERTIES_KEY_STORE_FILE))
                storePassword = uploadKeystoreProperties.getProperty(PROPERTIES_KEY_STORE_PASSWORD)
                keyAlias = uploadKeystoreProperties.getProperty(PROPERTIES_KEY_ALIAS)
                keyPassword = uploadKeystoreProperties.getProperty(PROPERTIES_KEY_PASSWORD)
            } catch (_: NoSuchFileException) {
                // Builds can't be signed for upload to store unless properties file is loaded
            }
        }
    }

    defaultConfig {
        applicationId = "com.fruitflvme.snotty_navigation"
        versionCode = 21030102
        versionName = "1.0.0"

        testInstrumentationRunnerArguments["clearPackageData"] = "true"
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
        }

        release {
            isDebuggable = false
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
    }

    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs +
                "-Xjvm-default=all" +
                "-opt-in=androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi" +
                "-opt-in=com.google.accompanist.permissions.ExperimentalPermissionsApi"
    }
}

dependencies {
    ksp(libs.google.hilt.compiler)

    implementation(project(":model:settings"))
    implementation(project(":ui:core"))
    implementation(project(":ui:design"))
    implementation(project(":ui:location"))
//    implementation(project(":ui:compass"))
//    implementation(project(":ui:sun"))
    implementation(project(":ui:settings"))
    implementation(libs.accompanist.permissions)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.compose.uiTooling)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewModel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.google.hilt.android)
    implementation(libs.google.materialComponents)
    implementation(platform(libs.google.firebase.bom))
    implementation(libs.google.firebase.crashlytics.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.timber)

    gmsImplementation(libs.google.firebase.analytics)

    androidTestRuntimeOnly(libs.androidx.test.runner)
}

hilt {
    enableAggregatingTask = false
}