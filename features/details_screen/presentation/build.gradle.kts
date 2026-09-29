plugins {
    alias(libs.plugins.pritom.android.library)
}

android {
    namespace = "site.pritom.features.details_screen.presentation"
}

dependencies {
    implementation(project(":features:details_screen:domain"))
}