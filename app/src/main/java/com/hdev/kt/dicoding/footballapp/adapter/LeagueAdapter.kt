package com.hdev.kt.dicoding.footballapp.adapter

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hdev.kt.dicoding.footballapp.R
import com.hdev.kt.dicoding.footballapp.model.League
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_league.*

class LeagueAdapter(private val leagueList: List<League>, private val listener: (League) -> Unit) :
    RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_league, parent, false
            )
        )

    override fun getItemCount() = leagueList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(leagueList[position])
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(league: League) {
            league.badge.let {
                Picasso.get().load(Uri.parse(it))
                    .placeholder(R.drawable.ic_image_blank)
                    .error(R.drawable.ic_image_blank)
                    .into(item_league_badge)
            }
            item_league_name.text = league.name
            itemView.setOnClickListener { listener(league) }
        }
    }
}