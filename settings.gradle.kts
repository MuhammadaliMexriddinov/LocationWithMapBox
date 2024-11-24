pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri( "https://api.mapbox.com/downloads/v2/releases/maven")
            credentials {
                username = "mapbox"
                password = "sk.eyJ1IjoibXVoYW1tYWRhbGkxMiIsImEiOiJjbTNza2ltYmwwamE2MmpzYzRwb3dvZ3N1In0.FZGyDEhy4S6PcrQfSyvMwQ"
            }

            url = uri("https://www.jitpack.io" )
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")

        }

    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri( "https://api.mapbox.com/downloads/v2/releases/maven")
            credentials {
                username = "mapbox"
                password = "sk.eyJ1IjoibXVoYW1tYWRhbGkxMiIsImEiOiJjbTNza2ltYmwwamE2MmpzYzRwb3dvZ3N1In0.FZGyDEhy4S6PcrQfSyvMwQ"
            }

            url = uri("https://www.jitpack.io" )
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")

        }

    }
}


rootProject.name = "MapBoxWithAndroid"
include(":app")
 