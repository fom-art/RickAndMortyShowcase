package plugin
import configuration.configureKotlinJvm
import extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import java.util.Locale

class KotlinMultiplatformPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.multiplatform")
        }

        version = libs.findVersion("shared-module-version")

        extensions.configure<KotlinMultiplatformExtension> {
            applyDefaultHierarchyTemplate()

            if (pluginManager.hasPlugin("com.android.library")) {
                androidTarget {
                    @OptIn(ExperimentalKotlinGradlePluginApi::class)
                    compilerOptions {
                        jvmTarget.set(JvmTarget.JVM_21)
                    }
                }
            }

            listOf(
                iosX64(),
                iosArm64(),
                iosSimulatorArm64(),
            ).forEach { target ->
                target.binaries.framework {
                    baseName = "Shared"
                    isStatic = true
                }
            }

            configureKotlinJvm()

            targets.withType<KotlinNativeTarget>().configureEach {
                binaries.withType<Framework> {
                    export(project(":shared:core:designsystem"))
                    export(project(":shared:core:data"))
                    export(project(":shared:core:model"))
                }
            }
        }
    }
}

fun Project.addLanguageArgs(vararg args: String) {
    extensions.configure<KotlinMultiplatformExtension> {
        sourceSets.all {
            languageSettings {
                args.forEach { optIn(it) }
            }
        }
    }
}

fun Project.addKspDependencyForAllTargets(dependencyNotation: Any) =
    addKspDependencyForAllTargets("", dependencyNotation)

private fun Project.addKspDependencyForAllTargets(
    configurationNameSuffix: String,
    dependencyNotation: Any,
) {
    val kmpExtension = extensions.getByType<KotlinMultiplatformExtension>()
    val kspTargets = kmpExtension.targets.names.map { targetName ->
        targetName.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.US) else it.toString()
        }
    }
    dependencies {
        kspTargets
            .asSequence()
            .map { target ->
                if (target == "Metadata") "CommonMainMetadata" else target
            }
            .forEach { targetConfigSuffix ->
                add("ksp${targetConfigSuffix}$configurationNameSuffix", dependencyNotation)
            }
    }
}
