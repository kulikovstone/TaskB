pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "TaskB"
include(":app")
include(":core:di")
include(":core:network")
include(":core:database")
include(":core:navigation")
include(":core:designsystem")
include(":feature:home")
include(":feature:home-api")
include(":feature:task")
include(":feature:task-api")
