plugins {
    alias(libs.plugins.pritom.android.network)
}

android {
    namespace = "site.pritom.network"
}

dependencies{
    implementation(project(":core:common"))
}