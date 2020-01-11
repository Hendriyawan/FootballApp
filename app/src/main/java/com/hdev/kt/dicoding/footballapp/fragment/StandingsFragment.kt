package com.hdev.kt.dicoding.footballapp.fragment


import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.hdev.kt.dicoding.footballapp.R
import com.hdev.kt.dicoding.footballapp.api.ApiService
import com.hdev.kt.dicoding.footballapp.model.Tables
import com.hdev.kt.dicoding.footballapp.mvp.standings.StandingPresenter
import com.hdev.kt.dicoding.footballapp.mvp.standings.StandingView
import com.hdev.kt.dicoding.footballapp.util.show
import kotlinx.android.synthetic.main.fragment_standings.*
import kotlinx.android.synthetic.main.fragment_teams.*
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.margin

/**
 * A simple [Fragment] subclass.
 *
 */
private const val ID_LEAGUE = "id_league"

class StandingsFragment : BaseFragment(), StandingView.MainView {

    private var id: String? = null

    companion object {
        @JvmStatic
        fun newInstance(id: String) =
            StandingsFragment().apply {
                arguments = Bundle().apply { putString(ID_LEAGUE, id) }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //get arguments
        arguments?.let {
            id = it.getString(ID_LEAGUE)
        }

        val apiService = ApiService.create()
        StandingPresenter(this, apiService).getTable(id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_standings, container, false)
    }


    override fun onStartProgress() {

    }

    override fun onStopProgress() {
    }

    override fun onTableLoaded(tables: List<Tables>) {
        initViews(tables)
    }

    override fun onFailed(message: String?) = Unit

    @SuppressLint("SetTextI18n")
    private fun initViews(tables: List<Tables>) {
        //LayoutParams
        val tableRowLP = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
            .apply { margin = 10 }
        val tableLayoutLP =
            TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT)
                .apply { margin = 10 }

        //title No
        val no = TextView(requireContext()).apply {
            setPadding(10, 0, 10, 0)
            setTextColor(Color.WHITE)
            textSize = 17f
            text = "No"
        }
        //title Team Name
        val teamName = TextView(requireContext()).apply {
            setPadding(10, 0, 10, 0)
            layoutParams = tableRowLP
            setTextColor(Color.WHITE)
            textSize = 17f
            text = "TEAM"
        }

        //title played
        val played = TextView(requireContext()).apply {
            setPadding(10, 0, 10, 0)
            layoutParams = tableRowLP
            setTextColor(Color.WHITE)
            textSize = 17f
            text = "PLAYED"
        }

        //title win
        val win = TextView(requireContext()).apply {
            setPadding(10, 0, 10, 0)
            layoutParams = tableRowLP
            setTextColor(Color.WHITE)
            textSize = 17f
            text = "WIN"
        }

        //title loss
        val loss = TextView(requireContext()).apply {
            setPadding(10, 0, 10, 0)
            layoutParams = tableRowLP
            setTextColor(Color.WHITE)
            textSize = 17f
            text = "LOSS"
        }

        //title total
        val total = TextView(requireContext()).apply {
            setPadding(10, 0, 10, 0)
            layoutParams = tableRowLP
            setTextColor(Color.WHITE)
            textSize = 17f
            text = "TOTAL"
        }
        //table row
        val tableRowTitle = TableRow(requireContext()).apply {
            setPadding(5, 5, 5, 5)
            layoutParams = tableLayoutLP
            backgroundResource = R.drawable.bg_table_row_title
            addView(no, tableRowLP)
            addView(teamName, tableRowLP)
            addView(played, tableRowLP)
            addView(win, tableRowLP)
            addView(loss, tableRowLP)
            addView(total, tableRowLP)
        }
        table_layout_standings.addView(tableRowTitle, tableLayoutLP)

        tables.apply {
            for (i in 0 until size) {
                val noValue = TextView(requireContext()).apply {
                    gravity = Gravity.CENTER
                    text = "${i + 1}"
                }
                val teamNameValue = TextView(requireContext()).apply {
                    gravity = Gravity.CENTER
                    text = "${tables[i].name}"
                }

                val playedValue = TextView(requireContext()).apply {
                    gravity = Gravity.CENTER
                    text = "${tables[i].played}"
                }

                val winValue = TextView(requireContext()).apply {
                    gravity = Gravity.CENTER
                    text = "${tables[i].win}"
                }

                val lossValue = TextView(requireContext()).apply {
                    gravity = Gravity.CENTER
                    text = "${tables[i].loss}"
                }

                val totalValue = TextView(requireContext()).apply {
                    gravity = Gravity.CENTER
                    text = "${tables[i].total}"
                }

                //TabRow Value
                val tableRowValue = TableRow(requireContext()).apply {
                    setPadding(5, 5, 5, 5)
                    addView(noValue)
                    addView(teamNameValue)
                    addView(playedValue)
                    addView(winValue)
                    addView(lossValue)
                    addView(totalValue)
                }
                table_layout_standings.addView(tableRowValue)

                if ((i % 2) == 0) {
                    tableRowValue.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            android.R.color.darker_gray
                        )
                    )
                }
            }
        }
    }
}
