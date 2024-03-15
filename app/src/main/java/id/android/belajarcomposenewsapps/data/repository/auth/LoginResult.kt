package id.android.belajarcomposenewsapps.data.repository.auth

import androidx.lifecycle.LiveData
import id.android.belajarcomposenewsapps.data.remote.response.LoginResponse

data class LoginResult(
    val errorMessage: String = "",
    val isError : Boolean = false,
    val loading: Boolean = false,
    val response: LoginResponse? = null
)
