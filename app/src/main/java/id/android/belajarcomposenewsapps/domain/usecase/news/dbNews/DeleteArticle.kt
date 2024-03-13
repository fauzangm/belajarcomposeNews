package id.android.belajarcomposenewsapps.domain.usecase.news.dbNews

import id.android.belajarcomposenewsapps.data.local.room.NewsDao
import id.android.belajarcomposenewsapps.domain.model.Article

class DeleteArticle (
    private  val newsDao: NewsDao
){

    suspend operator fun invoke(article: Article){
        newsDao.delete(article)
    }

}