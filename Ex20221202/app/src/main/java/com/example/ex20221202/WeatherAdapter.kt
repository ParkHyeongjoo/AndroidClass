package com.example.ex20221202

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeatherAdapter(val context: Context, val weatherList: ArrayList<WeatherVO>) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvCity: TextView
        val tvState: TextView
        val tvTemp: TextView
        val tvHumidity: TextView
        val tvSpeed: TextView

        init {
            tvCity = itemView.findViewById(R.id.tvCity)
            tvState = itemView.findViewById(R.id.tvState)
            tvTemp = itemView.findViewById(R.id.tvTemp)
            tvHumidity = itemView.findViewById(R.id.tvHumidity)
            tvSpeed = itemView.findViewById(R.id.tvSpeed)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.weather_list, null)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCity.text = "도시 : " + weatherList[position].city
        holder.tvState.text = weatherList[position].state
        holder.tvTemp.text = "온도 : " + weatherList[position].temp
        holder.tvHumidity.text = "습도 : " + weatherList[position].humidity
        holder.tvSpeed.text = "풍속 : " + weatherList[position].speed
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }
}