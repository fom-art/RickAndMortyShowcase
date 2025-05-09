package dependency

import extension.libs
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension


internal fun Project.configureKtor(
    kotlinMultiplatformExtension: KotlinMultiplatformExtension,
) {
    kotlinMultiplatformExtension.sourceSets.getByName("commonMain").dependencies {
        implementation(libs.findLibrary("ktor-client-core").get())
        implementation(libs.findLibrary("ktor-client-logging").get())
    }
    kotlinMultiplatformExtension.sourceSets.getByName("androidMain").dependencies {
        implementation(libs.findLibrary("ktor-client-okhttp").get())
    }
    kotlinMultiplatformExtension.sourceSets.getByName("iosMain").dependencies {
        implementation(libs.findLibrary("ktor-client-darwin").get())
    }
}