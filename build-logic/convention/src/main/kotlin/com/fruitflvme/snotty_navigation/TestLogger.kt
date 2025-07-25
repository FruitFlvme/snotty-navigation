package com.fruitflvme.snotty_navigation

import com.adarshr.gradle.testlogger.TestLoggerExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureTestLogger() {
    extensions.configure<TestLoggerExtension> {
        setTheme("mocha")
    }
}
