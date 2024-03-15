package id.android.belajarcomposenewsapps.domain.model.presensi

import com.google.gson.annotations.SerializedName

data class DataLoginResponse(

	@field:SerializedName("access_token")
	val accessToken: String? = null,

	@field:SerializedName("data")
	val dataUser: DataUser? = null,

	@field:SerializedName("token_type")
	val tokenType: String? = null
)