package com.example.gotapp

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
    private var persons: List<Person> // Измените val на var
) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_view, parent, false)
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
        private val personInf: TextView = itemView.findViewById(R.id.personInfo)

        fun bind(model: Person) {
            personInf.text = model.firstName
            Log.d("PersonViewHolder", "Binding person: ${model.firstName}, image URL: ${model.imageUrl}")

            Glide.with(itemView.context)
                .load(model.imageUrl)
                .placeholder(R.drawable.image_placeholder)
                .apply(RequestOptions().transform(RoundedCorners(20)))
                .into(personImageView)
        }
    }
}