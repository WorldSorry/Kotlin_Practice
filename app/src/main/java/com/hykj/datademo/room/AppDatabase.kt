package com.hykj.datademo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hykj.datademo.data.Book
import com.hykj.datademo.data.User

@Database(version = 3, entities = [User::class, Book::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun bookDao(): BookDao


    companion object {
        private var instance: AppDatabase? = null

        //数据库 1升级至2
        val MIGRATTION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("create table Book (id integer primary key autoincrement not null,name text not null,pages integer not null)")
            }

        }
        val MIGRATTION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table Book add column author text not null default 'unknown'")
            }

        }

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            )
                .allowMainThreadQueries()       //允许在主线程操作
                .addMigrations(MIGRATTION_1_2, MIGRATTION_2_3)
                .build().apply { instance = this }
        }
    }
}