package id.android.belajarcomposenewsapps.data.remote.response

import id.android.belajarcomposenewsapps.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)