/*
 * Copyright (c) 2022. Stephen Muindi
 */

package com.steve_md.joomia.ui.fragments.main

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.steve_md.joomia.databinding.FragmentDeliveryAddressBinding

class DeliveryAddressFragment : Fragment() , OnMapReadyCallback, LocationListener,
    GoogleApiClient.ConnectionCallbacks {

    private lateinit var binding: FragmentDeliveryAddressBinding
    private lateinit var mlocation: Location

    /**
     *  Google Map - is the main class of Google Maps Android SDK
     *  and entry class for all methods related to the Map
     * */
    private lateinit var googleMap: GoogleMap

    /**
     * SupportMapFragment -> The simplest way to place a map on an application
     *
     **/
    private lateinit var mapFragment: SupportMapFragment

    /**
     * A request code is any int value that identifies
     * the return result when the result arrives
     * -> This code identifies specific permission request
     **/
    private val LOCATION_REQUEST_CODE = 101


    //uniquely identify clients with coarse and fine location services
    // when requesting location updates and getting the latest location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDeliveryAddressBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageViewBackToCheck.setOnClickListener { findNavController().navigateUp() }
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        setUpPermissions()

    }

    /*
     * Initialises map and is called everytime map is to be used
    **/
    /*
    override fun onMapReady(p0: GoogleMap) {
        setUpPermissions()
        googleMap = p0

        }

     */

    override fun onLocationChanged(location: Location) {

        mlocation = location

        //val location = googleMap.isMyLocationEnabled
        val latLng = LatLng(mlocation.latitude,mlocation.longitude)
        googleMap.addMarker(MarkerOptions().position(latLng).title("Your Delivery Address Found"))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16.0f))
    }

    override fun onConnected(p0: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("Not yet implemented")
    }
}


    /**
     * Using fine location because it gives accurate results
     * */
    private fun setUpPermissions() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
           makeRequest()
        }

        else {
            setUpMaps()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(requireActivity(),
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_REQUEST_CODE)
    }