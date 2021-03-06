package com.doapps.habits.activity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.doapps.habits.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ProgramsAddTest {

  @Rule
  public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
      new ActivityTestRule<>(MainActivity.class);

  @Test
  public void doubleAddProgram() {
    onView(withId(R.id.drawer_layout)).perform(open());
    onView(withText(R.string.programs)).perform(click());
    onView(withText("Running")).perform(click());
    onView(withId(R.id.fab)).perform(click());
    onView(withId(R.id.fab)).perform(click());
    onView(withId(R.id.drawer_layout)).perform(open());
    onView(withText(R.string.list)).perform(click());
    onView(withId(R.id.habits_list))
        .perform(RecyclerViewActions.actionOnItemAtPosition(0, swipeRight()));
  }

}