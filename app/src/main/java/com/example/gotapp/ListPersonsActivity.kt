package com.example.gotapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ListPersonsActivity : AppCompatActivity(),  OnCharacterClickListener {
     lateinit var updateBtn: Button
    lateinit var errorCnn: TextView
    lateinit var backButton: ImageView
    private lateinit var recyclerView: RecyclerView
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://thronesapi.com/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val personsApiService = retrofit.create<PersonsApiService>()
    private lateinit var personAdapter: PersonAdapter

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ListPersonsActivity", "onCreate called")
        setContentView(R.layout.activity_listpersons)

         recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
         errorCnn=findViewById<TextView>(R.id.errorCnn)
         updateBtn= findViewById<Button>(R.id.refreshBtn)
        personAdapter = PersonAdapter(emptyList())
        recyclerView.adapter = personAdapter
         backButton=findViewById<ImageView>(R.id.backBtn)

        getPersonsFromApi()
         openLastActivity()
         updateList()
    }

private fun getPersonsFromApi(
//    recyclerView: RecyclerView
) {
    personsApiService.getPersons().enqueue(object : Callback<List<Person>> {
        override fun onResponse(call: Call<List<Person>>, response: Response<List<Person>>) {
            Log.d("API Response", "Response code: ${response.code()}")

            if (response.isSuccessful) {
                response.body()?.let { persons ->
                    Log.d("API Response", "Response body: $persons")
                    personAdapter.updateData(persons)
                    recyclerView.visibility = View.VISIBLE
                    updateBtn.visibility=View.GONE
                    errorCnn.visibility=View.GONE
                }

            } else {
                Log.e("API Error", "Error code: ${response.code()}")
                val errorJson = response.errorBody()?.string()
                Log.e("API Error", "Error body: $errorJson")
                recyclerView.visibility = View.GONE

                updateBtn.visibility=View.VISIBLE
                errorCnn.visibility=View.VISIBLE
            }
        }

        override fun onFailure(call: Call<List<Person>>, t: Throwable) {
            Log.e("API Error", "Request failed: ${t.message}")
            recyclerView.visibility = View.GONE
            updateBtn.visibility=View.VISIBLE
            errorCnn.visibility=View.VISIBLE
        }
    })
}
    fun openLastActivity() {
    backButton.setOnClickListener {
        val intent = Intent(this, MainActivity::class.java)
        finish()
    }
    }

    override fun onCharacterClick(character: Character) {
        val intent = Intent(this, PersonPageInfoActivity::class.java).apply {
            putExtra("character_key", character)
        }
        startActivity(intent)
    }

    fun updateList(){
        updateBtn.setOnClickListener{
        getPersonsFromApi()
        }

    }


}