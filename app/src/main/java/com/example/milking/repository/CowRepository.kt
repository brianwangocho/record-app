package com.example.milking.repository

import com.example.milking.api.RetrofitInstance
import com.example.milking.models.Cow

class CowRepository {
    suspend fun getCow(): Cow {
      return  RetrofitInstance.api.getCow()
    }
}