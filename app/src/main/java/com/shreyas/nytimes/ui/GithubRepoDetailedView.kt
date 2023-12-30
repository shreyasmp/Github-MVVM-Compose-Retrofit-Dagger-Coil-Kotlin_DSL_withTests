package com.shreyas.nytimes.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.shreyas.nytimes.R
import com.shreyas.nytimes.model.RepositoryData
import com.shreyas.nytimes.ui.theme.GithubRepositoryTheme
import com.shreyas.nytimes.utils.GithubRepositoryDataProvider

@Composable
fun GithubRepoDetailedView(
    repositoryData: RepositoryData
) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    GithubRepoHeader(
                        scrollState,
                        repositoryData,
                        this@BoxWithConstraints.maxHeight
                    )
                    GithubRepoContent(
                        repositoryData = repositoryData,
                        containerHeight = this@BoxWithConstraints.maxHeight
                    )
                }
            }
        }
    }
}

@Composable
private fun GithubRepoHeader(
    scrollState: ScrollState,
    repositoryData: RepositoryData,
    containerHeight: Dp
) {
    val offSet = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) { offSet.toDp() }
    val avatar: Painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(repositoryData.owner.avatar_url)
            .size(Size.ORIGINAL)
            .build()
    )

    Image(
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth()
            .padding(top = offsetDp),
        painter = avatar,
        contentDescription = "Github Repository Avatar",
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun GithubRepoContent(
    repositoryData: RepositoryData,
    containerHeight: Dp
) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))

        RepoName(repositoryData)

        RepoProperty(
            label = stringResource(id = R.string.description),
            value = repositoryData.description
        )

        RepoProperty(
            label = stringResource(id = R.string.htmlUrl),
            value = repositoryData.html_url,
            isLink = true
        )

        // Add a spacer that always shows part (320.dp) of the fields list regardless of the device,
        // in order to always leave some content at the top.
        Spacer(modifier = Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}

@Composable
private fun RepoName(
    repositoryData: RepositoryData
) {
    Row(
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Text(
            text = repositoryData.name,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 15.dp),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            minLines = 1,
            maxLines = 2
        )
    }
}

@Composable
private fun RepoProperty(
    label: String,
    value: String,
    isLink: Boolean = false
) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider()
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = label,
                modifier = Modifier
                    .height(24.dp)
                    .padding(top = 8.dp),
                style = MaterialTheme.typography.caption,
            )
        }
        val style = if (isLink) {
            MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.primary)
        } else {
            MaterialTheme.typography.body1
        }
        Text(
            text = value,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 4.dp),
            style = style,
            minLines = 3
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Preview("Night", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun GithubRepoDetailedViewPreview() {
    GithubRepositoryTheme {
        GithubRepoDetailedView(repositoryData = GithubRepositoryDataProvider.repositoryData)
    }
}

