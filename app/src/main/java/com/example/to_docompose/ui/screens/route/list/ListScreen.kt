package com.example.to_docompose.ui.screens.route.list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_docompose.ui.screens.components.fab.ListScreenFloatingActionButton
import com.example.to_docompose.ui.screens.components.toolbar.ListScreenTopBar
import com.example.to_docompose.ui.viewmodel.list.ListContract

@Composable
fun ListScreen(
    state: ListContract.State,
    onFabClicked: (Int) -> Unit,
    onSearchClick: () -> Unit,
    onCloseSearchClick: () -> Unit
) {
    val isSearching = state.isSearching

    Scaffold(
        topBar = {
            ListScreenTopBar(
                isSearching = isSearching,
                onSearchClick = onSearchClick,
                onCloseSearchClick = onCloseSearchClick
            )
        },
        floatingActionButton = {
            ListScreenFloatingActionButton(
                onFabClicked
            )
        }
    ) {
        it
    }

}

@Preview
@Composable
fun ListScreenPreview() {
    ListScreen(
        ListContract.State(),
        onFabClicked = {},
        onSearchClick = {},
        onCloseSearchClick = {}
    )
}