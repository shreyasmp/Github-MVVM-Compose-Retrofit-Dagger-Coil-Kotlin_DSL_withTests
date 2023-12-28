package com.shreyas.nytimes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.shreyas.nytimes.R
import com.shreyas.nytimes.model.RepositoryData
import com.shreyas.nytimes.utils.SearchState
import com.shreyas.nytimes.viewmodel.GithubSearchViewModel

@Composable
fun SearchTopAppBar(
    viewModel: GithubSearchViewModel,
    searchState: SearchState,
    searchTextState: String
) {
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
        color = Color.Red
    ) {
        TextField(
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            modifier = Modifier.fillMaxSize(),
            placeholder = {
                Text(
                    text = "Type repository name here...",
                    color = Color.Black,
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
                        tint = Color.Black
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
                        tint = Color.Black
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
                cursorColor = Color.Black.copy(alpha = ContentAlpha.medium)
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
        backgroundColor = colorResource(id = R.color.red),
        actions = {
            IconButton(onClick = { onSearchClicked() }) {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Search GitHub Repo",
                    tint = Color.Black
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

@Composable
fun SearchList(
    padding: PaddingValues,
    repositoriesData: MutableList<RepositoryData>?,
    isLoading: Boolean,
    navigateToDetailView: (RepositoryData) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repositoriesData?.let {
            items(it) { data ->
                GithubRepoListItem(repoData = data, navigateToGitHubDetailView = navigateToDetailView)
            }
        }
    }
    if (isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            CircularProgressIndicator()
        }
    }
}

//@Preview(showSystemUi = true)
//@Preview("Night", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Composable
//private fun GitSearchViewActionBarPreview() {
//    GithubRepositoryTheme {
//        SearchViewActionBar(viewModel =)
//    }
//}
