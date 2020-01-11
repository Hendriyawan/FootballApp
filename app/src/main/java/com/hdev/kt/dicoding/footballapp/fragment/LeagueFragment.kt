package com.hdev.kt.dicoding.footballapp.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.hdev.kt.dicoding.footballapp.R
import com.hdev.kt.dicoding.footballapp.activity.DetailLeagueActivity
import com.hdev.kt.dicoding.footballapp.adapter.LeagueAdapter
import kotlinx.android.synthetic.main.fragment_league.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 *
 */
class LeagueFragment : BaseFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //RecyclerView League
        recycler_view_league.apply {
            adapter = LeagueAdapter(getLeagueData()) {
                startActivity<DetailLeagueActivity>(DetailLeagueActivity.LEAGUE_DATA_KEY to it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league, container, false)
    }
}
