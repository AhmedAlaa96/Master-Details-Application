package com.example.master_detailsapplication.domain.repository

import com.example.master_detailsapplication.domain.models.Airline
import io.reactivex.Single


interface AirlineRepository {
    fun getAirlines(): Single<List<Airline>>

    fun createAirline(airline: Airline): Single<Airline>

}