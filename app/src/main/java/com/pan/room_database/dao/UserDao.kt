package com.pan.room_database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pan.room_database.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUserItem(user: User)

    @Delete
    suspend fun deleteUserItem(user: User)

    @Query("Delete FROM user_table")
    suspend fun deleteAllUserItem()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>
}