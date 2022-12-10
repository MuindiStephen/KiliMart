package com.steve_md.joomia.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.steve_md.joomia.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)



        supportActionBar?.hide()


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView2) as NavHostFragment

        navController = navHostFragment.navController


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.homeFragment ->  {
                    bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.addToCartFragmeny -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.payFragment -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }

                else -> {
                    bottomNavigationView.visibility = View.INVISIBLE
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



//private fun <T> Call<T>.enqueue(callback: Callback<ProductsItem>) {
//
//}

//private fun <T> Iterable<T>.enqueue(callback: Callback<T>) {
//
//}


