package com.hdev.kt.dicoding.footballapp.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.hdev.kt.dicoding.footballapp.R
import com.hdev.kt.dicoding.footballapp.activity.DetailTeamActivity
import com.hdev.kt.dicoding.footballapp.adapter.TeamAdapter
import com.hdev.kt.dicoding.footballapp.util.getTeamFavorites
import kotlinx.android.synthetic.main.fragment_team_favorite.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 *
 */
class TeamFavoriteFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        initRecyclerView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_favorite, container, false)
    }

    private fun initRecyclerView() {
        recycler_view_team?.apply {
            adapter = TeamAdapter(requireContext().getTeamFavorites()) {
                startActivity<DetailTeamActivity>(DetailTeamActivity.TEAM_DATA_KEY to it)
            }
        }
    }
}
