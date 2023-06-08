package com.example.to_docompose.ui.screens.route.list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_docompose.ui.screens.components.fab.ListScreenFloatingActionButton
import com.example.to_docompose.ui.viewmodel.list.ListContract

@Composable
fun ListScreen(
    onFabClicked: (Int) -> Unit,
    state: ListContract.State
) {

    Scaffold(
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
    ListScreen(onFabClicked = {}, ListContract.State())
}