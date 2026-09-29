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

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    androidTestImplementation(platform(libs.androidx.compose.bom))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}