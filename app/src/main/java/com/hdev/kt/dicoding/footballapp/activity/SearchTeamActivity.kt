package com.hdev.kt.dicoding.footballapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.animation.AnimationUtils
import com.ferfalk.simplesearchview.SimpleSearchView
import com.hdev.kt.dicoding.footballapp.R
import com.hdev.kt.dicoding.footballapp.adapter.TeamAdapter
import com.hdev.kt.dicoding.footballapp.api.ApiService
import com.hdev.kt.dicoding.footballapp.model.Teams
import com.hdev.kt.dicoding.footballapp.mvp.teams.TeamPresenter
import com.hdev.kt.dicoding.footballapp.mvp.teams.TeamView
import com.hdev.kt.dicoding.footballapp.util.show
import kotlinx.android.synthetic.main.activity_search_team.*
import org.jetbrains.anko.startActivity

class SearchTeamActivity : AppCompatActivity(), TeamView.MainView {

    companion object {
        const val QUERY = "query"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)

        val apiService = ApiService.create()

        initToolbar()
        loading_anim_view.animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim)
        val query = intent.getStringExtra(QUERY)
        TeamPresenter(this, apiService).searchTeam(query)

        search_view.setOnQueryTextListener(object : SimpleSearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                TeamPresenter(this@SearchTeamActivity, apiService).searchTeam(query)
                return false
            }

            override fun onQueryTextCleared() = false
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        search_view.setMenuItem(menu!!.findItem(R.id.action_search))
        menu.findItem(R.id.action_add_to_favorite).isVisible = false
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * init Toolbar
     */
    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(true)
            title = "Search Team"
        }
    }

    override fun onStartProgress() {
        layout_anim.show(true)
    }

    override fun onStopProgress() {
        layout_anim.show(false)
    }

    override fun onTeamLoaded(teams: List<Teams>) {
        if (teams.isNotEmpty()) {
            recycler_view_search_team.apply {
                adapter = TeamAdapter(teams) {
                    startActivity<DetailTeamActivity>(DetailTeamActivity.TEAM_DATA_KEY to it)
                }
            }
        }
    }

    override fun onFailed(message: String?) {
    }
}
