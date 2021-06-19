package com.example.master_detailsapplication.domain

import com.example.master_detailsapplication.domain.base.SingleUseCase
import com.example.master_detailsapplication.domain.models.Airline
import com.example.master_detailsapplication.domain.repository.AirlineLocalRepository
import io.reactivex.Single
import java.lang.Exception

class GetLocalAirlinesUseCase constructor(private val repository: AirlineLocalRepository):SingleUseCase<List<Airline>>(){
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