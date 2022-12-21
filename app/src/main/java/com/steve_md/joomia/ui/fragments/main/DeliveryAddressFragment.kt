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
    private lateinit var googleMap: GoogleMap
    private lateinit var maps:SupportMapFragment
    private val LOCATION_REQUEST_CODE = 101

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDeliveryAddressBinding.inflate(inflater, container, false)
        val root = binding.root
        return root
    }

    // include a map here
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBinding()
        setUpMapPermissions()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
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
        maps = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        maps.getMapAsync(OnMapReadyCallback {
            googleMap = it

            val location = LatLng(-1.2324779, 36.8765912)
            googleMap.addMarker(MarkerOptions().position(location).title("Nairobi, Kenya"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))
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

    private fun setUpBinding() {
        binding.imageViewBackToCheck.setOnClickListener { findNavController().navigateUp() }
    }

}