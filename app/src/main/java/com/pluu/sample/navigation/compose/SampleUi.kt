package com.pluu.sample.navigation.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SampleUi(
    viewModel: SampleViewModel = hiltViewModel(),
    title: String,
    onClick: () -> Unit = {}
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(align = Alignment.Center)) {
        Button(onClick = { onClick() }) {
            Text(text = title)
        }
    }
}
