package plugin
import com.android.build.gradle.LibraryExtension
import configuration.configureAndroid
import configuration.configureKotlinJvm
import dependency.configureKtor
import dependency.configureSqlDelight
import extension.TARGET_SDK
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class SqlDelightPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<KotlinMultiplatformExtension> {
                configureSqlDelight(this)
            }
        }
    }
}