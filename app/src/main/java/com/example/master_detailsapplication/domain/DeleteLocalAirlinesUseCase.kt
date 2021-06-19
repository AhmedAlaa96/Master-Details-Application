package com.example.master_detailsapplication.domain

import com.example.master_detailsapplication.domain.base.SingleUseCase
import com.example.master_detailsapplication.domain.repository.AirlineLocalRepository
import io.reactivex.Single
import java.lang.Exception

class DeleteLocalAirlinesUseCase constructor(private val repository: AirlineLocalRepository):SingleUseCase<Int>(){
    override fun run(): Single<Int> {
        return Single.create {
            try {
                it.onSuccess(repository.deleteAll())
            }catch (e:Exception){
                it.onError(Throwable(e.message))
            }
        }
    }
}