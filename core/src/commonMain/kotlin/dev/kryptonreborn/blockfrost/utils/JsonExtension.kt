package dev.kryptonreborn.blockfrost.utils

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.double
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.float
import kotlinx.serialization.json.floatOrNull
import kotlinx.serialization.json.int
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.long
import kotlinx.serialization.json.longOrNull

internal fun JsonElement.deserializeJsonElement(): Any? {
    return when (this) {
        is JsonObject -> mapValues { it.value.deserializeJsonElement() }
        is JsonArray -> map { it.deserializeJsonElement() }
        is JsonNull -> null
        is JsonPrimitive ->
            when {
                isString -> content
                booleanOrNull != null -> boolean
                intOrNull != null -> int
                longOrNull != null -> long
                floatOrNull != null -> float
                doubleOrNull != null -> double
                else -> throw IllegalArgumentException("Unknown JsonPrimitive type")
            }
    }
}
