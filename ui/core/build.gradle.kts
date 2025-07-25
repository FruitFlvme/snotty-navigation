plugins {
    id("com.fruitflvme.snotty_navigation.android.library")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.ksp)
}

android {
    namespace = "com.fruitflvme.snotty_navigation.ui.core"
}

dependencies {
    implementation(libs.androidx.core.i18n)
    ksp(libs.google.hilt.compiler)

    api(libs.androidx.appcompat)
    api(libs.javax.inject)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.google.hilt.android)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.datetime)

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}