package com.hykj.datademo.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hykj.datademo.data.Book

@Dao
interface BookDao {

    @Insert
    fun insertBook(book: Book): Long

    @Query("select * from Book")
    fun loadAllBooks(): List<Book>

}