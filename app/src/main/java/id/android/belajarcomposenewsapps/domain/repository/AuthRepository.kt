package id.android.belajarcomposenewsapps.domain.repository

import id.android.belajarcomposenewsapps.data.repository.auth.LoginResult

interface AuthRepository {

    suspend fun loginPresensi(email : String , password : String) : LoginResult
}