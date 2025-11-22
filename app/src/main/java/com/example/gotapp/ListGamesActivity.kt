package com.example.gotapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListGamesActivity: AppCompatActivity()   {

    lateinit var backBtn: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listgames)
        backBtn=findViewById<ImageView>(R.id.backBtn)
        backBtn.setOnClickListener {
        val Intent= Intent(this, MainActivity::class.java)
        finish()
    }

        val games = listOf(
            Game("Выбери ответ", "Нужно выбрать верный ответ"),
            Game("Выбери ответ", "Нужно выбрать верный ответ"),
            Game("Выбери ответ", "Нужно выбрать верный ответ"),
            Game("Выбери ответ", "Нужно выбрать верный ответ"),
            Game("Выбери ответ", "Нужно выбрать верный ответ"),
        )

        val adapter=GamesAdapter(games) {
           game ->
            val intent= Intent(this,GameActivity::class.java )
            startActivity(intent)
        }

        val recyclerView=findViewById<RecyclerView>(R.id.recyclerViewGames)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }


}