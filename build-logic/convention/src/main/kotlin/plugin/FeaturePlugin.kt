package plugin

import dependency.configureComposeNavigation
import dependency.configureFeature
import dependency.configureKoin
import dependency.configureNapier
import dependency.configurePermissions
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class FeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("build.logic.plugin.library")
                apply("build.logic.plugin.kotlin_multiplatform")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                configureFeature(this)
                configureKoin(this)
                configureComposeNavigation(this)
                configurePermissions(this)
                configureNapier(this)
            }
        }
    }
}