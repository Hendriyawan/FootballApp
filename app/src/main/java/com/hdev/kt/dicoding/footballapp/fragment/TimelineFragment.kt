package com.hdev.kt.dicoding.footballapp.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hdev.kt.dicoding.footballapp.R
import com.hdev.kt.dicoding.footballapp.model.Events
import kotlinx.android.synthetic.main.fragment_timeline.view.*

private const val EVENT = "event"

class TimelineFragment : Fragment() {
    private var event: Events? = null

    companion object {
        @JvmStatic
        fun newInstance(event: Events) =
            TimelineFragment().apply {
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
        return inflater.inflate(R.layout.fragment_timeline, container, false).apply {
            detail_home_goals.text = event?.homeGoalDetail?.replace(";", "\n") ?: "-"
            detail_away_goals.text = event?.awayGoalDetail?.replace(";", "\n") ?: "-"
            detail_home_yellow_card.text = event?.homeYellowCards?.replace(";", "\n") ?: "-"
            detail_away_yellow_card.text = event?.awayYellowCards?.replace(";", "\n") ?: "-"
            detail_home_red_card.text = event?.homeRedCards?.replace(";", "\n") ?: "-"
            detail_away_red_card.text = event?.awayRedCards?.replace(";", "\n") ?: "-"
        }
    }
}
