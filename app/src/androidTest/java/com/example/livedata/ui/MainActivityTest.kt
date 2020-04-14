package com.example.livedata.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.livedata.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get : Rule
    val activityRule : ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)  // global activity scenario

    // test_isActivityInView() test method will test weather activity ia in view or not with respect to id we provided
    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.root_layout)).check(matches(isDisplayed()))     // method 1
        //onView(withId(R.id.root_layout)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))   // method 2

    }

    // test_isFragmentInView test method will test weather fragment ia in view or not with respect to id we provided
    @Test
    fun test_isFragmentInView() {
        onView(withId(R.id.fragment_blog)).check(matches(isDisplayed()))
    }

}