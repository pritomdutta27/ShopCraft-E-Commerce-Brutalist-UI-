import ext.bundle
import ext.lib
import ext.plugins
import ext.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/** Created by Pritom Dutta on 30/9/26 */
class AndroidNetworkConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            val versionCatalog = versionCatalog()

            with(pluginManager){
                apply("site.pritom.dutta.android.library")
                apply(versionCatalog.plugins("pritom-android-hilt").get().pluginId)
            }

            dependencies {
                /** Bundle */
                add("implementation", versionCatalog.bundle("network-call"))
//                add("implementation", versionCatalog.lib("retrofit-core").get())
//                add("implementation", versionCatalog.lib("retrofit-kotlin-serialization").get())
//                add("implementation", versionCatalog.lib("retrofit-converter-gson").get())
//                add("implementation", versionCatalog.lib("okhttp-logging").get())
//                add("implementation", versionCatalog.lib("okhttp-core").get())
//                add("implementation", versionCatalog.lib("kotlinx-serialization-json").get())
            }
        }
    }
}