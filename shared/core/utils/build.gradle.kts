plugins {
    alias(libs.plugins.build.logic.library)
    alias(libs.plugins.build.logic.kotlin.multiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(compose.components.uiToolingPreview)
            implementation(compose.uiTooling)
            implementation(libs.androidx.lifecycle.viewmodel)
        }
        commonMain.dependencies {
            implementation(libs.androidx.core.ktx)

            //Compose
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.materialIconsExtended)

            implementation(projects.shared.core.model)
            implementation(projects.shared.core.data)
        }
    }
}

android {
    namespace = "com.fomart.rms.core.utils"
}