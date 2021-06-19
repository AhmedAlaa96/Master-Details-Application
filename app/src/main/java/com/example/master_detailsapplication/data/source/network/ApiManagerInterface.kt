package com.example.master_detailsapplication.data.source.network

import com.example.master_detailsapplication.domain.models.Airline
import io.reactivex.Single
import retrofit2.http.*


interface ApiManagerInterface {


    @GET("airlines")
    fun getAirlines() :  Single<List<Airline>>

    @POST("airlines")
    fun createAirline(@Body airline: Airline) :  Single<Airline>


}