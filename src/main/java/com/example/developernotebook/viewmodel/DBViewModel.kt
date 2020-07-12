package com.example.developernotebook.viewmodel

import androidx.lifecycle.*
import com.example.developernotebook.model.DBUser
import com.example.developernotebook.model.User
import com.example.developernotebook.model.UserCreatedResponse
import com.example.developernotebook.repository.remote.DBService
import com.example.developernotebook.repository.remote.RetrofitInstance
import com.example.developernotebook.util.LiveEvent
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DBViewModel : ViewModel() {

    private val _viewState: MutableLiveData<User> = MutableLiveData()
    val viewState: LiveData<User> get() = _viewState.distinctUntilChanged()

    private val _users: MutableLiveData<List<DBUser>> = MutableLiveData()
    val users: LiveData<List<DBUser>> = _users.distinctUntilChanged()

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading.distinctUntilChanged()

    private val _viewEvent = LiveEvent<Event>()
    val viewEvent: LiveData<Event> get() = _viewEvent

    init {
        _isLoading.postValue(false)
    }

    private val apiService : DBService =
        RetrofitInstance.instance.create(DBService::class.java)

    fun fetchUsers() {
        _isLoading.postValue(true)
        viewModelScope.launch {
            val userJob: Deferred<List<DBUser>> =
                async(Dispatchers.IO) { apiService.getAllUsers() }
            _users.postValue(userJob.await())
            if(userJob.isCompleted){
                _isLoading.postValue(false)
            }
        }
    }

    fun addUser(user: User){
        _isLoading.postValue(true)
        var response: UserCreatedResponse
        viewModelScope.launch {
            val userJob: Deferred<UserCreatedResponse> =
                async(Dispatchers.IO) { apiService.createUser(user) }
            response = userJob.await()
            if(userJob.isCompleted){
                _isLoading.postValue(false)
            }
        }
    }

    private fun addUserToViewState(user: User?){
        if(user != null){
            _viewState.postValue(
                User(
                    firstName = user.firstName,
                    lastName = user.lastName,
                    email = user.email,
                    phoneNumber = user.phoneNumber,
                    address = user.address,
                    password = user.password
                )
            )
        }
    }

    fun onAction(action: Action) {
        when (action) {
            is Action.SetNewUser -> addUserToViewState(action.user)
            is Action.SetFirstName -> {}
            is Action.SetLastName -> {}
            is Action.SetEmailAddress -> {}
            is Action.SetPhoneNumber -> {}
            is Action.SetAddress -> {}
            is Action.SetPassword -> {}
            is Action.SetUserName -> {}
        }
    }

    sealed class Action {
        data class SetNewUser(val user: User) : Action()
        data class SetFirstName(val firstName: String?) : Action()
        data class SetLastName(val lastName: String?) : Action()
        data class SetEmailAddress(val emailAddress: String?) : Action()
        data class SetPhoneNumber(val phoneNumber: String?) : Action()
        data class SetAddress(val address: String?) : Action()
        data class SetPassword(val password: String?) : Action()
        data class SetUserName(val userName: String?) : Action()
    }

    sealed class Event {
        object ProceedWithRegistration : Event()
        object ErrorEvent : Event()
    }
}