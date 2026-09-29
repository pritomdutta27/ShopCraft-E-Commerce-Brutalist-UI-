package create_feature_module

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class CreateFeatureTask : DefaultTask() {

    @get:Input
    var featureName: String = ""

    @get:Internal
    abstract val rootDirectory: DirectoryProperty

    @TaskAction
    fun createFeature() {

        require(featureName.isNotBlank()) {
            "Feature name is required. Use -Pfeature=authentication"
        }

        val feature = featureName
            .trim()
            .lowercase()
            .replace(" ", "_")

        val rootProject = rootDirectory.get().asFile

        val featureRoot = File(
            rootProject,
            "features/$feature"
        )

        if (featureRoot.exists()) {
            throw IllegalStateException(
                "Feature '$feature' already exists."
            )
        }

        println()
        println("Creating feature: $feature")
        println()

        createDataModule(rootProject, feature)
        createDomainModule(rootProject, feature)
        createPresentationModule(rootProject, feature)

        updateSettingsGradle(rootProject, feature)

        println()
        println("✓ Feature '$feature' created successfully")
        println()
    }

    private fun createDataModule(
        root: File,
        feature: String
    ) {
        val module = File(root, "features/$feature/data")

        module.mkdirs()

        createBuildGradle(
            module,
            """
            plugins {
                alias(libs.plugins.pritom.android.library)
            }

            android {
                namespace = "site.pritom.features.$feature.data"
            }

            dependencies {
                implementation(project(":features:$feature:domain"))
            }
            """.trimIndent()
        )

        createDirectories(
            module,
            "src/main/java/com/yourapp/features/$feature/data/datasource",
            "src/main/java/com/yourapp/features/$feature/data/mapper",
            "src/main/java/com/yourapp/features/$feature/data/repository"
        )

        println("✓ Created :features:$feature:data")
    }

    private fun createDomainModule(
        root: File,
        feature: String
    ) {
        val module = File(root, "features/$feature/domain")

        module.mkdirs()

        createBuildGradle(
            module,
            """
            plugins {
                alias(libs.plugins.pritom.android.library)
            }

            android {
                namespace = "site.pritom.features.$feature.domain"
            }

            dependencies {
            }
            """.trimIndent()
        )

        createDirectories(
            module,
            "src/main/java/com/yourapp/features/$feature/domain/model",
            "src/main/java/com/yourapp/features/$feature/domain/repository",
            "src/main/java/com/yourapp/features/$feature/domain/usecase"
        )

        println("✓ Created :features:$feature:domain")
    }

    private fun createPresentationModule(
        root: File,
        feature: String
    ) {
        val module = File(root, "features/$feature/presentation")

        module.mkdirs()

        createBuildGradle(
            module,
            """
            plugins {
                alias(libs.plugins.pritom.android.library)
            }

            android {
                namespace = "site.pritom.features.$feature.presentation"
            }

            dependencies {
                implementation(project(":features:$feature:domain"))
            }
            """.trimIndent()
        )

        createDirectories(
            module,
            "src/main/java/com/yourapp/features/$feature/presentation/component",
            "src/main/java/com/yourapp/features/$feature/presentation/navigation",
            "src/main/java/com/yourapp/features/$feature/presentation/screen",
            "src/main/java/com/yourapp/features/$feature/presentation/viewmodel"
        )

        println("✓ Created :features:$feature:presentation")
    }

    private fun createDirectories(
        module: File,
        vararg paths: String
    ) {
        paths.forEach {
            File(module, it).mkdirs()
        }
    }

    private fun createBuildGradle(
        module: File,
        content: String
    ) {
        File(
            module,
            "build.gradle.kts"
        ).writeText(content)
    }

    private fun updateSettingsGradle(
        root: File,
        feature: String
    ) {

        val settingsFile = File(
            root,
            "settings.gradle.kts"
        )

        val content = settingsFile.readText()

        val modules = """
        
        include(":features:$feature:data")
        include(":features:$feature:domain")
        include(":features:$feature:presentation")
        """.trimIndent()

        if (!content.contains(":features:$feature:data")) {
            settingsFile.writeText(
                content.trimEnd() +
                        "\n\n" +
                        modules +
                        "\n"
            )
        }
    }
}