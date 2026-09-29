import ext.lib
import ext.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/** Created by Pritom Dutta on 30/9/26 */
class AndroidFeatureConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("site.pritom.dutta.android.library")
                apply("site.pritom.dutta.android.hilt")
            }

            val libs = versionCatalog()
            dependencies {
                add("implementation", project(":core:designsystems"))
                add("implementation", project(":core:common"))

                add("implementation", libs.lib("androidx-hilt-navigation-compose").get())
                add("implementation", libs.lib("androidx-lifecycle-runtime-ktx").get())
                add("implementation", libs.lib("androidx-lifecycle-viewmodel-compose").get())
                add("implementation", libs.lib("kotlinx-coroutines-android").get())
            }
        }
    }
}