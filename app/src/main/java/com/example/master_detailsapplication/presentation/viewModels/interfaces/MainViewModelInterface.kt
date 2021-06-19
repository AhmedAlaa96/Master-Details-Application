package com.example.master_detailsapplication.presentation.viewModels.interfaces

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.master_detailsapplication.domain.models.Airline

interface MainViewModelInterface{

    val getAirlines: LiveData<List<*>>

    val getLocalAirlines: LiveData<List<*>>

    val createAirline: LiveData<List<*>>

    val createLocalAirline: LiveData<List<*>>

    val isLoading: MutableLiveData<Boolean>

    fun getLocalAirLinesList()

    fun getAirLinesList()

    fun createAirline(airline: Airline)

    fun createLocalAirline(airline: List<Airline>)



    fun deleteAllLocal()

    fun loading(isLoading: Boolean)

    fun dispose()
}