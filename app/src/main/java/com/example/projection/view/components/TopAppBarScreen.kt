package com.example.projection.view.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun TopAppBarScreen(
    title: String,
    showShadow: Boolean = true,
    content: @Composable (padding: PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        maxLines = 1
                    )
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.background,
                elevation = if (showShadow) AppBarDefaults.TopAppBarElevation else 0.dp
            )
        }
    ) {
        content(it)
    }
}

/**
 *
 */
@Composable
fun TopAppBarScreen(
    @StringRes title: Int,
    showShadow: Boolean = true,
    content: @Composable (padding: PaddingValues) -> Unit
) {
    TopAppBarScreen(
        title = LocalContext.current.getString(title),
        showShadow = showShadow,
        content = content
    )
}
