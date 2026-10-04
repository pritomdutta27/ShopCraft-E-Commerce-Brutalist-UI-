plugins {
    alias(libs.plugins.pritom.android.library)
}

android {
    namespace = "site.pritom.features.home.domain"
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.androidx.paging.runtime)
    testImplementation(libs.androidx.paging.common)
}