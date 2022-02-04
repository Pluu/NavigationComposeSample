package com.pluu.sample.navigation.compose

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(
    handle: SavedStateHandle
) : ViewModel() {
    // ...
}