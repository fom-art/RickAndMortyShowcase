package dependency

import extension.libs
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureComposeNavigation(
    kotlinMultiplatformExtension: KotlinMultiplatformExtension,
) {
    kotlinMultiplatformExtension.sourceSets.getByName("commonMain").dependencies {
        implementation(libs.findLibrary("navigation-compose").get())
        implementation(libs.findLibrary("kotlinx-serialization-json").get())
    }
}