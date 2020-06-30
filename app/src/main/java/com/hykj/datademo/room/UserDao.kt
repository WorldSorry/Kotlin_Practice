package com.hykj.datademo.room

import androidx.room.*
import com.hykj.datademo.data.User

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(newUser: User)

    @Query("select * from User")
    fun loadAllUser(): List<User>

    @Query("select * from User where age>:age")
    fun loadUsersOlderThan(age: Int): List<User>

    @Delete
    fun deleteUser(user: User)

    @Query("delete from User where lastName=:lastName")
    fun deleteUserByLastName(lastName: String)


}