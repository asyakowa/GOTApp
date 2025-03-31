package com.example.gotapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://thronesapi.com/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val personsApiService = retrofit.create<PersonsApiService>()
    private lateinit var personAdapter: PersonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate called")
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        personAdapter = PersonAdapter(emptyList())
        recyclerView.adapter = personAdapter


        getPersonsFromApi(recyclerView)
    }

    private fun getPersonsFromApi(recyclerView: RecyclerView) {
        personsApiService.getPersons().enqueue(object : Callback<List<Person>> {
            override fun onResponse(call: Call<List<Person>>, response: Response<List<Person>>) {
                Log.d("API Response", "Response code: ${response.code()}")

                if (response.isSuccessful) {
                    response.body()?.let { persons ->
                        Log.d("API Response", "Response body: $persons")
                        personAdapter.updateData(persons)
//                        personAdapter.notifyDataSetChanged()
                        recyclerView.visibility = View.VISIBLE
                    }
//                       ?: run {
//                        Log.e("API Error", "Response body is null")
//                        recyclerView.visibility = View.GONE
//                    }
                } else {
                    Log.e("API Error", "Error code: ${response.code()}")
                    val errorJson = response.errorBody()?.string()
                    Log.e("API Error", "Error body: $errorJson")
                    recyclerView.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<List<Person>>, t: Throwable) {
                Log.e("API Error", "Request failed: ${t.message}")
                recyclerView.visibility = View.GONE
            }
        })
    }
}