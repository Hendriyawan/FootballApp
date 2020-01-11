package com.hdev.kt.dicoding.footballapp.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import com.hdev.kt.dicoding.footballapp.R
import com.hdev.kt.dicoding.footballapp.adapter.ViewPagerAdapter
import com.hdev.kt.dicoding.footballapp.api.ApiService
import com.hdev.kt.dicoding.footballapp.fragment.LineupFragment
import com.hdev.kt.dicoding.footballapp.fragment.TimelineFragment
import com.hdev.kt.dicoding.footballapp.model.Events
import com.hdev.kt.dicoding.footballapp.model.Teams
import com.hdev.kt.dicoding.footballapp.mvp.events.EventPresenter
import com.hdev.kt.dicoding.footballapp.mvp.events.EventView
import com.hdev.kt.dicoding.footballapp.util.*
import kotlinx.android.synthetic.main.activity_detail_event.*

class DetailEventActivity : BaseActivity(), EventView.MainView {

    companion object {
        const val EVENT_KEY = "event"
    }

    //MenuItem
    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_event)

        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        menu?.findItem(R.id.action_search)?.isVisible = false
        menuItem = menu
        setFavoriteIcon(intent.getParcelableExtra<Events>(EVENT_KEY).idEvent!!)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_add_to_favorite -> {
                val event = intent.getParcelableExtra<Events>(EVENT_KEY)
                val id = event.idEvent
                if (isEventFavorite(id!!)) removeEventFavorite(id) else addEventToFavorite(event)
                setFavoriteIcon(id)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViews() {
        initToolbar()
        setAnimationLoading(loading_anim_view)
        val event = intent.getParcelableExtra<Events>(EVENT_KEY)
        if (event != null) {
            val apiService = ApiService.create()
            EventPresenter(this, apiService).getDetailEvent(event.idEvent)
            EventPresenter(this, apiService).getHomeDetail(event.idHomeTeam)
            EventPresenter(this, apiService).getAwayDetail(event.idAwayTeam)
        }
    }

    override fun onStartProgress() {
        layout_anim.show(true)
    }

    override fun onStopProgress() {
        layout_anim.show(false)
    }

    override fun onEventLoaded(events: List<Events>) {
        val data = events[0]
        detail_home_team.text = data.strHomeTeam ?: "-"
        detail_away_team.text = data.strAwayTeam ?: "-"
        detail_home_score.text = data.homeScore
        detail_away_score.text = data.awayScore
        initViewPager(events[0])
    }

    override fun onHomeLoaded(home: List<Teams>) {
        detail_home_team_badge.loadImage(home[0].teamBadge)
    }

    override fun onAwayLoaded(away: List<Teams>) {
        detail_away_team_badge.loadImage(away[0].teamBadge)
    }

    override fun onFailed(message: String?) {
    }

    /** initialize Toolbar */
    private fun initToolbar() {
        setSupportActionBar(toolbar)
        setUpButton("detail_event")
    }

    //init ViewPager
    private fun initViewPager(events: Events) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(TimelineFragment.newInstance(events), "Timeline")
        adapter.addFragment(LineupFragment.newInstance(events), "Lineup")
        view_pager.adapter = adapter
        tab_layout.setupWithViewPager(view_pager)
    }


    private fun setAnimationLoading(view: View) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim)
        view.animation = animation
    }

    /**
     * Change icon option menu when Event is added to Favorite
     * @param id even id
     */
    private fun setFavoriteIcon(id: String) {
        if (isEventFavorite(id)) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorite)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorite)
        }
    }
}
