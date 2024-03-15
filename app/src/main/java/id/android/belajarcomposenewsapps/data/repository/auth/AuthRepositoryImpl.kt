package id.android.belajarcomposenewsapps.data.repository.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import id.android.belajarcomposenewsapps.data.remote.ApiServicePresensi
import id.android.belajarcomposenewsapps.data.remote.response.LoginResponse
import id.android.belajarcomposenewsapps.di.IoDispatcher
import id.android.belajarcomposenewsapps.domain.model.Error.Companion.getErrorMessage
import id.android.belajarcomposenewsapps.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiServicePresensi: ApiServicePresensi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AuthRepository {

    override suspend fun loginPresensi(email: String, password: String): LoginResult {
        var error: String? = null
        var logResponse: LoginResponse? = null
        var isError = false
        try {
            val service = apiServicePresensi.login(email = email, password = password)
            Log.e("network", "start service")
            if (service.isSuccessful) {
                Log.e("network", "success network")
                logResponse = service.body()
            } else {
                isError = true
                Log.e("network", "Error network")
                error = service.errorBody()?.let { getErrorMessage(it.string()) }
            }
        } catch (e: Exception) {
            isError = true
            e.printStackTrace()
            error = e.localizedMessage
        }

        return LoginResult(errorMessage = error ?: "", response = logResponse, isError = isError)
    }

}