package com.hykj.datademo.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hykj.datademo.data.User

class ThirdViewModel(countReserved: Int) : ViewModel() {

    private val userLiveData = MutableLiveData<User>()

    val userName: LiveData<String> = Transformations.map(userLiveData) {
        "${it.fastName} ${it.lastName}"
    }

    var counter = MutableLiveData<Int>()

    init {
        counter.value = countReserved
    }

    fun plusOne() {
        val count = counter.value ?: 0
        counter.value = count + 1
    }

    fun clear() {
        counter.value = 0
    }

    fun getUserName(userId: String) {
        userLiveData.value = User(userId, userId)
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("ThirdViewModel", ":onCleared")
    }

}