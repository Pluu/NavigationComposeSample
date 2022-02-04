package com.pluu.sample.navigation.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SampleFirstUi(
    viewModel: SampleViewModel = hiltViewModel(),
    onSecondClick: () -> Unit = {},
    onThirdClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { onSecondClick() }) {
                Text(text = "Second")
            }
            Button(onClick = { onThirdClick() }) {
                Text(text = "Third")
            }
        }
    }
}

@Composable
fun SampleUi(
    viewModel: SampleViewModel = hiltViewModel(),
    title: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    ) {
        Text(text = title)
    }
}
