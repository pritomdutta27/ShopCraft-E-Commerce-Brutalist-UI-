plugins {
    alias(libs.plugins.pritom.android.library)
    alias(libs.plugins.pritom.android.compose)
}

android {
    namespace = "site.prito.dutta.core.common"
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}