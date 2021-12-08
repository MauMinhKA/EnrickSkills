package com.example.weathertrackapplication.api

data class Weather(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<DetailList>,
    val message: Double
)

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val timezone: Int
)

data class DetailList(
    val clouds: Int,
    val deg: Int,
    val dt: Int,
    val feels_like: FeelsLike,
    val gust: Double,
    val humidity: Int,
    val pop: Double,
    val pressure: Int,
    val rain: Double,
    val speed: Double,
    val sunrise: Float,
    val sunset: Int,
    val temp: Temp,
    val weather: List<WeatherX>
)

data class FeelsLike(
    val day: Double,
    val eve: Double,
    val morn: Double,
    val night: Double
)

data class Coord(
    val lat: Double,
    val lon: Double
)

data class Temp(
    val day: Double,
    val eve: Double,
    val max: Double,
    val min: Double,
    val morn: Double,
    val night: Double
)

data class WeatherX(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)
