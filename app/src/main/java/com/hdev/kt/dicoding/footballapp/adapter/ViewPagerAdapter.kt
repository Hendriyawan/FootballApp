package com.hdev.kt.dicoding.footballapp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragments: ArrayList<Fragment> = ArrayList()
    private val titleList: ArrayList<String> = ArrayList()

    override fun getCount() = fragments.size
    override fun getItem(position: Int) = fragments[position]
    override fun getPageTitle(position: Int) = titleList[position]

    //add fragment
    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        titleList.add(title)
    }
}