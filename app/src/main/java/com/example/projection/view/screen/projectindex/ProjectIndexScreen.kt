package com.example.projection.view.screen.projectindex

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.projection.R
import com.example.projection.view.component.ProjectList
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@Composable
fun ProjectIndexScreen(navController: NavHostController) {
    var tabSelectedState by remember { mutableStateOf(0) }
    val tabTitles = listOf(R.string.groupbuy, R.string.interest_check)

    Column {
        TabRow(selectedTabIndex = tabSelectedState) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    text = { Text(stringResource(title)) },
                    selected = tabSelectedState == index,
                    onClick = { tabSelectedState = index }
                )
            }
        }

        when (tabSelectedState) {
            0 -> GroupbuyIndexScreen(navController)
            1 -> InterestCheckIndexScreen(navController)
        }
    }
}

@Composable
fun InterestCheckIndexScreen(
    navController: NavHostController,
    viewModel: InterestCheckIndexViewModel = hiltViewModel()
) {
    ProjectList(navController, viewModel)
}

@Composable
fun GroupbuyIndexScreen(
    navController: NavHostController,
    viewModel: GroupbuyIndexViewModel = hiltViewModel()
) {
    ProjectList(navController, viewModel)
}
