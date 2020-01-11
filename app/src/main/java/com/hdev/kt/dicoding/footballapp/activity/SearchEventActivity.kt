package com.hdev.kt.dicoding.footballapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import com.ferfalk.simplesearchview.SimpleSearchView
import com.hdev.kt.dicoding.footballapp.R
import com.hdev.kt.dicoding.footballapp.adapter.EventsAdapter
import com.hdev.kt.dicoding.footballapp.api.ApiService
import com.hdev.kt.dicoding.footballapp.model.Events
import com.hdev.kt.dicoding.footballapp.model.Teams
import com.hdev.kt.dicoding.footballapp.mvp.events.EventPresenter
import com.hdev.kt.dicoding.footballapp.mvp.events.EventView
import com.hdev.kt.dicoding.footballapp.util.show
import kotlinx.android.synthetic.main.activity_search_event.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SearchEventActivity : AppCompatActivity(), EventView.MainView {

    companion object {
        const val QUERY = "query"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_event)

        val apiService = ApiService.create()

        initToolbar()
        loading_anim_view.animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim)
        val query = intent.getStringExtra(QUERY)
        EventPresenter(this, apiService).searchEvent(query)

        search_view.setOnQueryTextListener(object : SimpleSearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextCleared(): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                EventPresenter(this@SearchEventActivity, apiService).searchEvent(query)
                return false
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        search_view.setMenuItem(menu!!.findItem(R.id.action_search))
        menu.findItem(R.id.action_add_to_favorite).isVisible = false
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(true)
            title = "Search Event"
        }
    }

    override fun onStartProgress() {
        layout_anim.show(true)
    }

    override fun onStopProgress() {
        layout_anim.show(false)
    }

    override fun onEventLoaded(events: List<Events>) {
        if (events.isNotEmpty()) {
            toast(resources.getString(R.string.toast_search_event_found))
            recycler_view_search_event.apply {
                adapter = EventsAdapter(events) {
                    startActivity<DetailEventActivity>(DetailEventActivity.EVENT_KEY to it)
                }
            }
        }
    }

    override fun onHomeLoaded(home: List<Teams>) {}
    override fun onAwayLoaded(away: List<Teams>) {}
    override fun onFailed(message: String?) {}
}
