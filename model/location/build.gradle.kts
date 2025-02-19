plugins {
    id("com.fruitflvme.snotty_navigation.jvm.library")
}

dependencies {
    api(project(":model:core"))
    api(libs.kotlinx.datetime)
}