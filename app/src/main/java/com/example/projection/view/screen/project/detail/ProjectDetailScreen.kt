package com.example.projection.view.screen.project.detail

import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projection.view.components.TopAppBarScreen
import com.example.projection.view.screen.project.detail.viewmodel.GroupbuyDetailViewModelImpl

@Composable
fun ProjectShowScreen(
    viewModel: GroupbuyDetailViewModelImpl = hiltViewModel()
) {
    val project = viewModel.dataSource.observeAsState()

    project.value?.data?.let { data ->
        TopAppBarScreen(title = data.title) {
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(PaddingValues(horizontal = 16.dp, vertical = 8.dp))
            ) {
                Html(data.content)
            }
        }
    }
}

@Composable
fun Html(rawHtml: String) {
    val primaryColor = MaterialTheme.colors.primary.toArgb()
    val onSurfaceColor = MaterialTheme.colors.onSurface.toArgb()
    AndroidView({ context ->
        TextView(context).apply {
            text = HtmlCompat.fromHtml(
                rawHtml,
                HtmlCompat.FROM_HTML_MODE_COMPACT,
                GlideImageGetter(this),
                null
            )
        }
    },
    update = {
        it.setTextColor(onSurfaceColor)
        it.setLinkTextColor(primaryColor)
        it.setHintTextColor(primaryColor)
    })
}
