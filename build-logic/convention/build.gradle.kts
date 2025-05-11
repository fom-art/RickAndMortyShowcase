import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.gradledoctor.plugin)
    compileOnly(libs.dependency.analysis.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("apollo") {
            id = "build.logic.plugin.apollo"
            implementationClass = "plugin.ApolloPlugin"
        }
        register("application") {
            id = "build.logic.plugin.application"
            implementationClass = "plugin.ApplicationPlugin"
        }
        register("camera") {
            id = "build.logic.plugin.camera"
            implementationClass = "plugin.CameraPlugin"
        }
        register("feature") {
            id = "build.logic.plugin.feature"
            implementationClass = "plugin.FeaturePlugin"
        }
        register("kotlinMultiplatform") {
            id = "build.logic.plugin.kotlin_multiplatform"
            implementationClass = "plugin.KotlinMultiplatformPlugin"
        }
        register("koin") {
            id = "build.logic.plugin.koin"
            implementationClass = "plugin.KoinPlugin"
        }
        register("ktor") {
            id = "build.logic.plugin.ktor"
            implementationClass = "plugin.KtorPlugin"
        }
        register("library") {
            id = "build.logic.plugin.library"
            implementationClass = "plugin.LibraryPlugin"
        }
        register("root") {
            id = "build.logic.plugin.root"
            implementationClass = "plugin.RootPlugin"
        }
        register("sqlDelight") {
            id = "build.logic.plugin.sqldelight"
            implementationClass = "plugin.SqlDelightPlugin"
        }
    }
}