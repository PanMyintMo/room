package com.pan.room_database.repository

import androidx.lifecycle.LiveData
import com.pan.room_database.dao.UserDao
import com.pan.room_database.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()
    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun deleteUserItem(user: User) {
        userDao.deleteUserItem(user)
    }

    suspend fun deleteAllUserItem() {
        userDao.deleteAllUserItem()
    }

    suspend fun updateUserItem(user: User) {
        userDao.updateUserItem(user)
    }

}