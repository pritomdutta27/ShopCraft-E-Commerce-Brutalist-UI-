import com.android.build.api.dsl.ApplicationExtension
import ext.configureKotlin
import ext.lib
import ext.plugins
import ext.version
import ext.versionCatalog
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val versionCatalog = versionCatalog()
            with(pluginManager) {
                apply("com.android.application")
                apply(versionCatalog.plugins("pritom-android-compose").get().pluginId)
                apply(versionCatalog.plugins("pritom-android-hilt").get().pluginId)
            }



            extensions.configure<ApplicationExtension> {
                compileSdk = versionCatalog.version("compileSdk").toInt()

                defaultConfig {
                    applicationId = versionCatalog.version("applicationId")
                    minSdk = versionCatalog.version("minSdk").toInt()
                    targetSdk = versionCatalog.version("targetSdk").toInt()
                    versionCode = versionCatalog.version("versionCode").toInt()
                    versionName = versionCatalog.version("versionName")
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                buildTypes {
                    release {
                        isMinifyEnabled = false
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }
            }

            configureKotlin()

            dependencies {
                /** own Library*/
                add("implementation", project(":core:designsystems"))
                add("implementation", project(":core:common"))
                add("implementation", project(":core:network"))

                /** Features Home Library*/
                add("implementation", project(":features:home:presentation"))
                add("implementation", project(":features:home:data"))
                add("implementation", project(":features:home:domain"))
                /** Features Details Screen Library*/
                add("implementation", project(":features:details_screen:presentation"))
                add("implementation", project(":features:details_screen:data"))
                add("implementation", project(":features:details_screen:domain"))

                /**  AndroidX Library*/
                add("implementation", versionCatalog.lib("androidx-core-ktx").get())
                add("implementation", versionCatalog.lib("androidx-appcompat").get())
                add("implementation", versionCatalog.lib("material").get())

                /** Hilt and lifecycle Library*/
                add("implementation", versionCatalog.lib("androidx-hilt-navigation-compose").get())
                add("implementation", versionCatalog.lib("androidx-lifecycle-runtime-ktx").get())
                add("implementation", versionCatalog.lib("androidx-lifecycle-viewmodel-compose").get())
                add("implementation", versionCatalog.lib("kotlinx-coroutines-android").get())

                /** Testing library*/
                add("testImplementation", versionCatalog.lib("junit").get())
                add("androidTestImplementation", versionCatalog.lib("androidx-junit").get())
                add("androidTestImplementation", versionCatalog.lib("androidx-espresso-core").get())
            }
        }
    }
}