import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import ext.lib
import ext.plugins
import ext.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies


class AndroidComposeConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val versionCatalog = versionCatalog()

            with(pluginManager) {
                apply(versionCatalog.plugins("kotlin-compose").get().pluginId)

                withPlugin("com.android.application") {
                    extensions.configure<ApplicationExtension> {
                        buildFeatures {
                            compose = true
                        }
                    }
                }

                withPlugin("com.android.library") {
                    extensions.configure<LibraryExtension> {
                        buildFeatures {
                            compose = true
                        }
                    }
                }
            }

            dependencies {
                val bom = versionCatalog.lib("androidx-compose-bom").get()
                add("implementation", platform(bom))
                add("debugImplementation", platform(bom))
                add("androidTestImplementation", platform(bom))

                add("implementation", versionCatalog.lib("androidx-compose-ui").get())
                add("implementation", versionCatalog.lib("androidx-compose-ui-graphics").get())
                add("implementation", versionCatalog.lib("androidx-compose-ui-tooling-preview").get())
                add("implementation", versionCatalog.lib("androidx-compose-material3").get())
                add("implementation", versionCatalog.lib("androidx-activity-compose").get())
                add("implementation", versionCatalog.lib("androidx-lifecycle-runtime-ktx").get())
                add("implementation", versionCatalog.lib("androidx-lifecycle-viewmodel-compose").get())

                add("debugImplementation", versionCatalog.lib("androidx-compose-ui-tooling").get())
                add("debugImplementation", versionCatalog.lib("androidx-compose-ui-test-manifest").get())
                add("androidTestImplementation", versionCatalog.lib("androidx-compose-ui-test-junit4").get())
            }
        }
    }
}