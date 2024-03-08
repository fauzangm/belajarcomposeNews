package id.android.belajarcomposenewsapps.presentation.home.component

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import id.android.belajarcomposenewsapps.R
import id.android.belajarcomposenewsapps.domain.model.Article
import id.android.belajarcomposenewsapps.domain.model.Source
import id.android.belajarcomposenewsapps.ui.theme.BelajarcomposenewsappsTheme
import id.android.belajarcomposenewsapps.utils.Dimens


@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    Row(modifier = modifier.clickable { onClick() }) {

        AsyncImage(
            modifier = Modifier
                .size(Dimens.ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )


        Column(
            modifier = Modifier
                .padding(horizontal = Dimens.ExtraSmallPadding)
                .height(
                    Dimens.ArticleCardSize
                )
        ) {
            Text(
                text = article.title,
                color = colorResource(id = R.color.text_title), maxLines = 2,
                overflow = TextOverflow.Ellipsis

            )


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 8.dp)
            ){
                Text(
                    text = article.source.name,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray,
                    fontSize = 12.sp

                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_time) ,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .padding(top = 2.dp)
                    ,
                    tint = colorResource(id = R.color.body)
                    )

                Text(
                    text = article.publishedAt + "oksdoksodkoskdosdkosodskkdos",
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray,
                    fontSize = 12.sp
                    , maxLines = 1,
                    overflow = TextOverflow.Ellipsis

                )
            }

        }
    }

}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview(

) {
    BelajarcomposenewsappsTheme {
        ArticleCard(
            article = Article(
                author = "",
                content = "",
                description = "",
                publishedAt = "2 hours",
                source = Source(id = "", name = "BBC News"),
                title = "Halooo gess ini testing jadi ini judul dari artikel yang dikemas dan di pole sediansaidfsojofasd",
                url = "",
                urlToImage = ""
            )
        ) {

        }
    }
}