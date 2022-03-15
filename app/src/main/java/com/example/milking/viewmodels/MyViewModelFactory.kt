package com.example.milking.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.milking.repository.CowRepository

class MyViewModelFactory(private val repository: CowRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return MyViewModelFactory(repository) as T
    }

}