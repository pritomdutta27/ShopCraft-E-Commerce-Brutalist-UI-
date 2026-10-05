plugins {
    alias(libs.plugins.pritom.android.application)
    alias(libs.plugins.pritom.android.compose)
}

android {
    namespace = "net.live.ent.shopcraftpremiume_commerce"
    compileSdk {
        version = release(37)
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
}

dependencies {
    implementation(project(":core:common"))
}