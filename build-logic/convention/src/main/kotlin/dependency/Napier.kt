package dependency

import extension.libs
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureNapier(
    kotlinMultiplatformExtension: KotlinMultiplatformExtension,
) {
    kotlinMultiplatformExtension.sourceSets.getByName("commonMain").dependencies {
        implementation(libs.findLibrary("napier").get())
    }
}