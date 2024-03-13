package id.android.belajarcomposenewsapps.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import id.android.belajarcomposenewsapps.R
import id.android.belajarcomposenewsapps.domain.model.Article
import id.android.belajarcomposenewsapps.presentation.common.ArticlesList
import id.android.belajarcomposenewsapps.presentation.navigation.Route
import id.android.belajarcomposenewsapps.utils.Dimens


@Composable
fun BookMarkScreen(
    state: BookMarkState,
    navigateToDetails: (Article) -> Unit
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(
                top = Dimens.MediumPadding1,
                start = Dimens.MediumPadding1,
                end = Dimens.MediumPadding1
            )
    ) {

        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.Bold,
            ),
            color = colorResource(id = R.color.text_title)
        )

        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))


        ArticlesList(
            articles = state.articles,
            onClick = navigateToDetails
        )
    }
}