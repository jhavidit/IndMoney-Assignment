package tech.jhavidit.indmoney_assignment.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import tech.jhavidit.indmoney_assignment.model.UserResponse
import tech.jhavidit.indmoney_assignment.repository.UserApiRepository
import tech.jhavidit.indmoney_assignment.utilities.Resource
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val userApiRepository: UserApiRepository,
) : ViewModel() {

    private var _userResponse = MutableLiveData<Resource<List<UserResponse>>>()
    val userResponse: LiveData<Resource<List<UserResponse>>>
        get() = _userResponse

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            _userResponse.value = (Resource.loading(null))
            supervisorScope {
                val userResponseAsync =
                    async { userApiRepository.getUsers() }
                try {
                    _userResponse.value = (Resource.success(userResponseAsync.await()))
                } catch (e: Exception) {
                    _userResponse.value = (Resource.error(e.toString(), null))
                }

            }
        }
    }


}