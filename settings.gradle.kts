pluginManagement {
    includeBuild("build-logic")
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

rootProject.name = "Snotty-Navigation"

include(":app")
include(":data:settings")
include(":data:location")
include(":model:core")
include(":model:settings")
include(":model:location")
include(":ui:core")
include(":ui:design")
include(":ui:settings")
include(":ui:location")
