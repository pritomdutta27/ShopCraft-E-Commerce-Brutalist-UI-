import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

group = "site.pritom.dutta.convention.buildLogic"

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
    implementation(libs.hilt.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "site.pritom.dutta.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLibrary") {
            id = "site.pritom.dutta.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidCompose") {
            id = "site.pritom.dutta.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }

        register("androidHilt") {
            id = "site.pritom.dutta.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("androidFeature") {
            id = "site.pritom.dutta.android.features"
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("androidNetwork") {
            id = "site.pritom.dutta.android.network"
            implementationClass = "AndroidNetworkConventionPlugin"
        }

        register("createFeature") {
            id = "site.pritom.dutta.create.feature"
            implementationClass = "create_feature_module.FeaturePlugin"
        }
    }
}