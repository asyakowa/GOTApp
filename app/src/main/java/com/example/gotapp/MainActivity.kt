package com.example.gotapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val persons = listOf(
            Person("Kate", "https://i.pravatar.cc/150?img=5"),
            Person("Ann", "https://i.pravatar.cc/150?img=9"),
            Person("Tim", "https://i.pravatar.cc/150?img=8")
        )


        val repeatedPersons = List(17) { persons }.flatten()

        val personAdapter = PersonAdapter(repeatedPersons)
        recyclerView.adapter = personAdapter
    }


}
