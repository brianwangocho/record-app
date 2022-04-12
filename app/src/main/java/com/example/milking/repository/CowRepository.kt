package com.example.milking.repository

import com.example.milking.api.RetrofitInstance
import com.example.milking.models.Cow
import retrofit2.Call

class CowRepository {
     fun getCows(): Call<List<Cow>> {
      return  RetrofitInstance.api.getAllCows()
    }
}