package id.android.belajarcomposenewsapps.presentation.onboarding.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.packFloats
import id.android.belajarcomposenewsapps.R
import id.android.belajarcomposenewsapps.presentation.onboarding.Page
import id.android.belajarcomposenewsapps.ui.theme.BelajarcomposenewsappsTheme
import id.android.belajarcomposenewsapps.utils.Dimens


@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.60f),
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop // biar scalenya matchparent
        )
        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = Dimens.MediumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = Dimens.titleText
            ),
            color = colorResource(id = R.color.display_small)
        )

        Text(
            text = page.description,
            modifier = Modifier
                .padding(horizontal = Dimens.MediumPadding2)
                .padding(top = Dimens.defaultPadding )
            ,
            color = colorResource(id = R.color.text_medium),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = Dimens.mediumText

        )
    }

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun OnBoardingPagePreview() {
    BelajarcomposenewsappsTheme {
        OnBoardingPage(
            page = Page(
                title = "Lorem Ipsum is simply dummy",
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                image = R.mipmap.onboarding1
            )
        )
    }
}