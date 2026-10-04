plugins {
    alias(libs.plugins.pritom.android.library)
    alias(libs.plugins.pritom.android.hilt)
}

android {
    namespace = "site.pritom.features.home.domain"
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.androidx.paging.runtime)
    testImplementation(libs.androidx.paging.common)
}