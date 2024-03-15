package id.android.belajarcomposenewsapps.domain.usecase.presensi.auth

import id.android.belajarcomposenewsapps.data.local.room.NewsDao
import id.android.belajarcomposenewsapps.data.remote.ApiService
import id.android.belajarcomposenewsapps.data.remote.ApiServicePresensi
import id.android.belajarcomposenewsapps.data.repository.auth.LoginResult
import id.android.belajarcomposenewsapps.domain.model.Article
import id.android.belajarcomposenewsapps.domain.repository.AuthRepository

class LoginPresensi(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String) : LoginResult {
        return authRepository.loginPresensi(email = email, password = password)
    }

}