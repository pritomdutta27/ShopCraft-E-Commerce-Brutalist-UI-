plugins {
    alias(libs.plugins.pritom.android.library)
    alias(libs.plugins.pritom.android.hilt)
}

android {
    namespace = "site.pritom.features.home.domain"
}

dependencies {
    implementation(project(":core:common"))

    /*
        *Pure Kotlin lib for domain layer
        *Pure Kotlin, zero Android UI dependencies
    */
    implementation(libs.androidx.paging.common)
}