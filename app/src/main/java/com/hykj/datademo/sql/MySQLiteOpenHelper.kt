package com.hykj.datademo.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.P)
class MySQLiteOpenHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    private val createBook = "create table Book(" +
            "id integer primary key autoincrement, " +
            "author text," +
            " price real," +
            "pages integer," +
            "name text)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createBook)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}