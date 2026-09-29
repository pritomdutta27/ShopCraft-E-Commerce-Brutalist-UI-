// Fix AGP AndroidLocationsException when both ANDROID_PREFS_ROOT and ANDROID_USER_HOME are set in environment
try {
    val env = System.getenv()
    val field = env.javaClass.getDeclaredField("m")
    field.isAccessible = true
    @Suppress("UNCHECKED_CAST")
    val map = field.get(env) as MutableMap<Any, Any>
    map.remove("ANDROID_PREFS_ROOT")
} catch (_: Exception) {
}

pluginManagement {
    includeBuild("build-logic")
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
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ShopCraft Premium E-Commerce"
include(":app")
include(":core:common")
include(":core:network")
include(":core:designsystems")

include(":features:details_screen:data")
include(":features:details_screen:domain")
include(":features:details_screen:presentation")


include(":features:home:data")
include(":features:home:domain")
include(":features:home:presentation")
