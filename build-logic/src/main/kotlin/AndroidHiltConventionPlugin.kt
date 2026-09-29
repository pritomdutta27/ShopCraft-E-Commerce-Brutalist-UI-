import ext.lib
import ext.plugins
import ext.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/** Created by Pritom Dutta on 30/9/26 */

class AndroidHiltConventionPlugin : Plugin<Project>  {
    override fun apply(target: Project) {
        with(target) {
            val versionCatalog = versionCatalog()

            with(pluginManager) {
                apply(versionCatalog.plugins("ksp-plugins").get().pluginId)
                apply(versionCatalog.plugins("hilt-plugins").get().pluginId)
            }

            dependencies {
                add("implementation", versionCatalog.lib("hilt-android").get())
                add("ksp", versionCatalog.lib("hilt-compiler").get())
            }
        }

    }
}