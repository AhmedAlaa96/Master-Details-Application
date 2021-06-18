package com.example.master_detailsapplication.data.models

import androidx.room.Entity
import java.io.Serializable

@Entity(tableName = "Airline")
data class Airline(val id:Int,
                   val name:String,
                   val country:String,
                   val logo: String,
                   val slogan: String,
                   val head_quaters: String,
                   val website: String,
                   val established:String
) : Serializable