package com.example.master_detailsapplication.data.repositrory

import com.example.master_detailsapplication.domain.models.Airline
import com.example.master_detailsapplication.domain.repository.AirlineRepository
import com.example.master_detailsapplication.infrastructure.networkService.RetrofitClient
import io.reactivex.Single

class AirlineRepositoryImpl: AirlineRepository {

    override fun getAirlines(): Single<List<Airline>> {
        return RetrofitClient.apiService().getAirlines()
    }

    override fun createAirline(airline: Airline): Single<Airline> {
        return RetrofitClient.apiService().createAirline(airline)
    }
}