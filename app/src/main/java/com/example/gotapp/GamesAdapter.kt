package com.example.gotapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GamesAdapter (
private val games: List<Game>,
    private val onItemClick:(Game)->Unit
    ) :
RecyclerView.Adapter<GamesAdapter.GameViewHolder>()
{
    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title:TextView=itemView.findViewById(R.id.gameName)
        val description:TextView=itemView.findViewById(R.id.gameDescription)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesAdapter.GameViewHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.game_recycler,parent,false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game=games[position]
        holder.title.text=game.name
        holder.description.text=game.description
        holder.itemView.setOnClickListener{
            onItemClick(game)
    }

    }
    override fun getItemCount(): Int = games.size
}