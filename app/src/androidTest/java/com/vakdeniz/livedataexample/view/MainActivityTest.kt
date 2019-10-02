package com.vakdeniz.livedataexample.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vakdeniz.livedataexample.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun isSwipeLayoutDisplayed() {
        onView(withId(R.id.swipeRefresh)).check(matches(isDisplayed()))
    }

    @Test
    fun isRecyclerViewDisplayed(){
        onView(withId(R.id.blogRecyclerView)).check(matches(isDisplayed()))
    }


}