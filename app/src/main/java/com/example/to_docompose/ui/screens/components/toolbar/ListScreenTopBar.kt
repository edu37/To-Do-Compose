package com.example.to_docompose.ui.screens.components.toolbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.to_docompose.R
import com.example.to_docompose.data.models.Priority
import com.example.to_docompose.ui.screens.components.item.PriorityItem
import com.example.to_docompose.ui.screens.components.toolbar.Const.EMPTY_STRING
import com.example.to_docompose.ui.theme.Typography
import com.example.to_docompose.ui.theme.toolbarBackground
import com.example.to_docompose.ui.theme.toolbarContent

@Composable
fun ListScreenTopBar() {
    val isSearching = rememberSaveable { mutableStateOf(false) }

    if (isSearching.value) {
        SearchAppBar(
            onSearchClick = {},
            onCloseSearchClick = { isSearching.value = false }
        )
    } else {
        DefaultAppBar(
            onSearchClick = { isSearching.value = true },
            onFilterListClick = {},
            onDeleteAllTasksClicked = {}
        )
    }
}

@Composable
fun DefaultAppBar(
    onSearchClick: () -> Unit,
    onFilterListClick: (Priority) -> Unit,
    onDeleteAllTasksClicked: () -> Unit
) {
    val toolbarContentColor = MaterialTheme.colors.toolbarContent
    val toolbarBackgrounColor = MaterialTheme.colors.toolbarBackground

    TopAppBar(
        title = {
            Text(
                text = "Tasks", fontSize = 18.sp, color = toolbarContentColor
            )
        },
        backgroundColor = toolbarBackgrounColor, actions = {
            SearchAction(onSearchClick)
            Spacer(modifier = Modifier.width(8.dp))

            FilterAction(onFilterListClick)
            Spacer(modifier = Modifier.width(8.dp))

            MoreOptionsAction(onDeleteAllTasksClicked)
            Spacer(modifier = Modifier.width(8.dp))
        },
        modifier = Modifier.clip(
            RoundedCornerShape(bottomStartPercent = 5, bottomEndPercent = 5)
        )
    )
}


@Composable
fun SearchAction(onSearchClick: () -> Unit) {
    val toolbarContentColor = MaterialTheme.colors.toolbarContent
    IconButton(onClick = { onSearchClick.invoke() }) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = null,
            tint = toolbarContentColor,
            modifier = Modifier.size(28.dp)
        )
    }
}

@Composable
fun FilterAction(onFilterListClick: (Priority) -> Unit) {
    val toolbarContentColor = MaterialTheme.colors.toolbarContent
    val expanded = rememberSaveable { mutableStateOf(false) }

    fun dismissMenuItem(priority: Priority = Priority.NONE) {
        expanded.value = false
        onFilterListClick(priority)
    }

    IconButton(onClick = { expanded.value = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_filter_list),
            contentDescription = null,
            tint = toolbarContentColor,
            modifier = Modifier.size(28.dp)
        )
        DropdownMenu(expanded = expanded.value, onDismissRequest = { dismissMenuItem() }) {
            DropdownMenuItem(onClick = { dismissMenuItem(Priority.LOW) }) {
                PriorityItem(priority = Priority.LOW)
            }
            DropdownMenuItem(onClick = { dismissMenuItem(Priority.HIGH) }) {
                PriorityItem(priority = Priority.HIGH)
            }
            DropdownMenuItem(onClick = { dismissMenuItem(Priority.NONE) }) {
                PriorityItem(priority = Priority.NONE)
            }
        }
    }
}

@Composable
fun MoreOptionsAction(
    onDeleteAllTasksClicked: () -> Unit
) {
    val toolbarContentColor = MaterialTheme.colors.toolbarContent
    val expanded = rememberSaveable { mutableStateOf(false) }

    fun dismissMenuItem(action: () -> Unit = {}) {
        expanded.value = false
        action.invoke()
    }

    IconButton(onClick = { expanded.value = true }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = null,
            tint = toolbarContentColor,
            modifier = Modifier.size(28.dp)
        )
        DropdownMenu(expanded = expanded.value, onDismissRequest = { dismissMenuItem() }) {
            DropdownMenuItem(
                onClick = { dismissMenuItem(action = onDeleteAllTasksClicked) }
            ) {
                Text(
                    text = "Delete all", style = Typography.subtitle2
                )
            }
        }
    }
}

@Composable
fun SearchAppBar(
    onCloseSearchClick: () -> Unit,
    onSearchClick: (String) -> Unit,
) {
    val toolbarContentColor = MaterialTheme.colors.toolbarContent
    val toolbarBackgrounColor = MaterialTheme.colors.toolbarBackground
    val transparentColor = Color.Transparent

    val inputText = rememberSaveable { mutableStateOf(EMPTY_STRING) }

    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onSearchClick(inputText.value) }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        modifier = Modifier
                            .alpha(ContentAlpha.disabled)
                    )
                }
                TextField(
                    value = inputText.value,
                    onValueChange = {
                        inputText.value = it
                    },
                    placeholder = {
                        Text(
                            text = "Search",
                            color = Color.White,
                            modifier = Modifier.alpha(ContentAlpha.disabled)
                        )
                    },
                    textStyle = TextStyle(
                        color = toolbarContentColor,
                        fontSize = MaterialTheme.typography.subtitle2.fontSize
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(onSearch = {
                        onSearchClick(inputText.value)
                    }),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = toolbarContentColor,
                        backgroundColor = transparentColor,
                        focusedIndicatorColor = transparentColor,
                        unfocusedIndicatorColor = transparentColor,
                        disabledIndicatorColor = transparentColor
                    ),
                )
            }
        },
        backgroundColor = toolbarBackgrounColor,
        actions = {
            IconButton(onClick = {
                if (inputText.value.isEmpty()) onCloseSearchClick.invoke()
                else inputText.value = EMPTY_STRING
            }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    tint = toolbarContentColor
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
        },
        modifier = Modifier
            .clip(
                RoundedCornerShape(bottomStartPercent = 5, bottomEndPercent = 5)
            )
    )
}

private object Const {
    const val EMPTY_STRING = ""
}

@Preview
@Composable
fun DefaultAppBarPreview() {
    DefaultAppBar({}, {}) {}
}

@Preview
@Composable
fun SearchAppBarPreview() {
    SearchAppBar({}) {}
}