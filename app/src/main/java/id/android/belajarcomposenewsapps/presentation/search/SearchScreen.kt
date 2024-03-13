package id.android.belajarcomposenewsapps.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import id.android.belajarcomposenewsapps.domain.model.Article
import id.android.belajarcomposenewsapps.presentation.common.ArticlesList
import id.android.belajarcomposenewsapps.presentation.common.SearchBar
import id.android.belajarcomposenewsapps.utils.Dimens

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Dimens.MediumPadding1)
            .padding(horizontal = 16.dp)
            .statusBarsPadding()
    ) {

        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = { event(SearchEvent.SearchNews) }

        )

        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

        state.articles?.let { 
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(articles = articles, onClick = {navigateToDetails(it)} )
        }

    }
}