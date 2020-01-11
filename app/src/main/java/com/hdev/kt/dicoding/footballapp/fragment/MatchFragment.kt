package com.hdev.kt.dicoding.footballapp.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.hdev.kt.dicoding.footballapp.R
import com.hdev.kt.dicoding.footballapp.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_match.*


/**
 * A simple [Fragment] subclass.
 *
 */
private const val ID_LEAGUE = "id_league"

class MatchFragment : Fragment() {
    private var ID: String? = null

    companion object {
        @JvmStatic
        fun newInstance(id: String) =
            MatchFragment().apply {
                arguments = Bundle().apply { putString(ID_LEAGUE, id) }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //get argument
        arguments?.let {
            ID = it.getString(ID_LEAGUE)
        }

        //view pager match (last & next)
        view_pager_match.apply {
            adapter = ViewPagerAdapter(childFragmentManager).apply {
                addFragment(LastMatchFragment.newInstance(ID), "Last Match")
                addFragment(NextMatchFragment.newInstance(ID), "Next Match")
            }
        }
        tab_layout.setupWithViewPager(view_pager_match)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match, container, false)
    }
}
