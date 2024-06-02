package plugins

import com.android.build.gradle.LibraryExtension
import extensions.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.provider.ListProperty
import org.gradle.api.Plugin
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.File

class BuildKonfigPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension =
            project.extensions.create("buildKonfig", BuildKonfigExtension::class.java, project)

        project.tasks.register("updateBuildConfig") {
            doLast {
                val outputDir = "${project.buildDir}/generated/kotlin/dev/kryptonreborn/blockfrost"
                generateBuildConfigFile(outputDir, extension.fields.get())
            }
        }
        project.afterEvaluate {
            tasks.withType(KotlinCompile::class.java).configureEach {
                this.dependsOn("updateBuildConfig")
            }
        }
        project.tasks.forEach { task ->
            if (task.name.startsWith("compile") || task.name.contains("Test") || task.name.contains("Run")) {
                task.dependsOn("updateBuildConfig")
            }
        }
    }

    private fun generateBuildConfigFile(
        outputDir: String,
        fields: List<Triple<String, String, Any?>>
    ) {
        val outputDirectory = File(outputDir)
        println("Output directory: ${outputDirectory.absolutePath}")
        if (!outputDirectory.exists()) {
            println("Directory does not exist. Creating...")
            outputDirectory.mkdirs()
        }

        val outputFile = File("$outputDir/BuildConfig.kt")
        if (!outputFile.exists()) {
            println("File does not exist. Creating...")
            outputFile.createNewFile()
            println("File does not exist. Creating...${outputFile.exists()}")
        }

        val content = buildString {
            appendLine("package dev.kryptonreborn.blockfrost")
            appendLine()
            appendLine("object BuildConfig {")
            for ((name, type, value) in fields) {
                appendLine("    val $name: $type = $value")
            }
            appendLine("}")
        }
        outputFile.writeText(content)
        println("writeText success")
    }
}

open class BuildKonfigExtension(project: Project) {
    val fields: ListProperty<Triple<String, String, Any?>> =
        project.objects.listProperty(Triple::class.java) as ListProperty<Triple<String, String, Any?>>
}