package com.mobiquitytest.demo.ui.fragment

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.mobiquitytest.demo.R
import com.mobiquitytest.demo.data.room.entity.CityEntity
import com.mobiquitytest.demo.viewmodels.MapViewModel
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_map.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*

/**
 * Created by Nilesh Pambhar
 */
class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mapViewModel: MapViewModel
    private lateinit var map: GoogleMap
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mapViewModel = ViewModelProvider(this).get(MapViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_map, container, false)
        val supportMapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        supportMapFragment.getMapAsync(this)
        root.fabDone.visibility = View.INVISIBLE
        return root
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap!!
        map.setOnMapClickListener {
            map.clear()
            map.addMarker(MarkerOptions().position(it))
            fabDone.visibility = View.VISIBLE
            GlobalScope.launch(Dispatchers.IO) {
                getAddress(activity, it.latitude, it.longitude)

            }
        }
    }

    private fun getAddress(context: Context?, LATITUDE: Double, LONGITUDE: Double) { //Set Address
        try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses: List<Address>? = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1)

            if (addresses != null && addresses.isNotEmpty()) {
                val address: String =
                    addresses[0].getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                var city: String? = "Unknown"
                if (!addresses[0].locality.isNullOrEmpty()) {
                    city = addresses[0].locality
                }

                Log.d("tag", "City : $city")
                GlobalScope.launch(Dispatchers.Main) {
                    tvAddress.text = "Address : $address, $city"
                    fabDone.setOnClickListener {
                        GlobalScope.launch {
                            val cityData =
                                CityEntity(0, city!!, LATITUDE.toString(), LONGITUDE.toString())
                            mapViewModel.insertCity(cityData)
                        }
                        activity?.onBackPressed()
                    }
                }
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
