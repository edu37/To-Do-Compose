package com.example.to_docompose.ui.screens.components.toolbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.to_docompose.R
import com.example.to_docompose.ui.theme.toolbarBackground
import com.example.to_docompose.ui.theme.toolbarContent

@Composable
fun ListScreenTopBar(
    isSearching: Boolean,
    onSearchClick: () -> Unit,
    onCloseSearchClick: () -> Unit
) {
    if (isSearching) {
        SearchAppBar(onCloseSearchClick)
    } else {
        DefaultAppBar(
            onSearchClick = onSearchClick,
            onFilterListClick = {}
        )
    }
}

@Composable
fun DefaultAppBar(
    onSearchClick: () -> Unit,
    onFilterListClick: () -> Unit
) {
    val toolbarContentColor = MaterialTheme.colors.toolbarContent
    val toolbarBackgrounColor = MaterialTheme.colors.toolbarBackground

    TopAppBar(
        title = {
            Text(
                text = "Tasks",
                fontSize = 16.sp,
                color = toolbarContentColor
            )
        },
        backgroundColor = toolbarBackgrounColor,
        actions = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                tint = toolbarContentColor,
                modifier = Modifier
                    .size(30.dp)
                    .clickable { onSearchClick.invoke() }
            )
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_filter_list),
                contentDescription = null,
                tint = toolbarContentColor,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = null,
                tint = toolbarContentColor,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
        },
        modifier = Modifier.clip(
            RoundedCornerShape(bottomStartPercent = 5, bottomEndPercent = 5)
        )
    )
}

@Composable
fun SearchAppBar(
    onCloseSearchClick: () -> Unit
) {
    val toolbarContentColor = MaterialTheme.colors.toolbarContent
    val toolbarBackgrounColor = MaterialTheme.colors.toolbarBackground

    val inputText = rememberSaveable { mutableStateOf("") }

    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                TextField(
                    value = inputText.value,
                    onValueChange = {
                        inputText.value = it
                    },
                    label = {
                        Text(
                            text = "Search",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = toolbarContentColor,
                        backgroundColor = toolbarBackgrounColor,
                        focusedIndicatorColor = toolbarBackgrounColor,
                        focusedLabelColor = toolbarBackgrounColor
                    ),
                )
            }
        },
        backgroundColor = toolbarBackgrounColor,
        actions = {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = null,
                tint = toolbarContentColor,
                modifier = Modifier.clickable { onCloseSearchClick.invoke() }
            )
            Spacer(modifier = Modifier.width(10.dp))
        },
        modifier = Modifier.clip(
            RoundedCornerShape(bottomStartPercent = 5, bottomEndPercent = 5)
        )
    )
}

@Preview
@Composable
fun DefaultAppBarPreview() {
    DefaultAppBar({}) {}
}

@Preview
@Composable
fun SearchAppBarPreview() {
    SearchAppBar {}
}

internal enum class TextFieldColors {
    GRAY,
    TOOLBAR
}