package com.pluu.sample.navigation.compose

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomModelAdapter {
    inline fun <reified T> toSerialize(value: T): String {
        return Json.encodeToString(value)
    }

    inline fun <reified T> parseDeserialize(value: String): T {
        return Json.decodeFromString(value)
    }
}
