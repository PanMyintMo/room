package com.pan.room_database.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pan.room_database.database.UserDatabase
import com.pan.room_database.model.User
import com.pan.room_database.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUserItems(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUserItem(user)
        }
    }

    fun deleteUserItem(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUserItem(user)
        }
    }

    fun deleteAllUserItem() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUserItem()
        }
    }
}