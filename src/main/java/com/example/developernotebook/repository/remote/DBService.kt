package com.example.developernotebook.repository.remote

import com.example.developernotebook.model.DBUser
import com.example.developernotebook.model.User
import com.example.developernotebook.model.UserCreatedResponse
import retrofit2.http.*

interface DBService {

    @Headers("x-api-key: c41674f981b2b432637bc7cfd8625bd480250")
    @GET("rest/developers")
    suspend fun getAllUsers(): List<DBUser>

    @Headers("Content-Type: application/json","x-api-key: c41674f981b2b432637bc7cfd8625bd480250", "Cache-Control: no-cache")
    @POST("rest/developers")
    suspend fun createUser(@Body user: User): UserCreatedResponse


}