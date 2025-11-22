package com.example.gotapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class GameActivity: AppCompatActivity() {
    lateinit var backBtn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        backBtn=findViewById<ImageView>(R.id.backBtn)
        backBtn.setOnClickListener {
            val Intent= Intent(this, MainActivity::class.java)
            finish()
        }
    }
}