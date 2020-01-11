package com.hdev.kt.dicoding.footballapp.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.hdev.kt.dicoding.footballapp.R

open class BaseActivity : AppCompatActivity() {


    protected fun loadFragment(fragment: Fragment, container: Int, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(container, fragment, fragment::class.java.simpleName)
                .commit()
        }
    }

    private var classUI: String? = null

    /** set up button */
    protected fun setUpButton(classui: String?) {
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            elevation = 0f
            classUI = classui
            when (classUI) {
                "main" -> setHomeAsUpIndicator(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_trophy_white_24dp
                    )
                )
                "detail_league" -> setDisplayShowTitleEnabled(true)
                "detail_event" -> setDisplayShowTitleEnabled(false)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) if (!classUI.equals("main")) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
