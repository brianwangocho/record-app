package com.example.milking.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.milking.models.Cow
import com.example.milking.models.CowResponse
import com.example.milking.models.MilkingData
import com.example.milking.repository.CowRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Callable

class CowViewModel(private val repository: CowRepository) :ViewModel(){

    lateinit var cows: MutableLiveData<List<Cow>>
    lateinit var cow_response:MutableLiveData<CowResponse>



    init{
        cows =  MutableLiveData()
        cow_response = MutableLiveData()



    }

    fun getlistObservable():MutableLiveData<List<Cow>>{
        return cows
    }

    fun getAddCowResponse():MutableLiveData<CowResponse>{
        return  cow_response
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

             }

         })


    }

    fun addCow(cow: Cow){
        val call  = repository.addCow(cow)

        call.enqueue(object: Callback<CowResponse>{
            override fun onResponse(call: Call<CowResponse>, response: Response<CowResponse>) {

                if(response.isSuccessful){
                    cow_response.postValue(response.body())

                }

            }

            override fun onFailure(call: Call<CowResponse>, t: Throwable) {




            }

        })




    }

    fun addMilkingData(milkingSession: MilkingData){
        val call  = repository.addMilkSessionData(milkingSession)

        call.enqueue(object: Callback<CowResponse>{
            override fun onResponse(call: Call<CowResponse>, response: Response<CowResponse>) {

                if(response.isSuccessful){
                    cow_response.postValue(response.body())

                }

            }

            override fun onFailure(call: Call<CowResponse>, t: Throwable) {




            }

        })




    }
}