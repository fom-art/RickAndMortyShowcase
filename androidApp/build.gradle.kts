plugins {
    alias(libs.plugins.build.logic.application)
}

android {
    namespace = "com.fomart.rms"
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
//    implementation(projects.shared.app)
//    implementation(projects.shared.core.data)

    //Koin
    api(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.compose.viewmodel)

    androidTestImplementation(libs.junit)
}