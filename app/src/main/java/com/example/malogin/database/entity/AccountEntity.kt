package com.example.malogin.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "account")
data class AccountEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var username: String? = "",
    @ColumnInfo
    var email: String? = "",
    @ColumnInfo
    var password: String?
) : Parcelable