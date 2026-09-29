import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

group = "site.pritom.dutta.convention.buildLogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "site.pritom.dutta.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("createFeature") {
            id = "site.pritom.dutta.create.feature"
            implementationClass = "create_feature_module.FeaturePlugin"
        }
    }
}