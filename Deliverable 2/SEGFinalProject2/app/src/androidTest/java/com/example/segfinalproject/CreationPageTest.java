package com.example.segfinalproject;

import android.widget.TextView;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class CreationPageTest {

    @Rule
    public ActivityTestRule<CreateAccount> mActivityTestRule= new ActivityTestRule<>(CreateAccount.class);
    private CreateAccount mActivity = null;

    @Before
    public void setUp() throws Exception{
        mActivity=mActivityTestRule.getActivity();
    }

    //tests the toast message when nothing is entered in any text fields
    @Test
    public void nothingEntered(){
        onView(withId(R.id.createAccount)).perform(click());
        onView(withText("Please enter all information")).inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    //tests the toast message when the email entered is invalid
    @Test
    public void emailIsInvalid(){
        onView(withId(R.id.username)).perform(typeText("user"), closeSoftKeyboard());
        onView(withId(R.id.useremail)).perform(typeText("email@"), closeSoftKeyboard());
        onView(withId(R.id.userpassword)).perform(typeText("testing123"), closeSoftKeyboard());
        onView(withId(R.id.createAccount)).perform(click());
        onView(withText("Please enter a valid email address")).inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    //tests the toast message when the password entered is under 6 characters
    @Test
    public void passwordIsInvalid(){
        onView(withId(R.id.username)).perform(typeText("user"), closeSoftKeyboard());
        onView(withId(R.id.useremail)).perform(typeText("email@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.userpassword)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.createAccount)).perform(click());
        onView(withText("Password should be at least 6 characters long")).inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

}
