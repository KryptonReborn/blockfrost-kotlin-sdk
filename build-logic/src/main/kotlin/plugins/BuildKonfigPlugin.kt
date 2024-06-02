package plugins

import org.gradle.api.Project
import org.gradle.api.provider.ListProperty
import org.gradle.api.Plugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.File

class BuildKonfigPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension =
            project.extensions.create("buildKonfig", BuildKonfigExtension::class.java, project)
        project.tasks.register("updateBuildKonfig") {
            val packages = extension.packages
            doLast {
                val outputDir =
                    "${project.layout.buildDirectory.get()}/generated/kotlin/${
                        packages.replace(
                            '.',
                            '/'
                        )
                    }"
                generateBuildKonfigFile(outputDir, extension.packages, extension.fields.get())
            }
        }
        project.afterEvaluate {
            tasks.withType(KotlinCompile::class.java).configureEach {
                this.dependsOn("updateBuildKonfig")
            }
        }
        project.tasks.withType(KotlinCompile::class.java)
            .configureEach {
                dependsOn("updateBuildKonfig")
            }
    }

    private fun generateBuildKonfigFile(
        outputDir: String,
        packages: String,
        fields: List<Triple<String, String, Any?>>
    ) {
        val outputDirectory = File(outputDir)
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs()
        }

        val outputFile = File("$outputDir/BuildKonfig.kt")
        if (!outputFile.exists()) {
            outputFile.createNewFile()
        }

        val content = buildString {
            appendLine("package $packages")
            appendLine()
            appendLine("object BuildKonfig {")
            for ((name, type, value) in fields) {
                appendLine("    val $name: $type = $value")
            }
            appendLine("}")
        }
        outputFile.writeText(content)
    }
}

open class BuildKonfigExtension(project: Project) {
    lateinit var packages: String
    val fields: ListProperty<Triple<String, String, Any?>> =
        project.objects.listProperty(Triple::class.java) as ListProperty<Triple<String, String, Any?>>
}