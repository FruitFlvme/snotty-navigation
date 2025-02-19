import com.android.build.api.dsl.ApplicationExtension
import com.fruitflvme.snotty_navigation.configureAndroid
import com.fruitflvme.snotty_navigation.configureKotlinAndroid
import com.fruitflvme.snotty_navigation.configureTestLogger
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.adarshr.test-logger")
            }

            extensions.configure<ApplicationExtension> {
                configureAndroid()
                defaultConfig.targetSdk = 35

                extensions.configure<KotlinAndroidProjectExtension> {
                    configureKotlinAndroid()
                }
            }

            configureTestLogger()
        }
    }
}
