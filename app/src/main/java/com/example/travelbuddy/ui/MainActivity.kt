package com.example.travelbuddy.ui

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.travelbuddy.databinding.ActivityMainBinding
import com.example.travelbuddy.viewmodel.CarRentalViewModel
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import com.example.travelbuddy.BuildConfig
import java.util.Calendar
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CarRentalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                binding.pickupLocation.setText(state.pickupLocation)
                binding.dropoffLocation.setText(state.dropoffLocation)
                binding.pickupDateText.text = "Pickup Date: ${state.pickupDate}"
                binding.dropoffDateText.text = "Drop-off Date: ${state.dropoffDate}"
            }
        }

        val apiKey = BuildConfig.PLACES_API_KEY
        Places.initialize(applicationContext, apiKey)

        setupAutoComplete()

        binding.pickupDateBtn.setOnClickListener { showDatePicker(true) }
        binding.dropoffDateBtn.setOnClickListener { showDatePicker(false) }
        binding.searchKayakBtn.setOnClickListener { openKayak() }
    }

    private fun setupAutoComplete() {
        val fields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)

        val pickupAutoComplete = AutocompleteSupportFragment()
        pickupAutoComplete.setPlaceFields(fields)
        pickupAutoComplete.setHint("Enter Pickup Location")

        pickupAutoComplete.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                viewModel.updatePickupLocation(place.name ?: "")
            }

            override fun onError(status: Status) {
                Toast.makeText(this@MainActivity, "Error: ${status.statusMessage}", Toast.LENGTH_SHORT).show()
            }
        })

        val dropoffAutoComplete = AutocompleteSupportFragment()
        dropoffAutoComplete.setPlaceFields(fields)
        dropoffAutoComplete.setHint("Enter Drop-off Location")

        dropoffAutoComplete.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                viewModel.updateDropoffLocation(place.name ?: "")
            }

            override fun onError(status: Status) {
                Toast.makeText(this@MainActivity, "Error: ${status.statusMessage}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showDatePicker(isPickup: Boolean) {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .format(Calendar.getInstance().apply { set(year, month, dayOfMonth) }.time)

            if (isPickup) {
                viewModel.updatePickupDate(formattedDate)
            } else {
                viewModel.updateDropoffDate(formattedDate)
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

        datePicker.show()
    }

    private fun openKayak() {
        val pickup = binding.pickupLocation.text.toString().replace(" ", "-")
        val dropoff = binding.dropoffLocation.text.toString().replace(" ", "-")
        val url = "https://www.kayak.com/in?a=awesomecars&url=/cars/$pickup/$dropoff/${viewModel.state.value.pickupDate}/${viewModel.state.value.dropoffDate}"

        if (pickup.isNotEmpty() && viewModel.state.value.pickupDate.isNotEmpty() && viewModel.state.value.dropoffDate.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
        }
    }
}

