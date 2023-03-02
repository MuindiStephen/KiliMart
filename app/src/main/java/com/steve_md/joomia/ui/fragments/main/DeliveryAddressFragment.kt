/*
 * Copyright (c) 2022. Stephen Muindi
 */

package com.steve_md.joomia.ui.fragments.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.steve_md.joomia.R
import com.steve_md.joomia.databinding.FragmentDeliveryAddressBinding
import com.steve_md.joomia.util.toast

class DeliveryAddressFragment : Fragment() {

    private lateinit var binding:FragmentDeliveryAddressBinding

    /**
     *  Google Map - is the main class of Google Maps Android SDK
     *  and entry class for all methods related to the Map
     * */
    private lateinit var googleMap: GoogleMap

    /*
    SupportMapFragment -> The simplest way to place a map on an application
     */
    private lateinit var maps:SupportMapFragment

    /**
     * A request code is any int value that identifies
     * the return result when the result arrives
     * -> This code identifies specific permission request
     **/
    private val LOCATION_REQUEST_CODE = 101


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

        //val mapFragment =
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBinding()
        setUpMapPermissions()
    }

    private fun setUpBinding() {
        binding.imageViewBackToCheck.setOnClickListener { findNavController().navigateUp() }
    }
















    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            LOCATION_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] !=  PackageManager.PERMISSION_GRANTED) {
                    toast("Maps permission denied")
                } else {
                    setUpMaps()
                }
            }
        }
    }

    private fun setUpMaps() {
       // maps = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        maps.getMapAsync(OnMapReadyCallback { googleMap ->
            this.googleMap = googleMap

            val location = LatLng(-1.2324779, 36.8765912)
            // creating a marker at the exact

            googleMap.addMarker(MarkerOptions().position(location).title("You are currently here"))
            // Camera is updated
           // googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16.0f))
        })

    }

    private fun setUpMapPermissions() {
        val permission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }else{
            //setUpMaps()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(requireActivity(),
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_REQUEST_CODE)
    }



    companion object {
        private const val TAG = "DeliveryAddressFragment"
    }

}