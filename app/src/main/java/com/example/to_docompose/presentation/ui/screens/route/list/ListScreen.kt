package com.example.to_docompose.presentation.ui.screens.route.list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_docompose.presentation.ui.components.fab.ListScreenFloatingActionButton
import com.example.to_docompose.presentation.ui.components.toolbar.ListScreenTopBar
import com.example.to_docompose.presentation.viewmodel.list.ListContract

@Composable
fun ListScreen(
    state: ListContract.State,
    onFabClicked: (Int) -> Unit
) {

    Scaffold(
        topBar = {
            ListScreenTopBar()
        },
        floatingActionButton = {
            ListScreenFloatingActionButton(
                onFabClicked
            )
        }
    ) {

    }

}

@Preview
@Composable
fun ListScreenPreview() {
    ListScreen(
        ListContract.State(),
        onFabClicked = {}
    )
}