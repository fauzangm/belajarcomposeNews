package id.android.belajarcomposenewsapps.presentation.bookmark

import id.android.belajarcomposenewsapps.domain.model.Article

data class BookMarkState (
    val articles : List<Article> = emptyList()
)