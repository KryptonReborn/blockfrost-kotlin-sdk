package dev.kryptonreborn.blockfrost.base

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

internal fun JsonObject.toMap(): Map<String, Any> {
    val map = mutableMapOf<String, Any>()

    for ((key, value) in this) {
        map[key] =
            when (value) {
                is JsonObject -> value.toMap()
                is JsonArray -> value.toList()
                is JsonPrimitive -> value.content
                else -> throw IllegalArgumentException("Unsupported JsonElement type")
            }
    }

    return map
}

internal fun JsonArray.toList(): List<Any> {
    val list = mutableListOf<Any>()

    for (value in this) {
        list.add(
            when (value) {
                is JsonObject -> value.toMap()
                is JsonArray -> value.toList()
                is JsonPrimitive -> value.content
                else -> throw IllegalArgumentException("Unsupported JsonElement type")
            },
        )
    }

    return list
}

internal fun JsonElement.normalize(): Any {
    return when (this) {
        is JsonObject -> this.toMap()
        is JsonArray -> this.toList()
        is JsonPrimitive -> this.content
        else -> throw IllegalArgumentException("Unsupported JsonElement type")
    }
}
