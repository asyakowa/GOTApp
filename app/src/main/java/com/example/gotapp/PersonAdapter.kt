package com.example.gotapp

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class PersonAdapter(
    private var persons: List<Person>
) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_view_recycler, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(persons[position])



    }

    override fun getItemCount(): Int {
        return persons.size
    }

    fun updateData(newPersons: List<Person>) {
        persons = newPersons // Обновляем список persons
        notifyDataSetChanged() // Уведомляем адаптер об изменении данных
    }

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val personImageView: ImageView = itemView.findViewById(R.id.imageView)
        private val personFullName: TextView = itemView.findViewById(R.id.fullName)
        private val personTitle: TextView = itemView.findViewById(R.id.title)
        private val personFamily: TextView = itemView.findViewById(R.id.family)

        fun bind(model: Person) {
            personFullName.text = model.fullName
            personTitle.text=model.title
            personFamily.text=model.family
            Log.d("PersonViewHolder", "Binding person: ${model.fullName}, image URL: ${model.imageUrl}")



            Glide.with(itemView.context)
                .load(model.imageUrl)
                .placeholder(R.drawable.image_placeholder)
                .apply(RequestOptions().transform(RoundedCorners(20)))
                .into(personImageView)
        }
    }
}