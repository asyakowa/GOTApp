package com.example.gotapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.gotapp.R.layout.person_page_info

class PersonPageInfoActivity:AppCompatActivity() {
    private lateinit var fullNamePerson: TextView
    private lateinit var familyPerson: TextView
    private lateinit var titlePerson: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(person_page_info)
        fullNamePerson = findViewById(R.id.fullNamePerson)
        familyPerson= findViewById(R.id.familyPerson)
        titlePerson= findViewById(R.id.titlePerson)
        val imageView: ImageView = findViewById(R.id.imageViewPerson)
        val fullName = intent.getStringExtra("fullName")
        val family = intent.getStringExtra("family")
        val title = intent.getStringExtra("title")
        val imageUrl = intent.getStringExtra("imageUrl")
        fullNamePerson.text=fullName
        familyPerson.text=family
        titlePerson.text=title

        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.image_placeholder)
            .apply(RequestOptions().transform(RoundedCorners(20)))
            .into(imageView)
    }

}

