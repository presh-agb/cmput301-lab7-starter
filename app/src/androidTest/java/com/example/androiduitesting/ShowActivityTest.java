package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.is;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    // test 1: check if activity switches correctly
    @Test
    public void testClickingCityOpensNewScreen() {
        addNewCity("Paris");

        // click on the first city in the list
        onData(anything())
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // check if we see elements from ShowActivity
        onView(withId(R.id.button_back)).check(matches(isDisplayed()));
        onView(withId(R.id.text_city_name)).check(matches(isDisplayed()));
    }

    // test 2: check if the correct city name is consistent
    @Test
    public void testCorrectCityNameIsShown() {
        addNewCity("London");

        onData(anything())
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        onView(withId(R.id.text_city_name)).check(matches(withText("London")));
    }

    // test 3: back button returns to main screen
    @Test
    public void testBackButtonReturnsToMain() {
        // add a city and click
        addNewCity("Tokyo");

        onData(anything())
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // click the back button
        onView(withId(R.id.button_back)).perform(click());

        // check if back in MainActivity as intended
        onView(withId(R.id.button_add)).check(matches(isDisplayed()));
        onView(withId(R.id.button_clear)).check(matches(isDisplayed()));
    }

    // helper method to add a new city
    private void addNewCity(String cityName) {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name))
            .perform(androidx.test.espresso.action.ViewActions.typeText(cityName),
                androidx.test.espresso.action.ViewActions.closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());
    }

}