package id.android.belajarcomposenewsapps.domain.model

import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import id.android.belajarcomposenewsapps.utils.Constans.UNKNOWN_FORMAT
import id.android.belajarcomposenewsapps.utils.Constans.UNKNOWN_NETWORK_ERROR
import kotlinx.parcelize.Parcelize

@Parcelize
class Error(
    @SerializedName("message") val message: String?,
    @SerializedName("messages") val messages: String?
) : Parcelable {

    companion object {
        fun getErrorMessage(jsonString: String): String {
            return try {
            val error = Gson().fromJson(jsonString, Error::class.java)
                when {
                    !error.message.isNullOrBlank()  -> error.message
                    !error.messages.isNullOrBlank() -> error.messages
                    else -> UNKNOWN_NETWORK_ERROR
                }
            } catch (e: Exception) {
                e.printStackTrace()
                UNKNOWN_FORMAT
            }
        }
    }
}