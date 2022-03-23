package com.example.milking.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.milking.models.Cow
import com.example.milking.repository.CowRepository
import kotlinx.coroutines.launch

class CowViewModel(private val repository: CowRepository) :ViewModel(){

    lateinit var cows: MutableLiveData<List<Cow>>


    init{
        cows =  MutableLiveData()
    }

    fun getlistObservable():MutableLiveData<List<Cow>>{
        return cows
    }



    suspend fun getCow(){

        val call  = repository.getCows()
        val data = call.execute()
        cows.postValue(data.body())

//        viewModelScope.launch{
//            val response = repository.getCows()
//            cre
//
//
//        }
    }
}