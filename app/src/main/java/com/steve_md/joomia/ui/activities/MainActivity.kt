package com.steve_md.joomia.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.steve_md.joomia.R
import com.steve_md.joomia.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        supportActionBar?.hide()


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView2) as NavHostFragment

        navController = navHostFragment.navController


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


