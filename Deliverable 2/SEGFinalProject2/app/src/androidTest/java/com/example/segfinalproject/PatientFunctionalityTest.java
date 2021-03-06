package com.example.segfinalproject;

import android.os.SystemClock;
import android.widget.TextView;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class PatientFunctionalityTest {

    @Rule
    public ActivityTestRule<CreateAccount> mActivityTestRule= new ActivityTestRule<>(CreateAccount.class);
    private CreateAccount mActivity = null;

    @Before
    public void setUp() throws Exception{
        mActivity=mActivityTestRule.getActivity();
    }

    //tests the toast messages and searches for a clinic
    //NOTE: the employee creation clinic test must be run before this in order for the patient to properly search for a clinic
    @Test
    public void createClinic(){
        onView(withId(R.id.username)).perform(typeText("user2"), closeSoftKeyboard());
        onView(withId(R.id.useremail)).perform(typeText("user2@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.userpassword)).perform(typeText("123123"), closeSoftKeyboard());
        onView(withId(R.id.usertype)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Patient"))).perform(click());
        onView(withId(R.id.createAccount)).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.searchButton)).perform(click());
        onView(withId(R.id.addressText)).perform(typeText("Sample Address 1234"), closeSoftKeyboard());
        onView(withId(R.id.addressButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.ClinicResults)).atPosition(0).perform(longClick());
    }

}
