package com.example.weathertrackapplication.di

import com.example.weathertrackapplication.api.APIService
import com.example.weathertrackapplication.util.Constant
import com.example.weathertrackapplication.viewmodel.WeatherViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single { createService<APIService>(get()) }
    single { createOkHttpClient() }
    single { WeatherViewModel(get()) }
}


inline fun <reified T> createService(okHttpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(Constant.SERVER_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addNetworkInterceptor(Interceptor { chain ->
            var request = chain.request()
            val builder = request.newBuilder()
            request = builder.build()
            chain.proceed(request)
        })
        .addInterceptor(httpLoggingInterceptor)
        .build()
}