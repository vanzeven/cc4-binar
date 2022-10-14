package com.example.malogin.ui.editnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.malogin.database.dao.NoteDao
import com.example.malogin.database.entity.NoteEntity
import kotlinx.coroutines.launch

class EditViewModel (
    val database : NoteDao, application: Application
) : AndroidViewModel(application){

    fun updateNote(note: NoteEntity) {
        viewModelScope.launch {
            getData(note)
        }
    }

    private suspend fun getData(note: NoteEntity) {
        database.updateNote(note)
    }
}