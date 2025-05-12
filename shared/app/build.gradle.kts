import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.build.logic.library)
    alias(libs.plugins.build.logic.kotlin.multiplatform)
    alias(libs.plugins.build.logic.koin)
    alias(libs.plugins.build.logic.camera)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            //Compose
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
//            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.navigation.compose)

            implementation(libs.napier)

            //Serialization
            implementation(libs.kotlinx.serialization.json)

            //Core project dependencies
            api(projects.shared.core.data)
            api(projects.shared.core.designsystem)
            api(projects.shared.core.model)

            //Feature project dependencies
            implementation(projects.shared.feature.catalog)
            implementation(projects.shared.feature.favorites)
            implementation(projects.shared.feature.search)
            implementation(projects.shared.feature.characterDetails)
        }
    }
}

android {
    namespace = "com.fomart.mafiamaster"
}