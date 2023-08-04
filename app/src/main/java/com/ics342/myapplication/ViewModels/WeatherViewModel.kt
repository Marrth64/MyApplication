package com.ics342.myapplication.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ics342.myapplication.ApiService
import com.ics342.myapplication.Data.WeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val apiService: ApiService): ViewModel() {
    private val _weatherData: MutableLiveData<WeatherData> = MutableLiveData()
    val weatherData: LiveData<WeatherData>
        get() = _weatherData
    fun viewAppeared() = viewModelScope.launch {
        _weatherData.value = apiService.getWeatherData("55112")
    }
    fun updateWeatherData(zip: String) = viewModelScope.launch {
        _weatherData.value = apiService.getWeatherData(zip)
    }

}
