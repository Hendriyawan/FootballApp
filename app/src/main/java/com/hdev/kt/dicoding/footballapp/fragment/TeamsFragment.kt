package com.hdev.kt.dicoding.footballapp.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hdev.kt.dicoding.footballapp.R
import com.hdev.kt.dicoding.footballapp.activity.DetailTeamActivity
import com.hdev.kt.dicoding.footballapp.adapter.TeamAdapter
import com.hdev.kt.dicoding.footballapp.api.ApiService
import com.hdev.kt.dicoding.footballapp.model.Teams
import com.hdev.kt.dicoding.footballapp.mvp.teams.TeamPresenter
import com.hdev.kt.dicoding.footballapp.mvp.teams.TeamView
import com.hdev.kt.dicoding.footballapp.util.show
import kotlinx.android.synthetic.main.fragment_teams.*
import kotlinx.android.synthetic.main.fragment_teams.view.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 *
 */
private const val ID_LEAGUE = "id_league"

class TeamsFragment : BaseFragment(), TeamView.MainView {
    private var id: String? = null

    companion object {
        @JvmStatic
        fun newInstance(id: String?) =
            TeamsFragment().apply {
                arguments = Bundle().apply {
                    putString(ID_LEAGUE, id)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //get arguments
        arguments?.let {
            id = it.getString(ID_LEAGUE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_teams, container, false)
        loading = view.layout_anim
        setAnimationLoading(view.loading_anim_view)
        val apiService = ApiService.create()
        TeamPresenter(this, apiService).getTeams(id)
        return view
    }

    override fun onStartProgress() {
        loading.show(true)
    }

    override fun onStopProgress() {
        loading.show(false)
    }

    override fun onTeamLoaded(teams: List<Teams>) {
        initRecyclerView(teams)
    }

    override fun onFailed(message: String?) {

    }

    /**
     * set initialize RecyclerView
     */
    private fun initRecyclerView(teams: List<Teams>) {
        recycler_view_teams?.apply {
            adapter = TeamAdapter(teams) {
                startActivity<DetailTeamActivity>(DetailTeamActivity.TEAM_DATA_KEY to it)
            }
        }
    }
}
