package id.android.belajarcomposenewsapps.presentation.details

import id.android.belajarcomposenewsapps.domain.model.Article

sealed class DetailEvents {
    data class UpsertDeleteArticle(val article: Article) : DetailEvents()
    object RemoveSideEffect : DetailEvents()

}