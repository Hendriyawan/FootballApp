package com.hdev.kt.dicoding.footballapp.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hdev.kt.dicoding.footballapp.R
import com.hdev.kt.dicoding.footballapp.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_favorite_event.*


/**
 * A simple [Fragment] subclass.
 *
 */
class FavoritesFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view_pager?.apply {
            adapter = ViewPagerAdapter(childFragmentManager).apply {
                addFragment(EventFavoriteFragment(), "Favorite Events")
                addFragment(TeamFavoriteFragment(), "Favorite Teams")
            }
        }
        tab_layout.setupWithViewPager(view_pager)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_event, container, false)
    }
}
