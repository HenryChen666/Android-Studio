package com.example.segfinalproject;

import android.os.SystemClock;
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

public class EmployeeCreateClinicTest {

    @Rule
    public ActivityTestRule<CreateAccount> mActivityTestRule= new ActivityTestRule<>(CreateAccount.class);
    private CreateAccount mActivity = null;

    @Before
    public void setUp() throws Exception{
        mActivity=mActivityTestRule.getActivity();
    }

    //tests the toast messages and creates a clinic
    @Test
    public void createClinic(){
        onView(withId(R.id.username)).perform(typeText("user1"), closeSoftKeyboard());
        onView(withId(R.id.useremail)).perform(typeText("testuser1@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.userpassword)).perform(typeText("123123"), closeSoftKeyboard());
        onView(withId(R.id.createAccount)).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.editprofile)).perform(click());
        onView(withId(R.id.address)).perform(typeText("Sample Address 1234"), closeSoftKeyboard());
        onView(withId(R.id.phonenumber)).perform(typeText("1231231234"), closeSoftKeyboard());
        onView(withId(R.id.nextbtn)).perform(click());
        onView(withText("Please enter all information")).inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(2000);
        onView(withId(R.id.clinicname)).perform(typeText("My Test Clinic123"), closeSoftKeyboard());
        onView(withId(R.id.nextbtn)).perform(click());
        SystemClock.sleep(2000);
    }

}
