package com.example.master_detailsapplication.domain


import com.example.master_detailsapplication.domain.base.IOSchedulers
import com.example.master_detailsapplication.domain.base.MainIOSchedulers
import com.example.master_detailsapplication.domain.models.Airline
import com.example.master_detailsapplication.domain.base.SingleUseCaseWithParams
import com.example.master_detailsapplication.domain.repository.AirlineLocalRepository
import io.reactivex.Single
import java.lang.Exception

class CreateLocalAirlineUseCase constructor(private val repository: AirlineLocalRepository,scheduler: IOSchedulers = MainIOSchedulers()): SingleUseCaseWithParams<List<Airline>,Long>(scheduler) {
    override fun run(input:List<Airline>): Single<Long>{
        return Single.create {
            try {
                var long : Long = 0
                for (item in input){
                   long =  repository.insert(item)
                }
                it.onSuccess(long)
            }catch (e: Exception){
                it.onError(Throwable(e.message))
            }
        }
    }


}