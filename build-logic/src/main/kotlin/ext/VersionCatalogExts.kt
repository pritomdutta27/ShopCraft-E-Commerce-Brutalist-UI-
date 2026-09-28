package ext

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

/** Created by Pritom Dutta on 29/9/26 */


fun Project.versionCatalog(): VersionCatalog{
    return extensions.getByType<VersionCatalogsExtension>().named("libs")
}

fun VersionCatalog.lib(alias: String) = findLibrary(alias).get()

fun VersionCatalog.bundle(alias: String) = findBundle(alias).get()

fun VersionCatalog.version(alias: String) = findVersion(alias).get().requiredVersion