plugins {
    alias(libs.plugins.pritom.android.features)
    alias(libs.plugins.pritom.android.compose)
}

android {
    namespace = "site.pritom.features.home.presentation"
}

dependencies {
    implementation(project(":features:home:domain"))
    implementation(libs.androidx.compose.material3.window.size.class1)
    implementation(libs.androidx.material3.adaptive)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.palette.ktx)
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.androidx.paging.compose)
    testImplementation(libs.androidx.paging.common)
    testImplementation(libs.bundles.compose.adavtive)
}