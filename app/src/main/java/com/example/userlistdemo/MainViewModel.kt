package com.example.userlistdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userlistdemo.model.User
import com.example.userlistdemo.repository.UserRepository
import com.example.userlistdemo.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val _userList = MutableLiveData<List<User>>(emptyList())
    val userList: LiveData<List<User>>
        get() = _userList

    private val _progressBarVisibility = MutableLiveData(true)
    val progressBarVisibility: LiveData<Boolean>
        get() = _progressBarVisibility

    private val _showErrorToast = MutableLiveData<Event<Unit>>()
    val showErrorToast: LiveData<Event<Unit>>
        get() = _showErrorToast

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        _progressBarVisibility.postValue(true)
        viewModelScope.launch(IO) {
            val result = repository.getUsers()
            handleResult(result)
        }
    }

    private fun handleResult(result: Result<List<User>>) {
        _progressBarVisibility.postValue(false)
        if (result.isSuccess) {
            _userList.postValue(result.getOrNull())
        } else {
            _showErrorToast.postValue(Event(Unit))
        }
    }
}