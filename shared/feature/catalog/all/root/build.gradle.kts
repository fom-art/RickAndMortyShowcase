plugins {
    alias(libs.plugins.build.logic.feature)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(compose.components.uiToolingPreview)
        }
        commonMain.dependencies {
            //Compose
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.uiTooling)
            implementation(compose.components.resources)
            implementation(compose.materialIconsExtended)

            implementation(libs.coil.compose)

            implementation(projects.shared.feature.catalog.all.browse)
            implementation(projects.shared.feature.catalog.all.search)
        }
    }
}
android {
    namespace = "com.fomart.rms.shared.feature.catalog.all.root"
}
