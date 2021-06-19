package com.example.master_detailsapplication.presentation.ui.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.master_detailsapplication.R
import com.example.master_detailsapplication.domain.models.Airline
import com.example.master_detailsapplication.databinding.ActivityAirlineDetailsBinding
import java.lang.Exception


class AirlineDetails : AppCompatActivity() {

    private lateinit var airlineDetailsBinding: ActivityAirlineDetailsBinding
    private lateinit var airline: Airline
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        airlineDetailsBinding = ActivityAirlineDetailsBinding.inflate(layoutInflater)
        setContentView(airlineDetailsBinding.root)


        airline = intent.getSerializableExtra("airline") as Airline




        airlineDetailsBinding.backBtn.setOnClickListener { finish() }


        airlineDetailsBinding.airlineTitle.text = if(airline.name.isNotEmpty()) airline.name
        else getString(R.string.not_provided_text)

        airlineDetailsBinding.airlineCountry.text = if(airline.country.isNotEmpty()) airline.country
        else getString(R.string.not_provided_text)

        airlineDetailsBinding.airlineSlogan.text = if(airline.slogan.isNotEmpty())  airline.slogan
        else getString(R.string.not_provided_text)

        airlineDetailsBinding.airlineHeadQuarter.text = if(airline.head_quaters.isNotEmpty()) airline.head_quaters
        else getString(R.string.not_provided_text)


        airlineDetailsBinding.visitBtn.setOnClickListener {
            try {
                if (airline.website.isNotEmpty()) {
                    var url = airline.website
                    if (!url.startsWith("www.") && !url.startsWith("http://")) {
                        url = "www.$url"
                    }
                    if (!url.startsWith("http://")) {
                        url = "http://$url"
                    }
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(browserIntent)
                } else {
                    Toast.makeText(applicationContext, "Invalid URL", Toast.LENGTH_SHORT).show()
                }
            }catch (e:Exception){
                Toast.makeText(applicationContext, "Invalid URL", Toast.LENGTH_SHORT).show()
            }
        }
    }
}