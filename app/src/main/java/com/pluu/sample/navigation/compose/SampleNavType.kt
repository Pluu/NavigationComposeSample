package com.pluu.sample.navigation.compose

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import java.io.Serializable

inline fun <reified T : Serializable> createSerializableNavType(
    isNullableAllowed: Boolean = false
): NavType<T> {
    return object : NavType<T>(isNullableAllowed) {
        override val name: String
            get() = "SupportSerializable"

        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putSerializable(key, value)
        }

        override fun get(bundle: Bundle, key: String): T? {
            return bundle.getSerializable(key) as? T
        }

        override fun parseValue(value: String): T {
            return CustomModelAdapter.parseDeserialize(value)
        }
    }
}

inline fun <reified T : Parcelable> createParcelableNavType(
    isNullableAllowed: Boolean = false
): NavType<T> {
    return object : NavType<T>(isNullableAllowed) {
        override val name: String
            get() = "SupportParcelable"

        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putParcelable(key, value)
        }

        override fun get(bundle: Bundle, key: String): T? {
            return bundle.getParcelable(key)
        }

        override fun parseValue(value: String): T {
            return CustomModelAdapter.parseDeserialize(value)
        }
    }
}