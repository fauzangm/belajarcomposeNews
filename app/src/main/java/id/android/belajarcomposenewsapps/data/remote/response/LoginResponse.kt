package id.android.belajarcomposenewsapps.data.remote.response

import com.google.gson.annotations.SerializedName
import id.android.belajarcomposenewsapps.domain.model.presensi.DataLoginResponse

data class LoginResponse(

    @field:SerializedName("data")
	val dataLoginResponse: DataLoginResponse? = null,

    @field:SerializedName("success")
	val success: Boolean? = null,

    @field:SerializedName("message")
	val message: String? = null
)