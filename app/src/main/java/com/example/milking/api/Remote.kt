package com.example.milking.api

import com.example.milking.models.Cow
import retrofit2.Call
import retrofit2.http.*

interface Remote {


    @GET("get_cow/{id}")
    suspend fun getCow(@Path("id") cow_id:Int):Cow

    @GET("cow/all_cows")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun   getAllCows():Call<List<Cow>>

    @POST("add_Cow")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun addCow(@Body params :Cow):Call<Cow>
}