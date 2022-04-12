package com.example.milking.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.milking.models.Cow
import com.example.milking.repository.CowRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Callable

class CowViewModel(private val repository: CowRepository) :ViewModel(){

    lateinit var cows: MutableLiveData<List<Cow>>


    init{
        cows =  MutableLiveData()
    }

    fun getlistObservable():MutableLiveData<List<Cow>>{
        return cows
    }


     fun getCow(){

        val call  = repository.getCows()
//        val data = call.execute()
//        cows.postValue(data.body())
         call.enqueue(object : Callback<List<Cow>> {
             override fun onResponse(call: Call<List<Cow>>, response: Response<List<Cow>>) {
                 if(response.isSuccessful){

                     cows.postValue(response.body())
                 }

             }

             override fun onFailure(call: Call<List<Cow>>, t: Throwable) {
                 cows.postValue(null)
             }

         })


    }

    fun addCow(cow: Cow){
        val call  = repository.addCow(cow)



    }
}