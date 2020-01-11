package com.hdev.kt.dicoding.footballapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hdev.kt.dicoding.footballapp.R
import com.hdev.kt.dicoding.footballapp.api.ApiService
import com.hdev.kt.dicoding.footballapp.model.Events
import com.hdev.kt.dicoding.footballapp.model.Teams
import com.hdev.kt.dicoding.footballapp.mvp.events.EventPresenter
import com.hdev.kt.dicoding.footballapp.mvp.events.EventView
import com.hdev.kt.dicoding.footballapp.util.show
import kotlinx.android.synthetic.main.fragment_next_match.*
import kotlinx.android.synthetic.main.fragment_next_match.view.*

private const val ID_LEAGUE = "id_league"

class NextMatchFragment : BaseFragment(), EventView.MainView {
    private var id: String? = null

    companion object {
        @JvmStatic
        fun newInstance(id: String?) =
            NextMatchFragment().apply {
                arguments = Bundle().apply {
                    putString(ID_LEAGUE, id)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString(ID_LEAGUE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_next_match, container, false)
        loading = view.layout_anim
        setAnimationLoading(view.loading_anim_view)
        val apiService = ApiService.create()
        EventPresenter(this, apiService).getNextMatch(id)
        return view
    }

    override fun onStartProgress() {
        loading.show(true)
    }

    override fun onStopProgress() {
        loading.show(false)
    }

    override fun onEventLoaded(events: List<Events>) {
        initRecyclerView(recycler_view_next_match, events)
    }

    override fun onHomeLoaded(home: List<Teams>) = Unit
    override fun onAwayLoaded(away: List<Teams>) = Unit

    override fun onFailed(message: String?) {
        loading.show(false)
    }
}
