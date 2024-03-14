package id.android.belajarcomposenewsapps.data.local.model

import androidx.annotation.DrawableRes
import id.android.belajarcomposenewsapps.R

data class PagesEwarga(
    val tittle : String,
    val description : String,
    @DrawableRes val image : Int?
)


val pagesEwarga = listOf(
    PagesEwarga(
        tittle = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = null
    ),
    PagesEwarga(
        tittle = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = null
    ),
    PagesEwarga(
        tittle = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = null
    )
)