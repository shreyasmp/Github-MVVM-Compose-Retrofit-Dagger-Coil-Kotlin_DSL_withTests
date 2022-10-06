package com.shreyas.nytimes.ui

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.shreyas.nytimes.R

@Preview
@Composable
fun TopAppBarWithTitle() {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.app_bar_title))
        },
        backgroundColor = colorResource(id = R.color.purple_700)
    )
}