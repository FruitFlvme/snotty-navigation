plugins {
    id("com.fruitflvme.snotty_navigation.android.library")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.ksp)
}

android {
    namespace = "com.fruitflvme.snotty_navigation.ui.settings"
}

dependencies {
    ksp(libs.google.hilt.compiler)

    api(project(":data:settings"))
    api(project(":ui:core"))
    api(libs.androidx.compose.runtime)
    api(libs.google.hilt.android)

    implementation(project(":ui:design"))
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.uiTooling)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewModel.ktx)
    implementation(libs.androidx.navigation.compose)
}