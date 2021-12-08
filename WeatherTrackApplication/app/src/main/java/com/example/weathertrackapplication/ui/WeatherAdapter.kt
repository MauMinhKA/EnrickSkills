package com.example.weathertrackapplication.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertrackapplication.R
import com.example.weathertrackapplication.api.DetailList
import kotlinx.android.synthetic.main.item_weather.view.*
import java.text.SimpleDateFormat


class WeatherAdapter(private val context: Context, private val mListWeather: ArrayList<DetailList> = arrayListOf()) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bindWeather(detail: DetailList) {
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
            val dateString = simpleDateFormat.format("${detail.dt}000".toLong())
            itemView.txtDate.text = "Date: $dateString"
            itemView.txtAverage.text = "Average temperature: ${detail.deg}*C"
            itemView.txtHumidity.text = "Pressure: ${detail.pressure}"
            itemView.txtPressure.text = "Humidity: ${detail.humidity}%"
            itemView.txtDescription.text = "Description: ${detail.weather[0].description}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_weather, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bindWeather(mListWeather[position])
    }

    override fun getItemCount(): Int {
        return mListWeather.size
    }

    fun submitList(list: List<DetailList>?) {
        if (!list.isNullOrEmpty()) {
            mListWeather.clear()
            mListWeather.addAll(list)
            notifyDataSetChanged()
        }
    }
}