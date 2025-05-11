package dependency

import extension.libs
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension


internal fun Project.configureSqlDelight(
    kotlinMultiplatformExtension: KotlinMultiplatformExtension,
) {
    kotlinMultiplatformExtension.sourceSets.getByName("commonMain").dependencies {
        implementation(libs.findLibrary("sqldelight-runtime").get())
        implementation(libs.findLibrary("sqldelight-coroutines").get())
    }
    kotlinMultiplatformExtension.sourceSets.getByName("androidMain").dependencies {
        implementation(libs.findLibrary("sqldelight-android-driver").get())
    }
    kotlinMultiplatformExtension.sourceSets.getByName("iosMain").dependencies {
        implementation(libs.findLibrary("sqldelight-native-driver").get())
    }
}