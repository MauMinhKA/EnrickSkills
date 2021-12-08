package com.example.weathertrackapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathertrackapplication.api.APIService
import com.example.weathertrackapplication.api.Weather
import kotlinx.coroutines.launch

class WeatherViewModel(private val api: APIService) : ViewModel() {
    var weatherLiveData: MutableLiveData<Weather> = MutableLiveData()
    var errorLiveData = MutableLiveData<String>()

    fun getTodayWeather(city: String) {
        viewModelScope.launch {
            val result = api.getWeather(city)
            if (result.isSuccessful) {
                weatherLiveData.value = result.body()
            } else {
                errorLiveData.value = result.message()
            }
        }
    }
}