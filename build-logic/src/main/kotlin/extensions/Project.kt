package extensions

import org.apache.tools.ant.property.LocalProperties
import org.gradle.api.Project
import org.gradle.api.artifacts.*
import org.gradle.kotlin.dsl.getByType
import java.util.Properties

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun Project.getLocalProperty(propertyName: String): String? {
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        val properties = Properties().apply {
            load(localPropertiesFile.inputStream())
        }
        return properties.getProperty(propertyName)
    }
    return null
}
