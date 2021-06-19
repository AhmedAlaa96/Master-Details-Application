package com.example.master_detailsapplication.domain

interface ViewModelUseCasesInterface{
    val getAirlinesUseCase: GetAirlinesUseCase
    val createAirlineUseCase: CreateAirlineUseCase
    val getLocalAirlinesUseCase: GetLocalAirlinesUseCase
    val createLocalAirlineUseCase: CreateLocalAirlineUseCase
    val deleteLocalAirlinesUseCase: DeleteLocalAirlinesUseCase
}