rootProject.name = "RickAndMortyShowcase"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}


dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include(":androidApp")
include(":shared:app")
include(":shared:core:data")
include(":shared:core:database")
include(":shared:core:designsystem")
include(":shared:core:model")
include(":shared:core:network")
include(":shared:core:utils")

include(":shared:feature:catalog")
include(":shared:feature:favorites")
include(":shared:feature:search")
include(":shared:feature:character-details")