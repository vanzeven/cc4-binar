package com.example.malogin.ui.editnote

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.malogin.database.dao.NoteDao
import com.example.malogin.ui.createnote.CreateViewModel
import com.example.malogin.ui.home.NoteViewModel

class EditViewModelFactory(
    private val dataSource: NoteDao,
    private val application: Application
) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditViewModel::class.java)){
            return EditViewModel(dataSource, application) as T
        }
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)){
            return NoteViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}
