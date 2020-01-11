package com.hdev.kt.dicoding.footballapp.fragment


import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import com.hdev.kt.dicoding.footballapp.R
import com.hdev.kt.dicoding.footballapp.activity.DetailEventActivity
import com.hdev.kt.dicoding.footballapp.adapter.EventsAdapter
import com.hdev.kt.dicoding.footballapp.model.Events
import com.hdev.kt.dicoding.footballapp.model.League
import org.jetbrains.anko.support.v4.startActivity
import org.json.JSONObject

open class BaseFragment : Fragment() {
    protected lateinit var loading: LinearLayout
    protected fun setAnimationLoading(view: View) {
        val animation = AnimationUtils.loadAnimation(activity, R.anim.rotate_anim)
        view.animation = animation
    }

    protected fun initRecyclerView(recyclerView: RecyclerView?, eventList: List<Events>) {
        recyclerView?.adapter = EventsAdapter(eventList) {
            //action open detail Events
            startActivity<DetailEventActivity>(DetailEventActivity.EVENT_KEY to it)
        }
    }

    /**
     * get league data from assets (id, name, badge)
     */
    protected fun getLeagueData(): List<League> {
        val leagueList: MutableList<League> = mutableListOf()
        val jsonFile: String? = requireContext().assets.open("league.json").bufferedReader().use { it.readText() }
        JSONObject(jsonFile).apply {
            getJSONArray("league").apply {
                for (i in 0 until length()) {
                    getJSONObject(i).apply {
                        leagueList.add(League(getString("id"), getString("name"), getString("badge")))
                    }
                }
            }
        }
        return leagueList
    }
}
