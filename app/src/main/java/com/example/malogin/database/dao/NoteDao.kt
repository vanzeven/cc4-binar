package com.example.malogin.database.dao

import androidx.room.*
import com.example.malogin.database.entity.NoteEntity

@Dao
interface NoteDao {
    @Query("select * from note")
    suspend fun getAllNote(): List<NoteEntity>

    @Insert
    suspend fun insertNote(note: NoteEntity)

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)
}