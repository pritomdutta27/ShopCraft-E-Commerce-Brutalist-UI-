import com.android.build.api.dsl.ApplicationExtension
import ext.configureKotlin
import ext.version
import ext.versionCatalog
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            val versionCategory = versionCatalog()

            extensions.configure<ApplicationExtension> {
                compileSdk = versionCategory.version("compileSdk").toInt()

                defaultConfig {
                    applicationId = versionCategory.version("applicationId")
                    minSdk = versionCategory.version("minSdk").toInt()
                    targetSdk = versionCategory.version("targetSdk").toInt()
                    versionCode = versionCategory.version("versionCode").toInt()
                    versionName = versionCategory.version("versionName")
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
        }
    }
}