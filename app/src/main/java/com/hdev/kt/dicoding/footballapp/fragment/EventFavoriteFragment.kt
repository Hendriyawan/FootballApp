package com.hdev.kt.dicoding.footballapp.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.hdev.kt.dicoding.footballapp.R
import com.hdev.kt.dicoding.footballapp.activity.DetailEventActivity
import com.hdev.kt.dicoding.footballapp.adapter.EventsAdapter
import com.hdev.kt.dicoding.footballapp.util.getEventFavorites
import kotlinx.android.synthetic.main.fragment_event_favorite.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 *
 */
class EventFavoriteFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_event_favorite, container, false)
    }

    /**
     * initialize RecyclerView
     */
    private fun initRecyclerView() {
        recycler_view_event?.apply {
            adapter = EventsAdapter(requireContext().getEventFavorites()) {
                startActivity<DetailEventActivity>(DetailEventActivity.EVENT_KEY to it)
            }
        }
    }
}
