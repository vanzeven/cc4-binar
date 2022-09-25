package com.example.malogin.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.malogin.database.entity.AccountEntity

@Dao
interface AccountDao {
    @Query("select * from account where email == :email")
    suspend fun getAccountyByEmail(email: String): AccountEntity

    @Insert
    suspend fun insertAccount(account: AccountEntity)
}