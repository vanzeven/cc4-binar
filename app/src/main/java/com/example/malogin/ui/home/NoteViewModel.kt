package com.example.malogin.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.malogin.database.dao.NoteDao
import com.example.malogin.database.entity.NoteEntity
import kotlinx.coroutines.launch

class NoteViewModel (
    val database : NoteDao, application: Application) : AndroidViewModel(application){
    fun getNote() : MutableLiveData<List<NoteEntity>> {
        val dummy = MutableLiveData <List<NoteEntity>>()
        viewModelScope.launch {
            dummy.value = getData()
        }
        return dummy
    }

    fun deleteNote(note: NoteEntity){
        viewModelScope.launch {
            database.deleteNote(note)
        }
    }

    private suspend fun getData() : List<NoteEntity> {
        return database.getAllNote()
    }
}