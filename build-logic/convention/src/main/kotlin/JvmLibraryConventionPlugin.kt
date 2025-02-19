import com.fruitflvme.snotty_navigation.configureJava
import com.fruitflvme.snotty_navigation.configureKotlinJvm
import com.fruitflvme.snotty_navigation.configureTestLogger
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
                apply("com.adarshr.test-logger")
            }

            extensions.configure<JavaPluginExtension> {
                configureJava()
            }

            extensions.configure<KotlinJvmProjectExtension> {
                configureKotlinJvm()
            }

            configureTestLogger()
        }
    }
}
