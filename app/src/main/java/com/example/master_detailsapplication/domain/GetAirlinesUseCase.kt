package com.example.master_detailsapplication.domain


import com.example.master_detailsapplication.domain.models.Airline
import com.example.master_detailsapplication.domain.base.SingleUseCase
import com.example.master_detailsapplication.domain.repository.AirlineRepository
import io.reactivex.Single

class GetAirlinesUseCase constructor(private val repository: AirlineRepository): SingleUseCase<List<Airline>>() {
    override fun run(): Single<List<Airline>> = repository.getAirlines()

}