package configuration

import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.HasUnitTestBuilder
import com.android.build.gradle.BaseExtension
import extension.COMPILE_SDK
import extension.MIN_SDK
import extension.TARGET_SDK
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.configureAndroid() {
    android {
        compileSdkVersion(COMPILE_SDK)

        defaultConfig {
            minSdk = MIN_SDK
            targetSdk = TARGET_SDK
            manifestPlaceholders["appAuthRedirectScheme"] = "empty"
        }

        compileOptions {
            sourceCompatibility = ProjectConfig.COMPILE_JAVA_VERSION
            targetCompatibility = ProjectConfig.COMPILE_JAVA_VERSION
//            isCoreLibraryDesugaringEnabled = true
        }

        packagingOptions {
            resources {
                excludes.add("/META-INF/{AL2.0,LGPL2.1}")
                excludes.add("/META-INF/versions/9/previous-compilation-data.bin")
            }
        }
    }

    androidComponents {
        beforeVariants(selector().withBuildType("release")) { variantBuilder ->
            (variantBuilder as? HasUnitTestBuilder)?.apply {
                enableUnitTest = false
            }
        }
    }

//    dependencies {
//        add("coreLibraryDesugaring", libs.findLibrary("android-desugarJdkLibs").get())
//    }
}

fun Project.android(action: BaseExtension.() -> Unit) = extensions.configure<BaseExtension>(action)

private fun Project.androidComponents(action: AndroidComponentsExtension<*, *, *>.() -> Unit) {
    extensions.configure(AndroidComponentsExtension::class.java, action)
}
