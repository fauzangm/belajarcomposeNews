package id.android.belajarcomposenewsapps.presentation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import id.android.belajarcomposenewsapps.R
import id.android.belajarcomposenewsapps.domain.model.Article
import id.android.belajarcomposenewsapps.domain.model.Source
import id.android.belajarcomposenewsapps.presentation.details.components.DetailTopBar
import id.android.belajarcomposenewsapps.ui.theme.BelajarcomposenewsappsTheme
import id.android.belajarcomposenewsapps.utils.Dimens


@Composable
fun DetailScreen(
    article: Article,
    events: (DetailEvents) -> Unit,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
    ) {
        DetailTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookmarkClick = { events(DetailEvents.SaveArticle) },
            onBackClick = navigateUp

        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(
                start = Dimens.MediumPadding1,
                end = Dimens.MediumPadding1,
                top = Dimens.MediumPadding1
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(248.dp)
                        .clip(MaterialTheme.shapes.medium),

                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

                Text(
                    text = article.title,
                    fontSize = Dimens.titleText,
                    color = colorResource(id = R.color.text_title)
                )
                Text(
                    text = article.content,
                    fontSize = Dimens.normalText,
                    color = colorResource(id = R.color.text_title)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview(){
    BelajarcomposenewsappsTheme(dynamicColor = false) {
        DetailScreen(article = Article(
            author = "",
            title = "Ini tittle",
            description = "ini deskripsi",
            content = "ini content",
            publishedAt = "2023-06-15T22:24:33Z",
            source = Source(
                id = "",
                name = "bbc"
            ),
            url = "",
            urlToImage = ""
        ) , events ={} ) {

        }
    }
}