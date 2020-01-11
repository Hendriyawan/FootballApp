package com.hdev.kt.dicoding.footballapp.activity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.hdev.kt.dicoding.footballapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAppBehaviour() {

        delay()
        onView(withId(R.id.action_search)).check(matches(isDisplayed()))
        onView(withId(R.id.action_search)).perform(click())
        delay()


        onView(withId(R.id.search_view)).check(matches(isDisplayed()))
        onView(withId(R.id.searchEditText)).check(matches(isDisplayed())).perform(typeText("Arsenal"))
        onView(withId(R.id.searchEditText)).check(matches(isDisplayed())).perform(pressImeActionButton())

        onView(withId(R.id.recycler_view_search_event)).check(matches(isDisplayed()))
        delay()

        onView(withId(R.id.recycler_view_search_event)).check(matches(isDisplayed()))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1, click()
                )
            )

        delay()
        onView(withId(R.id.detail_home_team_badge)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_away_team_badge)).check(matches(isDisplayed()))
        onView(withId(R.id.action_add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.action_add_to_favorite)).perform(click())
        delay()
    }

    private fun delay() {
        try {
            sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}