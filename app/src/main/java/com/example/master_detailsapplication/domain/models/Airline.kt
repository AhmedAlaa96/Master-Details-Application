package com.example.master_detailsapplication.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Airline")
data class Airline(@PrimaryKey var id: String,
                   val name:String,
                   val country:String,
                   val logo: String,
                   val slogan: String,
                   val head_quaters: String,
                   val website: String,
                   val established:String) : Serializable{

    override fun toString(): String {
        return "Airline(id='$id', name='$name', country='$country', logo='$logo', slogan='$slogan', head_quaters='$head_quaters', website='$website', established='$established')"
    }
}