package com.shreyas.nytimes.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shreyas.nytimes.R
import com.shreyas.nytimes.ui.theme.Purple700
import com.shreyas.nytimes.utils.SearchState
import com.shreyas.nytimes.viewmodel.GithubSearchViewModel

@Composable
fun SearchViewActionBar(
    viewModel: GithubSearchViewModel
) {
    val searchState by viewModel.searchState
    val searchTextState by viewModel.searchTextState
    val context = LocalContext.current

    Scaffold(
        topBar = {
            AppBar(
                searchState = searchState,
                searchTextState = searchTextState,
                onTextChange = {
                    viewModel.updateSearchTextState(it)
                },
                onCloseClicked = {
                    viewModel.updateSearchState(SearchState.CLOSED)
                },
                onSearchClicked = {
                    viewModel.fetchMostPopularGitHubRepos(it)
                },
                onSearchTriggered = {
                    viewModel.updateSearchState(SearchState.OPENED)
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Repo Image",
                    tint = Color.Black,
                    modifier = Modifier.size(150.dp)
                )
                Text(
                    text = "Search Repository",
                    fontSize = 25.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    )
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = Purple700
    ) {
        TextField(
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Search here...",
                    color = Color.White,
                    modifier = Modifier.alpha(ContentAlpha.medium)
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    onClick = { },
                    modifier = Modifier.alpha(ContentAlpha.medium)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "SearchImage",
                        tint = Color.White
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                        } else {
                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Image",
                        tint = Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
            )
        )
    }
}

@Composable
fun DefaultAppBar(
    onSearchClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "Github Repo Search")
        },
        backgroundColor = colorResource(id = R.color.purple_700),
        actions = {
            IconButton(onClick = { onSearchClicked() }) {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Search GitHub Repo",
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
fun AppBar(
    searchState: SearchState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit
) {
    when (searchState) {
        SearchState.CLOSED -> {
            DefaultAppBar(
                onSearchClicked = onSearchTriggered
            )
        }
        SearchState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }
}
