package com.example.master_detailsapplication.presentation.viewModels

import com.example.master_detailsapplication.domain.models.Airline

object DataBaseTest {

    private val airlines =  mutableListOf(
        Airline("1","Test1","","","","","",""),
        Airline("2","Test2","","","","","",""),
        Airline("3","Test3","","","","","",""),
        Airline("4","Test4","","","","","",""),
        Airline("5","Test5","","","","","",""),
    )

    fun getAirlineTest(): List<Airline> = airlines.toList()

    fun createAirline(){
       airlines.add( Airline("6","Test6","","","","","",""),)
    }
    fun deleteAirline(){
       airlines.clear()
    }

}