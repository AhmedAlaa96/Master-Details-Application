package com.example.master_detailsapplication.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.master_detailsapplication.domain.models.Airline
import com.example.master_detailsapplication.domain.ViewModelUseCases
import com.example.master_detailsapplication.presentation.viewModels.interfaces.MainViewModelInterface

class MainViewModel constructor(private val viewModelUseCases: ViewModelUseCases): MainViewModelInterface, ViewModel() {

    private val _getAirlines: MutableLiveData<List<*>> by lazy { MutableLiveData<List<*>>() }

    private val _getLocalAirlines: MutableLiveData<List<*>> by lazy { MutableLiveData<List<*>>() }

    private val _createAirline: MutableLiveData<List<*>> by lazy { MutableLiveData<List<*>>() }

    private val _createLocalAirline: MutableLiveData<List<*>> by lazy { MutableLiveData<List<*>>() }


    override val getAirlines : LiveData<List<*>> = _getAirlines
    override val getLocalAirlines: LiveData<List<*>> = _getLocalAirlines
    override val createAirline: LiveData<List<*>> = _createAirline
    override val createLocalAirline: LiveData<List<*>> = _createLocalAirline
    override val isLoading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }


    override fun getLocalAirLinesList() {
        viewModelUseCases.getLocalAirlinesUseCase.execute(
            {
                _getLocalAirlines.value = listOf(true,it)
            },
            {
                _getLocalAirlines.value = listOf(false,it.message)
            }
        )
    }

    override fun getAirLinesList() {
        viewModelUseCases.getAirlinesUseCase.execute(
            {

                _getAirlines.value = listOf(true,it)
            },
            {
                _getAirlines.value = listOf(false,it.message)
            }
        )
    }

    init {
        isLoading.value = true
        getAirLinesList()
    }

    override fun createAirline(airline: Airline) {
        viewModelUseCases.createAirlineUseCase.execute(
            airline,
            {
                _createAirline.value = listOf(true,it)
            },
            {
                _createAirline.value = listOf(false,it.message)
            }
        )
    }

    override fun createLocalAirline(airlines: List<Airline>) {
        viewModelUseCases.createLocalAirlineUseCase.execute(
            airlines,
            {
                _createLocalAirline.value = listOf(true,it)
            },
            {
                _createLocalAirline.value = listOf(false,it.message)
            })
    }

    override fun deleteAllLocal() {
        viewModelUseCases.deleteLocalAirlinesUseCase.execute(
            {}, {})
    }

    override fun loading(isLoading: Boolean) {
       this.isLoading.value = isLoading
    }

    override fun dispose() {
        viewModelUseCases.getAirlinesUseCase.dispose()
        viewModelUseCases.createAirlineUseCase.dispose()
        viewModelUseCases.getLocalAirlinesUseCase.dispose()
        viewModelUseCases.createLocalAirlineUseCase.dispose()
        viewModelUseCases.deleteLocalAirlinesUseCase.dispose()
    }
}