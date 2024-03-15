package id.android.belajarcomposenewsapps.data.pref

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenHolder @Inject constructor(
    @ApplicationContext context: Context
) {

    companion object {

        // Sharedpref file name
        private const val PREF_NAME = "presensiTokenHolder"

        //        const val TOKEN_TYPE = "token_type"
        //        const val EXPIRES_IN = "expires_in"
        private const val ACCESS_TOKEN = "access_token"

        //        const val REFRESH_TOKEN = "refresh_token"
        private const val FCM_TOKEN = "fcmToken"
    }

    // Shared Preferences
    private var pref: SharedPreferences

    // Editor for Shared preferences
    private var editor: SharedPreferences.Editor

    // Shared pref mode
    private var privateMode = 0

    /**
     * Inisiasi Shared Preferences
     */
    init {
        pref = context.getSharedPreferences(PREF_NAME, privateMode)
        editor = pref.edit()
        editor.apply()
    }

    val getData: HashMap<String, String?>
        get() {
            val user = HashMap<String, String?>()
//            user[TOKEN_TYPE] = pref.getString(TOKEN_TYPE, "")
//            user[EXPIRES_IN] = pref.getString(
//                EXPIRES_IN, "")
            user[ACCESS_TOKEN] = pref.getString(
                ACCESS_TOKEN, ""
            )
//            user[REFRESH_TOKEN] = pref.getString(
//                REFRESH_TOKEN, "")
            return user
        }

    fun storeToken(token: String) {
//        editor.putString(TOKEN_TYPE, token.tokenType)
//        editor.putString(EXPIRES_IN, token.expiresIn.toString())
        editor.putString(ACCESS_TOKEN, token)
//        editor.putString(REFRESH_TOKEN, token.refreshToken)
        editor.commit()
    }

    fun getFCMToken(): String? {
        return pref.getString(FCM_TOKEN, null)
    }

    fun storeFCMToken(token: String) {
        editor.putString(FCM_TOKEN, token)
        editor.commit()
    }

    fun clearToken() {
        editor.clear()
        editor.commit()
    }

    val accessToken: String? get() = pref.getString(ACCESS_TOKEN, "")
    val fcmToken: String? get() = pref.getString(FCM_TOKEN, "")
//    val refreshToken: String? get() = pref.getString(REFRESH_TOKEN, "")
}