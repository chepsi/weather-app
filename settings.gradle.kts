pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Weather App"
include(":app")
include(":core:data")
include(":core:domain")
include(":core:presentation")
include(":core:local-data-source")
include(":core:remote-data-source")
