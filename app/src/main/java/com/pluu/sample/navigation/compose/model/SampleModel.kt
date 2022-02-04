package com.pluu.sample.navigation.compose.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class SampleSerializableModel(
    val value: String
): java.io.Serializable

@Serializable
@Parcelize
data class SampleParcelableModel(
    val value: String
): Parcelable
