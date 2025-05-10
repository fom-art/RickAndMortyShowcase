plugins {
    alias(libs.plugins.build.logic.library)
    alias(libs.plugins.build.logic.kotlin.multiplatform)
    alias(libs.plugins.build.logic.ktor)
    alias(libs.plugins.apollo)
    alias(libs.plugins.build.logic.apollo)
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

apollo {
    service("service") {
        packageName.set("com.fomart.rms.core.network.graphql")
    }
}

android {
    namespace = "com.fomart.rms.core.network"
}