package com.javatar.portafolio.features

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.javatar.portafolio.R
import com.javatar.portafolio.adapters.HeaderViewHolder
import kotlinx.android.synthetic.main.fragment_recycler_general.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    @get:Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivity_Experience() {
        selectTabByName("EXPERIENCIA")

        var itemCount: Int = mActivityTestRule.activity.recycler.adapter?.itemCount?.minus(1) ?: 0

        for (value in 0..itemCount) {

            scrollRecyclerByPosition(R.id.recycler, value)
            if (mActivityTestRule.activity.recycler.findViewHolderForAdapterPosition(value) !is HeaderViewHolder) {
                clickItemRecycler(R.id.recycler, value)
                scrollRecyclerEnd(R.id.recycler_features)
                backToolbar()
            }
        }
    }

    @Test
    fun mainActivity_Aptitude() {
        selectTabByName("APTITUDES")
        scrollRecyclerEnd(R.id.recycler)
    }

    @Test
    fun mainActivity_Projects() {

        selectTabByName("PROYECTOS")

        for (value in 0..8) {
            scrollRecyclerByPosition(R.id.recycler, value)
            if (mActivityTestRule.activity.recycler.findViewHolderForAdapterPosition(value) !is HeaderViewHolder) {
                clickItemRecycler(R.id.recycler, value)
                scrollRecyclerEnd(R.id.recycler_features)
                scrollRecyclerEnd(R.id.recycler_images)
                backToolbar()
            }
        }

    }


    private fun selectTabByName(name: String) {
        onView(
            allOf(
                withText(name),
                isDescendantOfA(
                    withId(R.id.tabLayout)
                )
            )
        ).perform(click())
    }

    private fun scrollRecyclerByPosition(idRecycler: Int, position: Int) {
        onView(
            allOf(
                isDisplayed(),
                withId(idRecycler)
            )
        ).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                scrollTo()
            )
        )
    }

    private fun scrollRecyclerEnd(idRecycler: Int) {
        onView(
            allOf(
                isDisplayed(),
                withId(idRecycler)
            )
        ).perform(ScrollToBottomAction())

    }

    private fun clickItemRecycler(idRecycler: Int, position: Int) {

        onView(allOf(isDisplayed(), withId(idRecycler))).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click())
        )
    }

    private fun backToolbar() {
        onView(
            childAtPosition(
                allOf(withId(R.id.toolbar)), 1
            )
        ).perform(click())
    }

    protected fun childAtPosition(parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
            }
        }

    }

    class ScrollToBottomAction : ViewAction {

        override fun getDescription(): String {
            return "scroll RecyclerView to bottom"
        }

        override fun getConstraints(): Matcher<View> {
            return allOf<View>(isAssignableFrom(RecyclerView::class.java), isDisplayed())
        }

        override fun perform(uiController: UiController?, view: View?) {
            val recyclerView = view as RecyclerView
            val itemCount = recyclerView.adapter?.itemCount
            val position = itemCount?.minus(1) ?: 0
            recyclerView.scrollToPosition(position)
            uiController?.loopMainThreadUntilIdle()
        }
    }
}