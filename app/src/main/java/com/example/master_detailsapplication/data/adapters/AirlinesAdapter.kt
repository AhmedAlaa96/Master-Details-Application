package com.example.master_detailsapplication.data.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.master_detailsapplication.presentation.ui.activities.MainActivity
import com.example.master_detailsapplication.data.models.Airline
import com.example.master_detailsapplication.databinding.AirlineItemBinding
import com.example.master_detailsapplication.presentation.ui.activities.AirlineDetails

class AirlinesAdapter(private var items: List<Airline> = listOf()) :RecyclerView.Adapter<AirlinesAdapter.ViewHolder>() {


    @JvmName("set Airlines Items")
    fun setItems(items:List<Airline>){
        this.items = items
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView =
            AirlineItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size


    class ViewHolder(private val view: AirlineItemBinding) : RecyclerView.ViewHolder(view.root),
        View.OnClickListener {
        private lateinit var airline: Airline

        init {
            view.root.setOnClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        fun bindView(airline: Airline) {
            this.airline = airline

            view.airplaneTitle.text = airline.name
        }

        override fun onClick(v: View?) {
            val intent = Intent(view.root.context, AirlineDetails::class.java)
            intent.putExtra("airline", this.airline)

            view.root.context.startActivity(intent)
        }
    }
}