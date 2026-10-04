plugins {
    alias(libs.plugins.pritom.android.network)
}

android {
    namespace = "site.pritom.features.home.data"
}

dependencies {
    /** own Library*/
    implementation(project(":features:home:domain"))
    implementation(project(":core:common"))
    implementation(project(":core:network"))

    implementation(libs.androidx.paging.runtime)
    testImplementation(libs.androidx.paging.common)
}