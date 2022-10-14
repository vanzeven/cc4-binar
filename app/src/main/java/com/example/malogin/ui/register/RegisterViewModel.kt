package com.example.malogin.ui.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.malogin.database.dao.AccountDao
import com.example.malogin.database.entity.AccountEntity
import kotlinx.coroutines.launch

class RegisterViewModel (
    val database : AccountDao, application: Application) : AndroidViewModel(application) {
    fun insertAccount(account: AccountEntity) {
        viewModelScope.launch {
            getData(account)
        }
    }

    private suspend fun getData(account: AccountEntity) {
        database.insertAccount(account)
    }

}