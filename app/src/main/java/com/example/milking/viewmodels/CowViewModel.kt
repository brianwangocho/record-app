package com.example.milking.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.milking.models.Cow
import com.example.milking.repository.CowRepository
import kotlinx.coroutines.launch

class CowViewModel(private val repository: CowRepository) :ViewModel(){

    val myResponse:MutableLiveData<Cow>  = MutableLiveData()

    fun getCow(){

        viewModelScope.launch{
            val response = repository.getCow()
            myResponse.value = response
        }
    }
}