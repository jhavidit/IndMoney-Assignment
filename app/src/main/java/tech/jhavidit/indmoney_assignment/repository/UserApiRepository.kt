package tech.jhavidit.indmoney_assignment.repository

import tech.jhavidit.indmoney_assignment.model.UserResponse


interface UserApiRepository {

    suspend fun getUsers() : List<UserResponse>

}