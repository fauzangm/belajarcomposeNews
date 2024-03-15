package id.android.belajarcomposenewsapps.domain.usecase.presensi

import id.android.belajarcomposenewsapps.domain.usecase.news.dbNews.DeleteArticle
import id.android.belajarcomposenewsapps.domain.usecase.news.dbNews.GetArticles
import id.android.belajarcomposenewsapps.domain.usecase.news.dbNews.SelectArticle
import id.android.belajarcomposenewsapps.domain.usecase.news.dbNews.UpsertArticle
import id.android.belajarcomposenewsapps.domain.usecase.presensi.auth.LoginPresensi

data class PresensiUseCases(
    val login: LoginPresensi,
)