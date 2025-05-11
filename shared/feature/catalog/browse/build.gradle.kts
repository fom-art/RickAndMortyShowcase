plugins {
    alias(libs.plugins.build.logic.feature)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            //Compose
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
//            implementation(compose.uiTooling)
//            implementation(compose.preview)
            implementation(compose.components.resources)
            implementation(compose.materialIconsExtended)
//            implementation(compose.components.uiToolingPreview)
        }
    }
}
android {
    namespace = "com.fomart.rms.shared.catalog.browse"
}
