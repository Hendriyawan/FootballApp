package com.hdev.kt.dicoding.footballapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hdev.kt.dicoding.footballapp.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_player_home_formation.*

class LineupAdapter(private val team: String, private val names: Array<String>) :
    RecyclerView.Adapter<LineupAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return when (team) {
            "home" -> ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_player_home_formation,
                    parent,
                    false
                )
            )
            else -> ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_player_away_formation,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount() = names.size - 1
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(names[position])
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(name: String) {
            item_player_name_formation.text = name
        }
    }
}