package com.example.travelbuddy.viewmodel

import androidx.lifecycle.ViewModel
import com.example.travelbuddy.viewmodel.data.CarRentalState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CarRentalViewModel : ViewModel() {

    private val _state = MutableStateFlow(CarRentalState())
    val state: StateFlow<CarRentalState> = _state

    fun updatePickupLocation(location: String) {
        _state.value = _state.value.copy(pickupLocation = location)
    }

    fun updateDropoffLocation(location: String) {
        _state.value = _state.value.copy(dropoffLocation = location)
    }

    fun updatePickupDate(date: String) {
        _state.value = _state.value.copy(pickupDate = date)
    }

    fun updateDropoffDate(date: String) {
        _state.value = _state.value.copy(dropoffDate = date)
    }
}
