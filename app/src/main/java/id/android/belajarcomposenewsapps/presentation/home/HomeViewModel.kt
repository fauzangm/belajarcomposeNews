package id.android.belajarcomposenewsapps.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.android.belajarcomposenewsapps.domain.usecase.news.NewsUseCases
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {

    val news = newsUseCases.getNews(
        sources = listOf("bitcoin","bbc-news")
    ).cachedIn(viewModelScope)
}