package com.hdev.kt.dicoding.footballapp.activity

import android.os.Bundle
import android.view.Menu
import com.ferfalk.simplesearchview.SimpleSearchView
import com.hdev.kt.dicoding.footballapp.R
import com.hdev.kt.dicoding.footballapp.fragment.FavoritesFragment
import com.hdev.kt.dicoding.footballapp.fragment.LeagueFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Toolbar
        initToolbar()

        //BottomNavigationView
        navigation.apply {
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_league -> {
                        loadFragment(LeagueFragment(), R.id.fragment_container, savedInstanceState)
                    }
                    R.id.navigation_league_favorite -> {
                        loadFragment(FavoritesFragment(), R.id.fragment_container, savedInstanceState)
                    }
                }
                true
            }
        }
        navigation.selectedItemId = R.id.navigation_league

        //SearchView
        search_view.setOnQueryTextListener(object : SimpleSearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextCleared(): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                startActivity<SearchEventActivity>(SearchEventActivity.QUERY to query)
                return false
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        search_view.setMenuItem(menu!!.findItem(R.id.action_search))
        menu.findItem(R.id.action_add_to_favorite)?.isVisible = false
        return true
    }

    /** initialize android Toolbar */
    private fun initToolbar() {
        setSupportActionBar(toolbar)
        setUpButton("main")
    }
}