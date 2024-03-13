package id.android.belajarcomposenewsapps.presentation.leartwomain.L2onBoarding

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import id.android.belajarcomposenewsapps.R
import id.android.belajarcomposenewsapps.presentation.common.ImageWithDefault
import id.android.belajarcomposenewsapps.presentation.common.PrimaryOutlineButton
import id.android.belajarcomposenewsapps.presentation.details.components.DetailTopBar
import id.android.belajarcomposenewsapps.ui.theme.BelajarcomposenewsappsTheme
import id.android.belajarcomposenewsapps.utils.Dimens


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

        Spacer(modifier = Modifier.height(32.dp))
        ImageWithDefault(
            url = null,
            height = 300.dp,
        )

        val text = "Kelebihan Kami Pertamax"
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
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur luctus.",
            textAlign = TextAlign.Center,
            color = Color.LightGray
            )


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

