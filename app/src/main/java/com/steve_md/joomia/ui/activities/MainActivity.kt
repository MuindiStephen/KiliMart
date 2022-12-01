package com.steve_md.joomia.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.steve_md.joomia.R
import com.steve_md.joomia.adapter.ProductsItemAdapter


class MainActivity : AppCompatActivity() {

    private val adapter by lazy { ProductsItemAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        // Declaring our views
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)

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

        }
    }

//private fun <T> Call<T>.enqueue(callback: Callback<ProductsItem>) {
//
//}

//private fun <T> Iterable<T>.enqueue(callback: Callback<T>) {
//
//}


