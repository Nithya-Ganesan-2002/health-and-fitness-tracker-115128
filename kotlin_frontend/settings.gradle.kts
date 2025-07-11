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
        // For Kizitonwose CalendarView
        maven { setUrl("https://jitpack.io") }
    }
}

rootProject.name = "kotlin_frontend"
include(":app")
