plugins {
    alias(libs.plugins.build.logic.library)
    alias(libs.plugins.build.logic.kotlin.multiplatform)
    alias(libs.plugins.build.logic.ktor)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.shared.core.model)

            //Koin
            api(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)

            //Logger
            implementation(libs.napier)
        }
    }
}

android {
    namespace = "com.fomart.rms.core.database"
}