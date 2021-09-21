package com.haksoy.kotlinmvvm.ui.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.haksoy.kotlinmvvm.data.UserRepository
import com.haksoy.kotlinmvvm.data.entiries.User
import com.haksoy.kotlinmvvm.data.remote.OperationCallback

class UserListViewModel : ViewModel() {


    private val userRepository: UserRepository = UserRepository()
    val errorMessage = MutableLiveData<String>()
    val filterNameForCLF = MutableLiveData<String>("")

    private val _users = MutableLiveData<List<User>>().apply { value = emptyList() }
    val mUsers: LiveData<List<User>>
        get() = Transformations.switchMap(filterNameForCLF) { searchKey ->

            val result = when (searchKey) {
                null,
                "" -> _users
                else -> {
                    Transformations.switchMap(_users) { users ->
                        val filteredResult = MutableLiveData<List<User>>()
                        val filteredList = users.filter {
                            it.name!!.lowercase().contains(searchKey.trim().toLowerCase())
                        }
                        filteredResult.value = filteredList
                        filteredResult

                    }
                }
            }
            result
        }

    fun loadUsers(count: Int) {

        userRepository.fetchUsers(this.activePage, count, object : OperationCallback<List<User>> {
            override fun onSuccess(data: List<Any>?) {
                if (activePage != 1) {
                    val temp = _users.value
                    _users.postValue(temp.let { list1 ->
                        (data as List<User>).let { list2 ->
                            list1!! + list2
                        }
                    }.toMutableList())
                } else
                    _users.value = data as List<User>?
            }

            override fun onError(error: String?) {
                errorMessage.postValue(error!!)
            }

        })
    }

    var activePage: Int = 1
    fun loadMore() {
        this.activePage++
        loadUsers(10)
    }

}