package com.ics342.myapplication.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ics342.myapplication.ApiService
import com.ics342.myapplication.Data.ForecastData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(private val apiService: ApiService): ViewModel(){
    private val _forecastData: MutableLiveData<ForecastData> = MutableLiveData()
    val forecastData: LiveData<ForecastData>
        get() = _forecastData
    fun viewAppeared() = viewModelScope.launch {
        _forecastData.value = apiService.getForecastData("55112")
    }
    fun updateForecastData(zip: String) = viewModelScope.launch {
        _forecastData.value = apiService.getForecastData(zip)
    }

}
