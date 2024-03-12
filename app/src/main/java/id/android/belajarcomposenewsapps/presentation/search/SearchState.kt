package id.android.belajarcomposenewsapps.presentation.search

import androidx.paging.PagingData
import id.android.belajarcomposenewsapps.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)