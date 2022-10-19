package com.steve_md.joomia.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.steve_md.joomia.R
import com.steve_md.joomia.adapter.ProductsItemAdapter
import com.steve_md.joomia.model.Products
import com.steve_md.joomia.model.ProductsItem
import com.steve_md.joomia.network.api.FakeJoomiaApp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {

    private val adapter by lazy { ProductsItemAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        // Declaring our views
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        FakeJoomiaApp.retrofitApi.getProducts().enqueue(object : retrofit2.Callback<ArrayList<ProductsItem>>{
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


        })
        }
    }

//private fun <T> Call<T>.enqueue(callback: Callback<ProductsItem>) {
//
//}

//private fun <T> Iterable<T>.enqueue(callback: Callback<T>) {
//
//}


