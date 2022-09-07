package tech.jhavidit.indmoney_assignment.repository


import tech.jhavidit.indmoney_assignment.model.UserResponse
import tech.jhavidit.indmoney_assignment.network.ApiService
import javax.inject.Inject

class UserApiRepositoryImplementation @Inject constructor(private val apiService: ApiService) :
    UserApiRepository {

    override suspend fun getUsers(): List<UserResponse> {
        return apiService.getUsersResponse()
    }
}