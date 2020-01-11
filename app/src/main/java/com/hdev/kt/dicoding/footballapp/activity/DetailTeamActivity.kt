package com.hdev.kt.dicoding.footballapp.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import com.hdev.kt.dicoding.footballapp.R
import com.hdev.kt.dicoding.footballapp.api.ApiService
import com.hdev.kt.dicoding.footballapp.model.Teams
import com.hdev.kt.dicoding.footballapp.mvp.teams.TeamPresenter
import com.hdev.kt.dicoding.footballapp.mvp.teams.TeamView
import com.hdev.kt.dicoding.footballapp.util.*
import kotlinx.android.synthetic.main.activity_detail_team.*

class DetailTeamActivity : BaseActivity(), TeamView.MainView {

    companion object {
        const val TEAM_DATA_KEY = "team_data"
    }

    //MenuItem
    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        menu?.findItem(R.id.action_search)?.isVisible = false
        menuItem = menu
        setFavoriteIcon(intent.getParcelableExtra<Teams>(TEAM_DATA_KEY).idTeam!!)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_add_to_favorite -> {
                val teams = intent.getParcelableExtra<Teams>(TEAM_DATA_KEY)
                val id = teams.idTeam
                if (isTeamFavorite(id!!)) removeTeamFavorite(id) else addTeamToFavorite(teams)
                setFavoriteIcon(id)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStartProgress() {
        //layout_anim.show(true)
    }

    override fun onStopProgress() {
        //layout_anim.show(false)
    }

    override fun onTeamLoaded(teams: List<Teams>) {
        detail_team_stadium_thumb.setBadge(teams[0].stadiumThumb)
        detail_team_name.text = teams[0].team
        detail_desc_name_team.text = teams[0].team
        detail_team_badge.loadImage(teams[0].teamBadge)
        detail_team_desc.text = teams[0].descriptionEN
        detail_team_stadium.text = teams[0].stadium
        detail_team_website.text = teams[0].website
        detail_team_stadium_capacity.text = teams[0].stadiumCapacity
    }

    override fun onFailed(message: String?) {

    }

    /**
     * initialize all views
     */
    private fun initViews() {
        initToolbar()
        layout_anim.animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim)
        val teams = intent.getParcelableExtra<Teams>(TEAM_DATA_KEY)
        val apiService = ApiService.create()
        TeamPresenter(this, apiService).getDetailTeam(teams.idTeam)
    }

    /** set Toolbar */
    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setUpButton("detail_team")
    }

    /**
     *  Change icon option menu when Team is added to Favorite
     *  @param id Team id
     *
     **/
    private fun setFavoriteIcon(id: String) {
        if (isTeamFavorite(id)) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorite)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorite)
        }
    }
}
