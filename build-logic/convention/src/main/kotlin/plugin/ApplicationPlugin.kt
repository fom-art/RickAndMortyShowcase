package plugin
import com.android.build.api.dsl.ApplicationExtension
import com.build_logic.convention.configureKotlinAndroid
import configuration.configureAndroid
import configuration.configureAndroidCompose
import dependency.configureComposeNavigation
import dependency.configureFeature
import dependency.configureKoin
import extension.MIN_SDK
import extension.TARGET_SDK
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.get
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ApplicationPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    applicationId = "com.thalesgroup.ibs.idc"
                    versionCode = 1
                    versionName = "1.0"
                    minSdk = MIN_SDK
                    targetSdk = TARGET_SDK

                    vectorDrawables {
                        useSupportLibrary = true
                    }

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                buildFeatures.buildConfig = true

                configureAndroid()
                configureKotlinAndroid(this)
                configureAndroidCompose(this)

                buildTypes {
                    release {
                        signingConfig = signingConfigs.findByName("release") ?: signingConfigs["debug"]
                        isShrinkResources = true
                        isMinifyEnabled = true

                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
                }
            }
        }
    }
}