package com.example.master_detailsapplication.domain

import com.example.master_detailsapplication.domain.base.IOSchedulers
import com.example.master_detailsapplication.domain.base.MainIOSchedulers
import com.example.master_detailsapplication.domain.base.SingleUseCase
import com.example.master_detailsapplication.domain.models.Airline
import com.example.master_detailsapplication.domain.repository.AirlineLocalRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import java.lang.Exception

class GetLocalAirlinesUseCase constructor(private val repository: AirlineLocalRepository,scheduler: IOSchedulers = MainIOSchedulers()):SingleUseCase<List<Airline>>(scheduler){
    override fun run(): Single<List<Airline>> {
        return Single.create {
            try {
                it.onSuccess(repository.loadAll().toList())
            }catch (e:Exception){
                it.onError(Throwable(e.message))
            }
        }
    }
}