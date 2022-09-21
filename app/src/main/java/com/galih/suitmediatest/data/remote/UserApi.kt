package com.galih.suitmediatest.data.remote

import com.galih.suitmediatest.base.BaseListResponse
import com.galih.suitmediatest.data.responses.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("users")
    suspend fun getUsers(@Query("page") page : Int) : BaseListResponse<UsersResponse>
}