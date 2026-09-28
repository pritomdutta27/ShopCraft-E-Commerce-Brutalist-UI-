package ext

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

/** Created by Pritom Dutta on 29/9/26 */

fun Project.configureKotlin(){
    extensions.configure<KotlinAndroidProjectExtension>{
        jvmToolchain(17)
    }
}