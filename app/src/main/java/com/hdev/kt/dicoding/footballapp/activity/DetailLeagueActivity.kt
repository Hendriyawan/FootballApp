package com.hdev.kt.dicoding.footballapp.activity

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.view.Menu
import android.view.View
import com.ferfalk.simplesearchview.SimpleSearchView
import com.hdev.kt.dicoding.footballapp.R
import com.hdev.kt.dicoding.footballapp.adapter.ViewPagerAdapter
import com.hdev.kt.dicoding.footballapp.api.ApiService
import com.hdev.kt.dicoding.footballapp.fragment.MatchFragment
import com.hdev.kt.dicoding.footballapp.fragment.StandingsFragment
import com.hdev.kt.dicoding.footballapp.fragment.TeamsFragment
import com.hdev.kt.dicoding.footballapp.model.League
import com.hdev.kt.dicoding.footballapp.model.Leagues
import com.hdev.kt.dicoding.footballapp.mvp.leagues.LeaguePresenter
import com.hdev.kt.dicoding.footballapp.mvp.leagues.LeagueView
import com.hdev.kt.dicoding.footballapp.util.loadImage
import com.hdev.kt.dicoding.footballapp.util.setBadge
import kotlinx.android.synthetic.main.activity_detail_league.*
import kotlinx.android.synthetic.main.content_detail_league.*
import org.jetbrains.anko.startActivity

class DetailLeagueActivity : BaseActivity(), LeagueView.MainView {
    companion object {
        const val LEAGUE_DATA_KEY = "league_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)

        initViews()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        search_view.setMenuItem(menu!!.findItem(R.id.action_search))
        menu.findItem(R.id.action_add_to_favorite).isVisible = false
        return super.onCreateOptionsMenu(menu)
    }


    override fun onStartProgress() {}
    override fun onStopProgress() {}
    override fun onDetailLoaded(leagues: List<Leagues>) {
        detail_badge.setBadge(leagues[0].strPoster)
        detail_badge_league.loadImage(leagues[0].strBadge)
        detail_desc_league.text = leagues[0].strDescriptionEN
    }

    override fun onFailed(message: String?) {}

    /** initialize all view */
    private fun initViews() {

        //SearchView
        search_view.setOnQueryTextListener(object : SimpleSearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?) = false

            override fun onQueryTextSubmit(query: String?): Boolean {
                startActivity<SearchTeamActivity>(SearchTeamActivity.QUERY to query)
                return false
            }

            override fun onQueryTextCleared() = false
        })

        initToolbar()
        initBottomSheet()
        val league = intent.getParcelableExtra<League>(LEAGUE_DATA_KEY)

        toolbar_layout.title = league.name
        val apiService = ApiService.create()
        LeaguePresenter(this, apiService).getDetailLeague(league.id)
        //initViewPager(league.id)
        initViewPagerDetailLeague(league.id)
    }

    /** initialize android Toolbar */
    private fun initToolbar() {
        setSupportActionBar(toolbar)
        setUpButton("detail_league")
    }

    private fun initBottomSheet() {
        val bottomSheet = BottomSheetBehavior.from(layout_bottom_sheet)
        bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
        bottomSheet.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) {
            }

            override fun onStateChanged(p0: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
        })
        //show description League
        detail_show_desc.setOnClickListener {
            bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
        }

        //close bottom sheet / hide description of League
        close_bottom_sheet.setOnClickListener {
            bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }

    private fun initViewPagerDetailLeague(id: String) {
        view_pager.apply {
            adapter = ViewPagerAdapter(supportFragmentManager).apply {
                addFragment(MatchFragment.newInstance(id), "Match")
                addFragment(StandingsFragment.newInstance(id), "Standings")
                addFragment(TeamsFragment.newInstance(id), "Teams")
            }
        }
        tab_layout.setupWithViewPager(view_pager)

    }
}