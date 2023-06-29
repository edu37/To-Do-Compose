package com.example.to_docompose.presentation.ui.components.toolbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.to_docompose.R
import com.example.to_docompose.data.models.Priority
import com.example.to_docompose.presentation.ui.components.item.PriorityItem
import com.example.to_docompose.presentation.ui.components.toolbar.Const.EMPTY_STRING
import com.example.to_docompose.presentation.ui.theme.Typography
import com.example.to_docompose.presentation.ui.theme.toolbarBackground
import com.example.to_docompose.presentation.ui.theme.toolbarContent
import com.example.to_docompose.presentation.viewmodel.list.ListContract
import com.example.to_docompose.util.TestTags

@Composable
fun ListScreenTopBar(
    sendEvent: (ListContract.Event) -> Unit
) {
    val isSearching = rememberSaveable { mutableStateOf(false) }

    if (isSearching.value) {
        SearchAppBar(
            onSearch = { input ->
                sendEvent(ListContract.Event.SearchTasks(input))
            },
            onCloseSearchClick = {
                isSearching.value = false
                sendEvent(ListContract.Event.GetAllTasks)
            }
        )
    } else {
        DefaultAppBar(
            onSearchClicked = { isSearching.value = true },
            onFilterListClicked = { priority ->
                sendEvent(ListContract.Event.OrderTasksByPriority(priority))
            },
            onDeleteAllTasksClicked = {
                sendEvent(ListContract.Event.DeleteAllTasks)
            }
        )
    }
}

@Composable
fun DefaultAppBar(
    onSearchClicked: () -> Unit,
    onFilterListClicked: (Priority) -> Unit,
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
            SearchAction(onSearchClicked)

            FilterAction(onFilterListClicked)

            MoreOptionsAction(onDeleteAllTasksClicked)
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

    IconButton(
        onClick = { expanded.value = true },
        modifier = Modifier.testTag(TestTags.MORE_OPTIONS_TOOLBAR)
    ) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = null,
            tint = toolbarContentColor,
            modifier = Modifier.size(28.dp)
        )
        DropdownMenu(expanded = expanded.value, onDismissRequest = { dismissMenuItem() }) {
            DropdownMenuItem(
                onClick = { dismissMenuItem(action = onDeleteAllTasksClicked) },
                modifier = Modifier.testTag(TestTags.DELETE_ALL_MORE_OPTIONS)
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
    onSearch: (String) -> Unit,
) {
    val toolbarContentColor = MaterialTheme.colors.toolbarContent
    val toolbarBackgrounColor = MaterialTheme.colors.toolbarBackground
    val transparentColor = Color.Transparent

    val inputText = rememberSaveable { mutableStateOf(EMPTY_STRING) }
    val focusRequest = FocusRequester()
    LaunchedEffect(Unit) {
        focusRequest.requestFocus()
    }

    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = { onSearch(inputText.value) }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        modifier = Modifier
                            .alpha(ContentAlpha.disabled)
                    )
                }
                TextField(
                    modifier = Modifier.focusRequester(focusRequest),
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
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(onSearch = {
                        onSearch(inputText.value)
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