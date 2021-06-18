package com.example.master_detailsapplication.presentation.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.master_detailsapplication.data.adapters.AirlinesAdapter
import com.example.master_detailsapplication.data.models.Airline
import com.example.master_detailsapplication.databinding.ActivityMainBinding
import com.example.master_detailsapplication.databinding.ContentMainBinding
import com.example.master_detailsapplication.presentation.ui.fragments.AddAirlineBottomSheet

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var contentMainActivityBinding: ContentMainBinding
    private lateinit var airlinesAdapter: AirlinesAdapter
    private lateinit var airlinesList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

        contentMainActivityBinding = mainActivityBinding.contentMain


        airlinesAdapter = AirlinesAdapter()

        airlinesList = contentMainActivityBinding.airlinesList

        airlinesAdapter.setItems(listOf(
            Airline(1,
            "Quatar Airways",
            "Quatar",
            "https://upload.wikimedia.org/wikipedia/en/thumb/9/9b/Qatar_Airways_Logo.svg/300px-Qatar_Airways_Logo.svg.png",
            "Going Places Together",
            "Qatar Airways Towers, Doha, Qatar",
            "www.qatarairways.com",
                "1994"),
         Airline(1,
            "Quatar Airways",
            "Quatar",
            "https://upload.wikimedia.org/wikipedia/en/thumb/9/9b/Qatar_Airways_Logo.svg/300px-Qatar_Airways_Logo.svg.png",
            "Going Places Together",
            "Qatar Airways Towers, Doha, Qatar",
            "www.qatarairways.com",
                "1994"),
         Airline(1,
            "Quatar Airways",
            "Quatar",
            "https://upload.wikimedia.org/wikipedia/en/thumb/9/9b/Qatar_Airways_Logo.svg/300px-Qatar_Airways_Logo.svg.png",
            "Going Places Together",
            "Qatar Airways Towers, Doha, Qatar",
            "www.qatarairways.com",
                "1994"),
         Airline(1,
            "Quatar Airways",
            "Quatar",
            "https://upload.wikimedia.org/wikipedia/en/thumb/9/9b/Qatar_Airways_Logo.svg/300px-Qatar_Airways_Logo.svg.png",
            "Going Places Together",
            "Qatar Airways Towers, Doha, Qatar",
            "www.qatarairways.com",
                "1994"),
         Airline(1,
            "Quatar Airways",
            "Quatar",
            "https://upload.wikimedia.org/wikipedia/en/thumb/9/9b/Qatar_Airways_Logo.svg/300px-Qatar_Airways_Logo.svg.png",
            "Going Places Together",
            "Qatar Airways Towers, Doha, Qatar",
            "www.qatarairways.com",
                "1994"),
         Airline(1,
            "Quatar Airways",
            "Quatar",
            "https://upload.wikimedia.org/wikipedia/en/thumb/9/9b/Qatar_Airways_Logo.svg/300px-Qatar_Airways_Logo.svg.png",
            "Going Places Together",
            "Qatar Airways Towers, Doha, Qatar",
            "www.qatarairways.com",
                "1994"),
         Airline(1,
            "Quatar Airways",
            "Quatar",
            "https://upload.wikimedia.org/wikipedia/en/thumb/9/9b/Qatar_Airways_Logo.svg/300px-Qatar_Airways_Logo.svg.png",
            "Going Places Together",
            "Qatar Airways Towers, Doha, Qatar",
            "www.qatarairways.com",
                "1994"),
         Airline(1,
            "Quatar Airways",
            "Quatar",
            "https://upload.wikimedia.org/wikipedia/en/thumb/9/9b/Qatar_Airways_Logo.svg/300px-Qatar_Airways_Logo.svg.png",
            "Going Places Together",
            "Qatar Airways Towers, Doha, Qatar",
            "www.qatarairways.com",
                "1994"),

            ))

        airlinesList.adapter = airlinesAdapter

        airlinesList.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )



        mainActivityBinding.fab.setOnClickListener {

            AddAirlineBottomSheet().show(supportFragmentManager,AddAirlineBottomSheet.TAG)

        }
    }


}