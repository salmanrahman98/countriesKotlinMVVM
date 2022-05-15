package com.rahman.countrymvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rahman.countrymvvm.Model.Country

class ListViewModel : ViewModel() {

    val countriesMutable = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loadingProgressBar = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        val mockData = listOf(
            Country("Country 1"),
            Country("Country 2"),
            Country("Country 3"),
            Country("Country 4"),
            Country("Country 5"),
            Country("Country 6"),
            Country("Country 7"),
            Country("Country 8"),
        )

        countryLoadError.value = false
        loadingProgressBar.value = false
        countriesMutable.value = mockData
    }
}