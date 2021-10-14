package com.example.desafionetshoes.presentation

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.desafionetshoes.R
import com.example.desafionetshoes.data.Gist
import com.example.desafionetshoes.data.GistsList

const val CHOOSED_GIST_ID = "com.example.desafionetshoes.ID"

public class GistsAdapter(var context: Context, var dataset: MutableList<Gist> = mutableListOf()) : RecyclerView.Adapter<GistsAdapter.GistsViewHolder>() {
//public class GistsAdapter(var context: Context, var dataset: MutableList<GistsList> = mutableListOf()) : RecyclerView.Adapter<GistsAdapter.GistsViewHolder>() {
    inner class GistsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val gistUserName: TextView = view.findViewById(R.id.TxtGusrName)
        val gistArType: TextView = view.findViewById(R.id.TxtGtype)
        val btnFavorite: ToggleButton = view.findViewById(R.id.BtnFavorite)
        val gistUserPhoto: ImageButton = view.findViewById(R.id.ImbGusrPhoto)
        //val idMovie: TextView = view.findViewById(R.id.idMovie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistsViewHolder =
        GistsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.gist_item, parent, false)
        )

    override fun onBindViewHolder(holder: GistsViewHolder, position: Int) {
        holder.gistUserName.text = dataset[position].owner.login
        holder.gistUserPhoto.load(dataset[position].owner.avatar_url)
        holder.gistArType.text = dataset[position].files.map { file -> file.value.type }.toString()

        // button toggle
        //holder.idMovie.text = dataset[position].id.toString()
        holder.gistUserPhoto.setOnClickListener{
            val intent = Intent(context, DetailGistActivity::class.java).apply{
                putExtra(CHOOSED_GIST_ID,dataset[position].id.toString())
            }
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}