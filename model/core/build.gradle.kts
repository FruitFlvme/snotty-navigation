plugins {
    id("com.fruitflvme.snotty_navigation.jvm.library")
}

dependencies {
    implementation(libs.worldwind)

    testImplementation(libs.kotest.assertions.core)
    testImplementation(libs.kotlin.test)
}
