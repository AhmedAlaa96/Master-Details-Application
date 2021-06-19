package com.example.master_detailsapplication.data.repositrory

import com.example.master_detailsapplication.data.source.local.dao.AirlineDao
import com.example.master_detailsapplication.domain.models.Airline
import com.example.master_detailsapplication.domain.repository.AirlineLocalRepository

class AirlineLocalRepositoryImpl(private val airlineDao: AirlineDao): AirlineLocalRepository {
    override fun insert(airline: Airline): Long {
       return airlineDao.insert(airline)
    }

    override fun loadAll(): MutableList<Airline> {
       return airlineDao.loadAll()
    }

    override fun deleteAll(): Int {
        return airlineDao.deleteAll()
    }
}