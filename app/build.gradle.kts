plugins {
    alias(libs.plugins.pritom.android.application)
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
}