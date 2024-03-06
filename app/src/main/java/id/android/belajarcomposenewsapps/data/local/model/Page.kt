package id.android.belajarcomposenewsapps.data.local.model

import androidx.annotation.DrawableRes
import id.android.belajarcomposenewsapps.R

data class Page(
    val title : String,
    val description : String,
    @DrawableRes val image : Int
)


val pages = listOf(
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.mipmap.onboarding1
    ),
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.mipmap.onboarding2
    ),
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.mipmap.onboarding3
    )
)