package dependency

import extension.libs
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureCamera(
    kotlinMultiplatformExtension: KotlinMultiplatformExtension,
) {
    kotlinMultiplatformExtension.sourceSets.getByName("androidMain").dependencies {
        implementation(libs.findLibrary("androidx-camera-camera2").get())
        implementation(libs.findLibrary("androidx-camera-lifecycle").get())
        implementation(libs.findLibrary("androidx-camera-view").get())
    }
}