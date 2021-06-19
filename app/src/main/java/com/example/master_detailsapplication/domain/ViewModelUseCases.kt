package com.example.master_detailsapplication.domain

data class ViewModelUseCases(
    val getAirlinesUseCase: GetAirlinesUseCase,
    val createAirlineUseCase: CreateAirlineUseCase,
    val getLocalAirlinesUseCase: GetLocalAirlinesUseCase,
    val createLocalAirlineUseCase: CreateLocalAirlineUseCase,
    val deleteLocalAirlinesUseCase: DeleteLocalAirlinesUseCase
)