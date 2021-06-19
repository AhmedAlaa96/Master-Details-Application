package com.example.master_detailsapplication.domain.repository

import com.example.master_detailsapplication.data.source.local.dao.AirlineDao
import com.example.master_detailsapplication.domain.models.Airline

interface AirlineLocalRepository {
    fun insert(airline: Airline): Long

    fun loadAll(): MutableList<Airline>

    fun deleteAll():Int
}