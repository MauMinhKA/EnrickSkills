package com.example.weathertrackapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.weathertrackapplication.R
import com.example.weathertrackapplication.databinding.ActivityMainBinding
import com.example.weathertrackapplication.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    lateinit var weatherAdapter: WeatherAdapter

    private val viewModel: WeatherViewModel by viewModel()
    lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.lifecycleOwner = this
        mBinding.rcvWeather.setHasFixedSize(true)

        weatherAdapter = WeatherAdapter(baseContext)
        mBinding.rcvWeather.adapter = weatherAdapter

        btnGetWeather.setOnClickListener() {
            viewModel.getTodayWeather(mBinding.edtCity.text.toString())
        }
        observerViewModel()
    }

    private fun observerViewModel() {
        viewModel.weatherLiveData.observe(this, { weather ->
            weather?.let {
                weatherAdapter.submitList(it.list)
            }
        })

        viewModel.errorLiveData.observe(this, { weather ->
            weather?.let {
                it?.let { Toast.makeText(this, it, Toast.LENGTH_LONG).show() }
            }
        })
    }
}