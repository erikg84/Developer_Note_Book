package com.example.developernotebook.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserCreatedResponse(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("_tags")
	val tags: String? = null,

	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("_createdby")
	val createdby: String? = null,

	@field:SerializedName("_changed")
	val changed: String? = null,

	@field:SerializedName("_changedby")
	val changedby: String? = null,

	@field:SerializedName("_created")
	val created: String? = null,

	@field:SerializedName("phone_number")
	val phoneNumber: String? = null,

	@field:SerializedName("_keywords")
	val keywords: List<String?>? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null,

	@field:SerializedName("_version")
	val version: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("password")
	val password: String? = null
) : Parcelable
