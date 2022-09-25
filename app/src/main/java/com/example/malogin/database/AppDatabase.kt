package com.example.malogin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.malogin.database.dao.AccountDao
import com.example.malogin.database.dao.NoteDao
import com.example.malogin.database.entity.AccountEntity
import com.example.malogin.database.entity.NoteEntity

@Database(entities = [AccountEntity::class, NoteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDatabaseDao() : AccountDao
    abstract fun noteDatabaseDao() : NoteDao

    companion object {
        private const val DB_NAME = "note.db"

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME).build()
                }
                return instance
            }
        }
    }
}