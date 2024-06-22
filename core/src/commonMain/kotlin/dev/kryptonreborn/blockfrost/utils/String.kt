package dev.kryptonreborn.blockfrost.utils

internal fun String.normalizeJsonString() = replace("\\s".toRegex(), "")
