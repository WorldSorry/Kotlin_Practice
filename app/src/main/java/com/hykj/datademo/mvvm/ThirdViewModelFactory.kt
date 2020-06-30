package com.hykj.datademo.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ThirdViewModelFactory(val countReserved: Int) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return ThirdViewModel(countReserved) as T

    }

}