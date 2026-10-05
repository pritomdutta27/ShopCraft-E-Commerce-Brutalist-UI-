plugins {
    alias(libs.plugins.pritom.android.library)
    alias(libs.plugins.pritom.android.compose)
}

android {
    namespace = "site.pritom.designsystems"
}

dependencies {
    implementation(libs.androidx.material.icons.core)
    implementation(libs.androidx.material.icons.extended)

    implementation(libs.coil)
    implementation(libs.coil.compose)
}