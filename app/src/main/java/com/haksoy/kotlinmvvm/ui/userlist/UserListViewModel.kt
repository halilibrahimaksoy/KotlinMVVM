package com.haksoy.kotlinmvvm.ui.userlist

import com.haksoy.kotlinmvvm.data.entiries.User
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haksoy.kotlinmvvm.data.UserRepository
import com.haksoy.kotlinmvvm.data.remote.OperationCallback

class UserListViewModel : ViewModel() {


    private val userRepository: UserRepository = UserRepository()
    val errorMessage = MutableLiveData<String>()

    private val _users = MutableLiveData<List<User>>().apply { value = emptyList() }
    val mUsers: LiveData<List<User>> = _users
    fun loadUsers() {

        userRepository.fetchUsers(100, object : OperationCallback<List<User>> {
            override fun onSuccess(data: List<Any>?) {
                _users.value = data as List<User>?
            }

            override fun onError(error: String?) {
                errorMessage.postValue(error!!)
            }

        })
    }

}