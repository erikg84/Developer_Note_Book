package com.example.developernotebook.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("first_name")
    val firstName: String? = null,

    @SerializedName("last_name")
    val lastName: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("address")
    val address: String? = null,

    @SerializedName("phone_number")
    val phoneNumber: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    var isValid: Boolean? = null
) : Parcelable