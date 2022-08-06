package com.example.projection.view.screen.project.detail

import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projection.view.screen.project.detail.viewmodel.GroupbuyDetailViewModelImpl

@Composable
fun ProjectShowScreen(
    viewModel: GroupbuyDetailViewModelImpl = hiltViewModel()
) {
    val project = viewModel.dataSource.observeAsState()

    project.value?.data?.let {
        Column {
            TopAppBar(
                title = { Text(it.title, maxLines = 1) },
                backgroundColor = MaterialTheme.colors.secondary
            )
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(PaddingValues(horizontal = 16.dp, vertical = 8.dp))
            ) {
                Html(it.content)
            }
        }
    }
}

@Composable
fun Html(rawHtml: String) {
    val color = MaterialTheme.colors.onSurface
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
        it.setTextColor(color.toArgb())
    })
}
