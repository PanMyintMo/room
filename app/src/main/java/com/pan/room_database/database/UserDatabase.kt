package com.pan.room_database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.pan.room_database.dao.UserDao
import com.pan.room_database.model.User

@Database(entities = [User::class], version = 2, exportSchema = true)
abstract class UserDatabase : RoomDatabase() {


    abstract fun userDao(): UserDao

    companion object {

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL( "CREATE TABLE IF NOT EXISTS `user_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `firstName` TEXT NOT NULL, `lastName` TEXT NOT NULL, `age` INTEGER NOT NULL)")
            }
        }

        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).addMigrations(MIGRATION_1_2).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
