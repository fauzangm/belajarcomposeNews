package id.android.belajarcomposenewsapps.domain.model.presensi

import com.google.gson.annotations.SerializedName

data class Pivot(

	@field:SerializedName("role_id")
	val roleId: Int? = null,

	@field:SerializedName("model_type")
	val modelType: String? = null,

	@field:SerializedName("model_id")
	val modelId: Int? = null
)