package com.example.milking.repository

import com.example.milking.api.RetrofitInstance
import com.example.milking.models.Cow
import com.example.milking.models.CowResponse

import retrofit2.Call
import retrofit2.Response

import java.util.*

class CowRepository {
     fun getCows(): Call<List<Cow>> {
      return  RetrofitInstance.api.getAllCows()
    }

   fun addCow(cow: Cow): Call<CowResponse> {
       return RetrofitInstance.api.addCow(cow)
   }
}