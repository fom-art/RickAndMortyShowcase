package plugin
import com.android.build.gradle.LibraryExtension
import configuration.configureAndroid
import configuration.configureKotlinJvm
import extension.TARGET_SDK
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class LibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }

            extensions.configure<LibraryExtension> {

                defaultConfig.targetSdk = TARGET_SDK

                configureKotlinJvm()
                configureAndroid()
            }
        }
    }
}