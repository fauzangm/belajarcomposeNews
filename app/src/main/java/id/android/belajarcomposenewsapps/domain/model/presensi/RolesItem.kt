package id.android.belajarcomposenewsapps.domain.model.presensi

import com.google.gson.annotations.SerializedName

data class RolesItem(

    @field:SerializedName("editor")
	val editor: String? = null,

    @field:SerializedName("creator")
	val creator: String? = null,

    @field:SerializedName("updated_at")
	val updatedAt: String? = null,

    @field:SerializedName("name")
	val name: String? = null,

    @field:SerializedName("description")
	val description: String? = null,

    @field:SerializedName("created_at")
	val createdAt: String? = null,

    @field:SerializedName("pivot")
	val pivot: Pivot? = null,

    @field:SerializedName("id")
	val id: Int? = null,

    @field:SerializedName("guard_name")
	val guardName: String? = null
)