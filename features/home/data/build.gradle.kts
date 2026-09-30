plugins {
    alias(libs.plugins.pritom.android.network)
}

android {
    namespace = "site.pritom.features.home.data"
}

dependencies {
    implementation(project(":features:home:domain"))
}