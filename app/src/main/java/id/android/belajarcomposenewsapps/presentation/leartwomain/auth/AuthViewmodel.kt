package id.android.belajarcomposenewsapps.presentation.leartwomain.auth


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.map
import dagger.hilt.android.lifecycle.HiltViewModel
import id.android.belajarcomposenewsapps.data.repository.auth.LoginResult
import id.android.belajarcomposenewsapps.domain.usecase.presensi.PresensiUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val presensiUseCases: PresensiUseCases
) : ViewModel() {

    private val _loginResult = MutableStateFlow<LoginResult>(LoginResult())
    val loginResult = _loginResult.asStateFlow()

    fun loginPresensi(
        email: String,
        password: String
    ) {
        _loginResult.update { currentState ->
            currentState.copy(loading = true)
        }
        viewModelScope.launch {
            val result = presensiUseCases.login(email, password)
            _loginResult.value = result
            _loginResult.update { currentState ->
                currentState.copy(loading = false)

            }
        }

    }

    fun resetStateError() {
        _loginResult.update { currentState ->
            currentState.copy(isError = false)
        }
    }
}
