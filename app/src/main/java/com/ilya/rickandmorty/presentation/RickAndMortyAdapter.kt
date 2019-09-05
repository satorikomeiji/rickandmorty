package com.ilya.rickandmorty.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ilya.rickandmorty.R
import com.ilya.rickandmorty.repository.CharacterEntity

class RickAndMortyAdapter() :
    PagedListAdapter<CharacterEntity, CharacterViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val entity = getItem(position)
        Glide.with(holder.itemView.context).load(entity?.image ?: "").into(holder.characterImage)
        holder.characterName.text = entity?.name ?: holder.itemView.context.getString(R.string.character_no_name)
    }


    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<CharacterEntity>() {
            override fun areItemsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
}


class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val characterName: TextView
    val characterImage: ImageView

    init {
        characterName = view.findViewById(R.id.character_name)
        characterImage = view.findViewById(R.id.character_picture)

    }

}