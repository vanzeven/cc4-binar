package com.example.malogin.ui.createnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.malogin.database.dao.NoteDao
import com.example.malogin.database.entity.NoteEntity
import kotlinx.coroutines.launch

class CreateViewModel (
    val database : NoteDao, application: Application) : AndroidViewModel(application){
    fun insertNote(note: NoteEntity) {
        viewModelScope.launch {
            getData(note)
        }
    }

    private suspend fun getData(note: NoteEntity) {
        database.insertNote(note)
    }
}