package id.android.belajarcomposenewsapps.domain.usecase.news

import id.android.belajarcomposenewsapps.domain.usecase.news.dbNews.DeleteArticle
import id.android.belajarcomposenewsapps.domain.usecase.news.dbNews.GetArticles
import id.android.belajarcomposenewsapps.domain.usecase.news.dbNews.SelectArticle
import id.android.belajarcomposenewsapps.domain.usecase.news.dbNews.UpsertArticle

data class NewsUseCases(
    val getNews: GetNews,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val selectArticle: SelectArticle,
    val getArticle: GetArticles
)