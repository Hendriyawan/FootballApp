package com.hdev.kt.dicoding.footballapp.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hdev.kt.dicoding.footballapp.R
import com.hdev.kt.dicoding.footballapp.adapter.LineupAdapter
import com.hdev.kt.dicoding.footballapp.model.Events
import com.hdev.kt.dicoding.footballapp.util.getAwayFormation
import com.hdev.kt.dicoding.footballapp.util.getHomeFormation
import com.hdev.kt.dicoding.footballapp.util.setLayoutManager
import kotlinx.android.synthetic.main.fragment_lineup.view.*

private const val EVENT = "event"

class LineupFragment : Fragment() {
    private var event: Events? = null

    companion object {
        @JvmStatic
        fun newInstance(event: Events) =
            LineupFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EVENT, event)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            event = it.getParcelable(EVENT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lineup, container, false).apply {
            detail_home_team.text = event?.strHomeTeam
            if (event?.homeFormation != null) {
                detail_home_formation.text = event?.homeFormation ?: event?.getHomeFormation()
            }
            detail_away_team.text = event?.strAwayTeam
            if (event?.awayFormation != null) {
                detail_away_formation.text = event?.awayFormation ?: event?.getAwayFormation()
            }

            //Home Lineup
            detail_player_home_keeper.text = event?.homeGoalKeeper ?: "-"
            recycler_view_home_defense.apply {
                setLayoutManager()
                if (event?.homeDefense != null) {
                    val data = event?.homeDefense
                    if (data != null && data.isNotEmpty() && data.isNotEmpty()) {
                        adapter = LineupAdapter("home", data.substring(0, data.length - 1).split(";").toTypedArray())
                    }
                }
            }
            recycler_view_home_midfield.apply {
                setLayoutManager()
                if (event?.homeMidfield != null) {
                    val data = event?.homeMidfield
                    if (data != null && data.isNotEmpty()) {
                        adapter = LineupAdapter("home", data.substring(0, data.length - 1).split(";").toTypedArray())
                    }
                }
            }
            recycler_view_home_forward.apply {
                setLayoutManager()
                val data = event?.homeForward
                if (data != null && data.isNotEmpty()) {
                    adapter = LineupAdapter("home", data.substring(0, data.length - 1).split(";").toTypedArray())
                }
            }

            //Away Lineup
            recycler_view_away_forward.apply {
                setLayoutManager()
                val data = event?.awayForward
                if (data != null && data.isNotEmpty()) {
                    adapter = LineupAdapter("away", data.substring(0, data.length - 1).split(";").toTypedArray())
                }
            }
            recycler_view_away_midfield.apply {
                setLayoutManager()
                val data = event?.awayMidfield
                if (data != null && !data.isEmpty()) {
                    adapter = LineupAdapter("away", data.substring(0, data.length - 1).split(";").toTypedArray())
                }
            }
            recycler_view_away_defense.apply {
                setLayoutManager()
                val data = event?.awayDefense
                if (data != null && data.isNotEmpty()) {
                    adapter = LineupAdapter("away", data.substring(0, data.length - 1).split(";").toTypedArray())
                }
            }
            detail_player_away_keeper.text = event?.awayKeeper ?: "-"
        }
    }
}
