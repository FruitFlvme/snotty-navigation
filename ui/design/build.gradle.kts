plugins {
    id("com.fruitflvme.snotty_navigation.android.library")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.fruitflvme.snotty_navigation.ui.design"
}

dependencies {
    api(project(":ui:core"))
    api(libs.androidx.compose.runtime)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.uiTooling)
    implementation(libs.composePlaceholder.material3)
    implementation(libs.markwon)
    implementation(libs.androidx.ui.text.google.fonts)
}