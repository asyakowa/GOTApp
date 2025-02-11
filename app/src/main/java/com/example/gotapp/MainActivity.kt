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

        val persons = listOf(Persons("Kate"), Persons("Ann"), Persons("Tim"))

        for (person in persons) {
            println("Персонаж #${person.firstName}")
        }

        val personAdapter = PersonAdapter(persons)
        recyclerView.adapter = personAdapter
    }
}