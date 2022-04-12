package com.example.milking.repository

import com.example.milking.api.RetrofitInstance
import com.example.milking.models.Cow
import retrofit2.Call
import retrofit2.Response

class CowRepository {
     fun getCows(): Call<List<Cow>> {
      return  RetrofitInstance.api.getAllCows()
    }

   fun addCow(cow: Cow):Call<Cow>{
       return RetrofitInstance.api.addCow(cow)
   }
}