import com.android.build.api.dsl.LibraryExtension
import ext.configureKotlin
import ext.lib
import ext.version
import ext.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

/** Created by Pritom Dutta on 29/9/26 */

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
//                apply("org.jetbrains.kotlin.android")
            }

            val libs = versionCatalog()

            extensions.configure<LibraryExtension>{
                compileSdk = libs.version("compileSdk").toInt()
                defaultConfig {
                    minSdk = libs.version("minSdk").toInt()
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                compileOptions {
                    sourceCompatibility = org.gradle.api.JavaVersion.VERSION_17
                    targetCompatibility = org.gradle.api.JavaVersion.VERSION_17
                }
            }

            configureKotlin()

            dependencies{
                /**  AndroidX Library*/
                add("implementation", libs.lib("androidx-core-ktx").get())
                add("implementation", libs.lib("androidx-appcompat").get())
                add("implementation", libs.lib("material").get())

                /** Testing library*/
                add("testImplementation", libs.lib("junit").get())
                add("androidTestImplementation", libs.lib("androidx-junit").get())
                add("androidTestImplementation", libs.lib("androidx-espresso-core").get())
            }
        }
    }
}