package com.hdev.kt.dicoding.footballapp.adapter

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hdev.kt.dicoding.footballapp.R
import com.hdev.kt.dicoding.footballapp.model.Teams
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_team.*

class TeamAdapter(private val teamList: List<Teams>, private val listener: (Teams) -> Unit) :
    RecyclerView.Adapter<TeamAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_team, parent, false
            )
        )

    override fun getItemCount() = teamList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(teamList[position])
    }

    //class ViewHolder
    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(teams: Teams) {
            if(teams.teamBadge != null){
                teams.teamBadge.let {
                    Picasso.get().load(Uri.parse(it))
                        .placeholder(R.drawable.ic_image_blank)
                        .error(R.drawable.ic_image_blank)
                        .into(item_team_badge)
                }
            }
            item_team_name.text = teams.team
            itemView.setOnClickListener { listener(teams) }
        }
    }
}