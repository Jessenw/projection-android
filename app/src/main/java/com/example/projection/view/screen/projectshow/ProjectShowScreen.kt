package com.example.projection.view.screen.projectshow

import android.content.res.Resources
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.navigation.NavHostController
import org.wordpress.aztec.AztecText

@Composable
fun ProjectShowScreen(navController: NavHostController) {
    // Aztec editor view
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            AztecText(context)
    })
}