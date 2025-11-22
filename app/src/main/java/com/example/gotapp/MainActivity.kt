package com.example.gotapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.gotapp.R.layout.activity_main


class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)


    val listPersinsButton = findViewById<Button>(R.id.listPersonsBtn)
    listPersinsButton.setOnClickListener {
        val displayIntent = Intent(this, ListPersonsActivity::class.java)
        startActivity(displayIntent)
    }
        val listGamesButton=findViewById<Button>(R.id.games)
        listGamesButton.setOnClickListener{
            val displayIntent= Intent(this,ListGamesActivity::class.java)
            Log.d("listGamesButton.setOnClickListener", "listGamesButton.setOnClickListener")
            startActivity(displayIntent)

        }
}}