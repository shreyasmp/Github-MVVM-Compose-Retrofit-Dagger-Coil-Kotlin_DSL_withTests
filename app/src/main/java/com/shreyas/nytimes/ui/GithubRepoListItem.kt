package com.shreyas.nytimes.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.shreyas.nytimes.R
import com.shreyas.nytimes.model.OwnerData
import com.shreyas.nytimes.model.RepositoryData
import com.shreyas.nytimes.ui.theme.GithubRepositoryTheme
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Composable
fun GithubRepoListItem(
    repoData: RepositoryData,
    navigateToGitHubDetailView: (RepositoryData) -> Unit
) {

    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.onSurface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(140.dp)
                .clickable { navigateToGitHubDetailView(repoData) }
        ) {

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                val avatar: Painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(repoData.owner.avatar_url)
                        .size(Size.ORIGINAL)
                        .build()
                )
                DrawComposableImage(
                    image = avatar,
                    composeContentDescription = "Github Repository Avatar",
                    width = 80.dp,
                    height = 80.dp,
                    size = 16.dp
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.align(Alignment.CenterVertically)) {

                DrawComposableText(
                    content = repoData.name,
                    start = 0.dp,
                    top = 0.dp,
                    end = 12.dp,
                    bottom = 0.dp,
                    weightOfFont = FontWeight.Bold,
                    textSize = 20.sp,
                    styleOfText = MaterialTheme.typography.h5,
                    maxLine = 1,
                    tag = "repoName"
                )

                Spacer(modifier = Modifier.height(4.dp))

                DrawComposableText(
                    content = repoData.description,
                    start = 0.dp,
                    top = 0.dp,
                    end = 12.dp,
                    bottom = 0.dp,
                    weightOfFont = FontWeight.Normal,
                    textSize = 16.sp,
                    styleOfText = MaterialTheme.typography.h6,
                    maxLine = 3,
                    tag = "repoDesc"
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .height(20.dp)
                ) {

                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {

                        DrawComposableImage(
                            image = painterResource(id = R.drawable.ic_star),
                            composeContentDescription = "Repository Stars Count",
                            width = 12.dp,
                            height = 12.dp,
                            size = 1.dp
                        )
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    DrawComposableText(
                        content = repoData.stargazers_count.toString(),
                        start = 0.dp,
                        top = 0.dp,
                        end = 12.dp,
                        bottom = 0.dp,
                        weightOfFont = FontWeight.Normal,
                        textSize = 12.sp,
                        styleOfText = MaterialTheme.typography.h6,
                        maxLine = 1,
                        tag = "repoStarGazerCount"
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        DrawComposableImage(
                            image = painterResource(id = R.drawable.ic_fork),
                            composeContentDescription = "Repository Forks Count",
                            width = 12.dp,
                            height = 12.dp,
                            size = 1.dp
                        )
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    DrawComposableText(
                        content = repoData.forks_count.toString(),
                        start = 0.dp,
                        top = 0.dp,
                        end = 12.dp,
                        bottom = 0.dp,
                        weightOfFont = FontWeight.Normal,
                        textSize = 12.sp,
                        styleOfText = MaterialTheme.typography.h6,
                        maxLine = 1,
                        tag = "repoForksCount"
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                DrawComposableText(
                    content = "Last updated on " + setFormattedDate(repoData.updated_at),
                    start = 0.dp,
                    top = 0.dp,
                    end = 12.dp,
                    bottom = 0.dp,
                    weightOfFont = FontWeight.Normal,
                    textSize = 12.sp,
                    styleOfText = MaterialTheme.typography.h6,
                    maxLine = 1,
                    tag = "repoLastUpdatedDate"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Preview("Night", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun GithubRepositoryLineItemPreview() {
    GithubRepositoryTheme {
        GithubRepoListItem(previewRepoData(), navigateToGitHubDetailView = {})
    }
}

private fun previewRepoData(): RepositoryData = RepositoryData(
    name = "okhttp",
    description = "Square’s meticulous HTTP client for the JVM, Android, and GraalVM.",
    stargazers_count = 44786,
    forks_count = 9275,
    updated_at = "2023-12-12T02:20:23Z",
    html_url = "https://github.com/square/okhttp",
    owner = OwnerData(avatar_url = "https://avatars.githubusercontent.com/u/82592?v=4")
)

@Composable
private fun DrawComposableImage(
    image: Painter,
    composeContentDescription: String,
    width: Dp,
    height: Dp,
    size: Dp
) {
    Image(
        painter = image,
        modifier = Modifier
            .size(width, height)
            .clip(RoundedCornerShape(size)),
        alignment = Alignment.CenterStart,
        contentDescription = composeContentDescription,
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun DrawComposableText(
    content: String?,
    start: Dp,
    top: Dp,
    end: Dp,
    bottom: Dp,
    weightOfFont: FontWeight?,
    textSize: TextUnit,
    styleOfText: TextStyle,
    maxLine: Int,
    tag: String
) {
    if (content != null) {
        Text(
            text = content,
            modifier = Modifier
                .padding(start, top, end, bottom)
                .testTag(tag),
            color = MaterialTheme.colors.surface,
            fontWeight = weightOfFont,
            fontSize = textSize,
            style = styleOfText,
            maxLines = maxLine
        )
    }
}

private fun setFormattedDate(dateTime: String): String {
    val dateTimeInstance = LocalDateTime.ofInstant(Instant.parse(dateTime), ZoneOffset.UTC)
    return DateTimeFormatter.ofPattern(LAST_UPDATED_DATE_FORMAT).format(dateTimeInstance).toString()
}

const val LAST_UPDATED_DATE_FORMAT: String = "dd-MMM-yyyy hh:mm:ss"
