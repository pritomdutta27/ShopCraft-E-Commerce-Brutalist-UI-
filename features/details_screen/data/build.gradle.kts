plugins {
    alias(libs.plugins.pritom.android.library)
}

android {
    namespace = "site.pritom.features.details_screen.data"
}

dependencies {
    implementation(project(":features:details_screen:domain"))
}