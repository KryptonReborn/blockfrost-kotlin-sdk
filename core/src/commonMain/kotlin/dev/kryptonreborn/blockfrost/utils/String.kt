package dev.kryptonreborn.blockfrost.utils

fun String.normalizeJsonString() = replace("\\s".toRegex(), "")
