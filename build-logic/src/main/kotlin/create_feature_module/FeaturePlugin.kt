package create_feature_module

import org.gradle.api.Plugin
import org.gradle.api.Project

class FeaturePlugin : Plugin<Project> {

    override fun apply(project: Project) {

        project.tasks.register(
            "createFeature",
            CreateFeatureTask::class.java
        ) {

            featureName =
                project.findProperty("feature")
                    ?.toString()
                    ?: ""

            rootDirectory.convention(project.rootProject.layout.projectDirectory)
        }
    }
}