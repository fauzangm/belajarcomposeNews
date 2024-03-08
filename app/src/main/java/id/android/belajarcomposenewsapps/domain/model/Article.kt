package id.android.belajarcomposenewsapps.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.android.belajarcomposenewsapps.domain.model.Source
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    @PrimaryKey val url: String,
    val urlToImage: String
) : Parcelable