package id.android.belajarcomposenewsapps.data.pref

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import id.android.belajarcomposenewsapps.domain.model.presensi.DataLoginResponse
import javax.inject.Inject

class SessionManager @Inject constructor(
    @ApplicationContext val context: Context,
    private val tokenHolder: TokenHolder
) {
    companion object {
        private const val PREF_NAME = "dataLoginPresensiWPCache"
        private const val DATA_LOGIIN = "dataLoginCache"
        private const val DATA_LOGIN = "dataLogin"

        // Login state
        private const val IS_LOGIN = "isLoggedIn"
        private const val USER_KEY = "userKey"

    }

    private var pref: SharedPreferences
    private var editor: SharedPreferences.Editor
    private var privateMode = 0

    init {
        pref = context.getSharedPreferences(PREF_NAME, privateMode)
        editor = pref.edit()
        editor.apply()
    }

    /**
     * Menyimpan data login setelah berhasil melakukan otentikasi ke backend
     */
    fun createLoginSession(dataLogin: DataLoginResponse) {

        // Storing login value as TRUE
        val strUser = Gson().toJson(dataLogin)
        pref.edit()
            .putBoolean(IS_LOGIN, true)
            .putString(USER_KEY, strUser)
            .apply()

        // commit changes
        editor.apply()
    }

    /**
     * mengambil data login tersimpan
     */
    fun getUser(): DataLoginResponse? {
        val strUser: String? = pref.getString(USER_KEY, null)
        return Gson().fromJson(strUser, DataLoginResponse::class.java)
    }

    /**
     * Quick check status login
     */
    private val isLoggedIn: Boolean
        get() = pref.getBoolean(IS_LOGIN, false)

    /**
     * Memeriksa status login, jika tidak ada user yang login akan me-redirect ke halaman Login
     */
//    fun checkLogin() {
//        // Check login status
//        if (!this.isLoggedIn) {
//            // Clearing all data from Shared Preferences
//            editor.clear()
//            editor.apply()
//
//            tokenHolder.clearToken()
//
//            // Starting Login Activity
//            context.start<AuthActivity>() {
//                this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            }
//        }
//
//    }

    /**
     * Proses logout, clear session data dan redirect ke halaman Login
     */
//    fun logoutUser() {
//        // Clearing all data from Shared Preferences
//        editor.clear()
//        editor.apply()
//
//        tokenHolder.clearToken()
//
//        // Starting Login Activity
//        context.start<LoginActivity>() {
//            this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//            this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        }
//    }


    fun put(key: String, value: String) {
        editor.putString(key, value)
            .apply()
    }

    fun getString(key: String): String? {
        return pref.getString(key, "")
    }

    fun put(key: String, value: Boolean) {
        editor.putBoolean(key, value)
            .apply()
    }


    fun getBoolean(key: String): Boolean {
        return pref.getBoolean(key, false)
    }

    fun clear() {
        // Clearing all data from Shared Preferences
        editor.clear()
        editor.apply()

        // Starting Login Activity
//        context.start<AuthActivity>() {
//            this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//            this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        }
    }
}