package com.hdev.kt.dicoding.footballapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hdev.kt.dicoding.footballapp.R
import com.hdev.kt.dicoding.footballapp.model.Events
import com.hdev.kt.dicoding.footballapp.util.dateFormat
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_events.*

class EventsAdapter(private var eventList: List<Events>, private val listener: (Events) -> Unit) :
    RecyclerView.Adapter<EventsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_events, parent, false
        )
    )

    override fun getItemCount(): Int = eventList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(eventList[position])
    }

    //class Holder
    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(events: Events) {
            //date text
            item_event_date.text = dateFormat(events.dateEvent.toString(), events.strTime, "EEE, dd MMM")
            item_event_time.text = dateFormat(events.dateEvent.toString(), events.strTime, "HH:mm")

            //home team name & score
            item_home_team.text = events.strHomeTeam
            item_home_score.text = events.homeScore ?: "-"

            //away team name & score
            item_away_team.text = events.strAwayTeam
            item_away_score.text = events.awayScore ?: "-"
            itemView.setOnClickListener { listener(events) }
        }
    }
}