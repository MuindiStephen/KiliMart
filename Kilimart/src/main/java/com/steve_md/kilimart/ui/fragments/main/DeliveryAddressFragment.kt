/*
 * Copyright (c) 2022. Stephen Muindi
 */

package com.steve_md.kilimart.ui.fragments.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.steve_md.kilimart.databinding.FragmentDeliveryAddressBinding
import com.steve_md.kilimart.util.toast


class DeliveryAddressFragment : Fragment() , OnMapReadyCallback {

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

        setUpBinding()

        setUpLocationClient()

    }

    private fun setUpLocationClient() {
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    private fun setUpBinding() {
        binding.imageViewBackToCheck.setOnClickListener { findNavController().navigateUp() }
    }

    /*
     * Initialises map and is called everytime map is to be used
    **/
    override fun onMapReady(p0: GoogleMap) {
        googleMap = p0
        setUpPermissions()
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
        } else {
            setUpMaps()
        }
    }

    private fun setUpMaps() {

        mapFragment = childFragmentManager.findFragmentById(com.steve_md.kilimart.R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this@DeliveryAddressFragment)

        val latLng = LatLng(mlocation.latitude, mlocation.longitude)
        googleMap.addMarker(MarkerOptions().position(latLng).title("Your Delivery Address Found")
            .icon(bitmapDescriptorFromVector(requireContext(), com.steve_md.kilimart.R.drawable.ic_delivered_point)))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f))
    }

    private fun bitmapDescriptorFromVector(
        context: Context,
        icDeliveredPoint: Int
    ): BitmapDescriptor? {
        val background =
            ContextCompat.getDrawable(context, com.steve_md.kilimart.R.drawable.ic_delivered_point)

        background!!.setBounds(0, 0, background.intrinsicWidth, background.intrinsicHeight)

        val vectorDrawable = ContextCompat.getDrawable(requireContext(), icDeliveredPoint)
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )
        val bitmap = Bitmap.createBitmap(
            background.intrinsicWidth,
            background.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        background.draw(canvas)
        vectorDrawable.draw(canvas)

        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults.size == 1 && grantResults[0]  == PackageManager.PERMISSION_GRANTED) {
                toast("Permission Granted")
                setUpPermissions()
            }
        } else {
                toast("Permission Denied")
        }
    }
}