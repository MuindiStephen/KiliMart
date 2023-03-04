/*
 * Copyright (c) 2022. Stephen Muindi
 */

package com.steve_md.joomia.ui.fragments.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.steve_md.joomia.R
import com.steve_md.joomia.databinding.FragmentDeliveryAddressBinding

class DeliveryAddressFragment : Fragment() {

    private lateinit var binding: FragmentDeliveryAddressBinding

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

        setUpBinding()
        setUpLocationClient()
        setUpPermissions()

    }



    private fun setUpBinding() {
        binding.imageViewBackToCheck.setOnClickListener { findNavController().navigateUp() }
    }
    private fun setUpLocationClient() {
       fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
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

    private fun setUpMaps() {
        mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(OnMapReadyCallback { googleMap ->
            this.googleMap = googleMap
        })
    }


}
