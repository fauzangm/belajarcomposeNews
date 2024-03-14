package id.android.belajarcomposenewsapps.presentation.leartwomain.onBoarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.android.belajarcomposenewsapps.R
import id.android.belajarcomposenewsapps.data.local.model.pages
import id.android.belajarcomposenewsapps.data.local.model.pagesEwarga
import id.android.belajarcomposenewsapps.presentation.common.ImageWithDefault
import id.android.belajarcomposenewsapps.presentation.common.NewsButton
import id.android.belajarcomposenewsapps.presentation.common.NewsTextButton
import id.android.belajarcomposenewsapps.presentation.common.PrimaryOutlineButton
import id.android.belajarcomposenewsapps.presentation.onboarding.OnBoardingEvent
import id.android.belajarcomposenewsapps.presentation.onboarding.components.PageIndicator
import id.android.belajarcomposenewsapps.ui.theme.BelajarcomposenewsappsTheme
import id.android.belajarcomposenewsapps.utils.Dimens
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    navigateUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .statusBarsPadding()
            .padding(vertical = 32.dp, horizontal = 24.dp)
    ) {

        val pagerState = rememberPagerState(initialPage = 0) {
            pagesEwarga.size
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = navigateUp) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = null
                )

            }

            PrimaryOutlineButton(text = "Lewati", onClick = {})

        }
        HorizontalPager(
            modifier = Modifier.padding(vertical = 24.dp),
            state = pagerState) { index ->
            val item = pagesEwarga[index]
            Spacer(modifier = Modifier.height(32.dp))
            Column {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    ImageWithDefault(
                        url = item.image,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height = 250.dp)
                            .background(Color.LightGray, RoundedCornerShape(16.dp))
                    )
                }


                val text = item.tittle
                val annotatedString = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = colorResource(id = R.color.color_primary_brown))) {
                        append(text.substring(0, 9)) // Teks "Kelebihan "
                    }
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append(text.substring(9)) // Teks "Kami Pertamax"
                    }
                }

                Text(
                    text = annotatedString,
                    modifier = Modifier.padding(16.dp),
                    fontSize = Dimens.titleText,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = item.description,
                    textAlign = TextAlign.Center,
                    color = Color.LightGray
                )
            }

        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Dimens.MediumPadding2)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                modifier = Modifier.width(52.dp),
                pageSize = pagesEwarga.size,
                selectedPage = pagerState.currentPage,
                selectedColor = colorResource(id = R.color.color_primary)
            )

        }
        Spacer(modifier = Modifier.weight(0.5f))


    }
}

@Preview
@Preview(showBackground = true)
@Composable
fun OnboardingScreePreview() {
    BelajarcomposenewsappsTheme {
        OnboardingScreen {

        }
    }
}

