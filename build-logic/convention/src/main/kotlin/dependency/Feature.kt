package dependency

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureFeature(
    kotlinMultiplatformExtension: KotlinMultiplatformExtension,
) {
    kotlinMultiplatformExtension.sourceSets.getByName("commonMain").dependencies {
        implementation(project(":shared:core:data"))
        implementation(project(":shared:core:designsystem"))
        implementation(project(":shared:core:model"))
        implementation(project(":shared:core:utils"))
    }
}

