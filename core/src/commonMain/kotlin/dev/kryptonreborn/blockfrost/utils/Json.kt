package dev.kryptonreborn.blockfrost.utils

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

internal fun JsonObject.removeEmptyKeys(): JsonObject {
    val result = mutableMapOf<String, JsonElement>()
    for ((key, value) in this) {
        val cleanedValue = value.removeEmptyKeys()
        if (cleanedValue.isNotEmpty()) {
            result[key] = cleanedValue
        }
    }
    return JsonObject(result)
}

internal fun JsonArray.removeEmptyKeys(): JsonArray {
    val result = mutableListOf<JsonElement>()
    for (element in this) {
        val cleanedElement = element.removeEmptyKeys()
        if (cleanedElement.isNotEmpty()) {
            result.add(element.removeEmptyKeys())
        }
    }
    return JsonArray(result)
}

internal fun JsonElement.removeEmptyKeys(): JsonElement {
    return when (this) {
        is JsonObject -> removeEmptyKeys()
        is JsonArray -> removeEmptyKeys()
        else -> this
    }
}

internal fun JsonElement.isNotEmpty(): Boolean =
    when (this) {
        is JsonObject -> this.keys.isNotEmpty()
        is JsonArray -> this.size > 0
        else -> true
    }
