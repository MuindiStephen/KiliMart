/*
 * Copyright (c)  Stephen Muindi
 */

package com.steve_md.kilimart.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.steve_md.kilimart.R
import com.steve_md.kilimart.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        navController = navHostFragment.findNavController()

        binding.bottomNavigationView.apply {
            setupWithNavController(navController)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.homeFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.addToCartFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.paymentFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.accountProfileFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }

                else -> {
                    binding.bottomNavigationView.visibility = View.INVISIBLE
                }
            }
        }
    }
}

/* FakeJoomiaApp.retrofitApi.getProducts().enqueue(object : retrofit2.Callback<ArrayList<ProductsItem>>{
     override fun onResponse(
         call: Call<ArrayList<ProductsItem>>,
         response: Response<ArrayList<ProductsItem>>
     ) {
         progressBar.isVisible = false
         adapter.submitList(response.body())
         recyclerView.adapter = adapter
     }

     override fun onFailure(call: Call<ArrayList<ProductsItem>>, t: Throwable) {
         Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
     }


 }) */

// private fun <T> Call<T>.enqueue(callback: Callback<ProductsItem>) {
//
// }

// private fun <T> Iterable<T>.enqueue(callback: Callback<T>) {
//
// }
