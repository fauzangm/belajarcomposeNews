package id.android.belajarcomposenewsapps.domain.pref

import kotlinx.coroutines.flow.Flow

interface LocalUserPref {

    suspend fun saveAppEntry()

    fun readAppEntry(): Flow<Boolean>
}