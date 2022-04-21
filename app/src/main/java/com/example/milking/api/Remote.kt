package com.example.milking.api

import com.example.milking.models.Cow
import com.example.milking.models.CowResponse
import com.example.milking.models.MilkingData

import retrofit2.Call
import retrofit2.Response

import retrofit2.http.*
import java.util.*

interface Remote {


    @GET("get_cow/{id}")
    suspend fun getCow(@Path("id") cow_id:Int):Cow

    @GET("cow/all_cows")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun   getAllCows():Call<List<Cow>>

    @POST("cow/add_cow")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun addCow(@Body params :Cow): Call<CowResponse>

    @POST("milking_data/add_milking_data")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun addMilkData(@Body params :MilkingData): Call<CowResponse>
}