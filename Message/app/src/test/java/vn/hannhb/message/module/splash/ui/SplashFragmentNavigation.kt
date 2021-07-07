package vn.hannhb.message.module.splash.ui

import android.os.Build
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import vn.hannhb.message.MainActivity
import vn.hannhb.message.R

/**
 * Please comment initZomInOrOutImageView() in Application to test some cases bellow
 */

@RunWith(RobolectricTestRunner::class)
@LargeTest
class SplashFragmentNavigation {

    @Test
    fun test_splashScreen_open_messageScreen() {
        GlobalScope.launch(context = Dispatchers.Main) {
            delay(1000L)
            Espresso.onView(withId(R.id.message_fragment))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, true, false)

    @Test()
    fun messageFragment_backButton() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        GlobalScope.launch(context = Dispatchers.Main) {
            delay(1000L)
            Espresso.onView(withId(R.id.message_fragment))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
        pressBack()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Assert.assertEquals(activityRule.activity, null)
        }
        activityScenario.close()
    }
}
