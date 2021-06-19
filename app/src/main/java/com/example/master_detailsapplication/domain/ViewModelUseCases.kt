package com.example.master_detailsapplication.domain

data class ViewModelUseCases(
    override val getAirlinesUseCase: GetAirlinesUseCase,
    override val createAirlineUseCase: CreateAirlineUseCase,
    override val getLocalAirlinesUseCase: GetLocalAirlinesUseCase,
    override val createLocalAirlineUseCase: CreateLocalAirlineUseCase,
    override val deleteLocalAirlinesUseCase: DeleteLocalAirlinesUseCase
) : ViewModelUseCasesInterface