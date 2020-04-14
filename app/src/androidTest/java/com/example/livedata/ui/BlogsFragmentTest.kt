package com.example.livedata.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.livedata.MainApplication
import com.example.livedata.R
import com.example.livedata.adapter.AdapterRow
import com.example.livedata.data.Blog
import com.example.livedata.data.BlogRepository
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class BlogsFragmentTest{

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    /*
    * recycler view comes into the view
    * */
    // here we are checking weather recyclerview of fragmengt getting displayed or not
    @Test
    fun test_isFragmentRecyclerviewDisplayed() {
        launchFragmentInContainer<BlogsFragment>()
        onView(withId(R.id.recyclerview))
            .check(matches(isDisplayed()))
    }

}