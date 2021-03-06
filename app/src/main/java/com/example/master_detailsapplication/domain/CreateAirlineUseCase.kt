package com.example.master_detailsapplication.domain


import com.example.master_detailsapplication.domain.base.IOSchedulers
import com.example.master_detailsapplication.domain.base.MainIOSchedulers
import com.example.master_detailsapplication.domain.models.Airline
import com.example.master_detailsapplication.domain.base.SingleUseCaseWithParams
import com.example.master_detailsapplication.domain.repository.AirlineRepository
import io.reactivex.Single

class CreateAirlineUseCase constructor(private val repository: AirlineRepository,scheduler: IOSchedulers = MainIOSchedulers()): SingleUseCaseWithParams<Airline,Airline>(scheduler) {
    override fun run(input:Airline): Single<Airline> = repository.createAirline(input)


}