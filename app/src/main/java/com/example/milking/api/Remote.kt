package com.example.milking.api

import com.example.milking.models.Cow
import retrofit2.http.GET

interface Remote {


    @GET("get_cow/id")
    suspend fun getCow():Cow

    @GET("get_all_cows")
    suspend fun   getAllCows():List<Cow>
}