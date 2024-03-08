package id.android.belajarcomposenewsapps.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.android.belajarcomposenewsapps.domain.model.Article

class NewsPagingSource(
    private val apiService: ApiService,
    private val sources: String
) : PagingSource<Int, Article>() {


    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = apiService.getNews(page = page, source = "bitcoin" , from = "2024-03-06", sortBy = "publishedAt")
            totalNewsCount += newsResponse.articles.size
            Log.e("error network","cek = ${newsResponse.status}")
            val articles = newsResponse.articles.distinctBy { it.title } //Remove duplicates

            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }
}