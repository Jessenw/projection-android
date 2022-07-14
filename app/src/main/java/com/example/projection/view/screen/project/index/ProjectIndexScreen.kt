package com.example.projection.view.screen.project.index

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.projection.R
import com.example.projection.view.component.ProjectList
import com.example.projection.view.screen.project.index.viewmodel.GroupbuyIndexViewModel
import com.example.projection.view.screen.project.index.viewmodel.InterestCheckIndexViewModel

@Composable
fun ProjectIndexScreen(navController: NavHostController) {
    var tabSelectedState by remember { mutableStateOf(0) }
    val tabTitles = listOf(R.string.groupbuy, R.string.interest_check)

    Column(Modifier.background(MaterialTheme.colors.background)) {
        TabRow(
            selectedTabIndex = tabSelectedState,
            contentColor = MaterialTheme.colors.onPrimary
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            stringResource(title),
                            color = MaterialTheme.colors.background
                        )
                    },
                    selected = tabSelectedState == index,
                    onClick = { tabSelectedState = index },
                    modifier = Modifier.background(MaterialTheme.colors.primary)
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
