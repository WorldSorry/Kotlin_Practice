package com.hykj.datademo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(var fastName: String, var lastName: String, var age: Int = 20) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}