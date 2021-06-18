package com.example.master_detailsapplication.presentation.ui.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.master_detailsapplication.R
import com.example.master_detailsapplication.data.models.Airline
import com.example.master_detailsapplication.databinding.ActivityAirlineDetailsBinding
import com.squareup.picasso.Picasso


class AirlineDetails : AppCompatActivity() {

    private lateinit var airlineDetailsBinding: ActivityAirlineDetailsBinding
    private lateinit var airline: Airline
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        airlineDetailsBinding = ActivityAirlineDetailsBinding.inflate(layoutInflater)
        setContentView(airlineDetailsBinding.root)


        airline = intent.getSerializableExtra("airline") as Airline




        airlineDetailsBinding.backBtn.setOnClickListener { finish() }


        if(airline.logo.isEmpty()){
            Picasso
                .get()
                .load(R.drawable.placeholder)
                .into(airlineDetailsBinding.airlineImage)
        }else {
            Picasso
                .get()
                .load(airline.logo)
                .placeholder(R.drawable.placeholder)
                .into(airlineDetailsBinding.airlineImage)
        }

        airlineDetailsBinding.airlineTitle.text = airline.name
        airlineDetailsBinding.airlineCountry.text = airline.country
        airlineDetailsBinding.airlineSlogan.text = airline.slogan
        airlineDetailsBinding.airlineHeadQuarter.text = airline.head_quaters


        airlineDetailsBinding.visitBtn.setOnClickListener {
            if(airline.website.isNotEmpty()) {
                var url = airline.website
                if(!url.startsWith("www.")&& !url.startsWith("http://")){
                    url = "www.$url"
                }
                if(!url.startsWith("http://")){
                    url = "http://$url"
                }
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(browserIntent)
            }else{
                Toast.makeText(applicationContext,"Invalid URL", Toast.LENGTH_SHORT).show()
            }
        }
    }
}