package com.doapps.habits.activity;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.doapps.habits.activity.TestUtilsKt.removeFirstHabit;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.doapps.habits.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

  @Rule
  public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
      new ActivityTestRule<>(MainActivity.class);

  @Test
  public void createAndDeleteHabit() {
    onView(withId(R.id.fab)).perform(click());
    onView(withId(R.id.edit_title)).perform(typeText("Running"));
    onView(withId(R.id.edit_time)).perform(typeText("50"));
    onView(withId(R.id.edit_time)).perform(pressImeActionButton());
    closeSoftKeyboard();
    onView(withId(R.id.btnCreate)).perform(click());
    onView(withId(R.id.habits_list)).check(matches(isDisplayed()));
    onView(withId(R.id.toolbar_search)).check(matches(isDisplayed()));
    onView(withId(R.id.drawer_layout)).perform(open());
    onView(withText("Home")).perform(click());
    onView(withText("Running")).check(matches(isDisplayed()));
    removeFirstHabit();
    onView(withId(R.id.empty_view)).check(matches(isDisplayed()));
    onView(withId(R.id.drawer_layout)).perform(open());
    onView(withText("Home")).perform(click());
    onView(withId(R.id.drawer_layout)).perform(open());
    onView(withText("List")).perform(click());
    onView(withId(R.id.empty_view)).check(matches(isDisplayed()));
  }

}