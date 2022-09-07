package tech.jhavidit.indmoney_assignment.network

import retrofit2.http.GET
import tech.jhavidit.indmoney_assignment.model.UserResponse


interface ApiService {

    @GET("user")
    suspend fun getUsersResponse(): List<UserResponse>

}