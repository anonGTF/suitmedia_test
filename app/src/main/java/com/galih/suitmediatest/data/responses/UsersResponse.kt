package com.galih.suitmediatest.data.responses

import com.google.gson.annotations.SerializedName

data class UsersResponse(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("first_name")
    val firstName : String? = null,

    @SerializedName("last_name")
    val lastName : String? = null,

    @SerializedName("avatar")
    val avatar : String? = null,

    @SerializedName("email")
    val email : String? = null
)
