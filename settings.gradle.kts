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

include(":shared:feature:catalog:all:browse")
include(":shared:feature:catalog:all:root")
include(":shared:feature:catalog:all:search")
include(":shared:feature:catalog:favorite")
include(":shared:feature:character-details")