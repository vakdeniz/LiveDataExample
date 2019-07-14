package com.vakdeniz.livedataexample.view

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.vakdeniz.livedataexample.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun isSwipeLayoutDisplayed() {
        onView(withId(R.id.swiperefresh)).check(matches(isDisplayed()))
    }

    fun isRecyclerViewDisplayed(){
        onView(withId(R.id.blogRecyclerView)).check(matches(isDisplayed()))
    }


}