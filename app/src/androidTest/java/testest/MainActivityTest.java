package testest;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.example.lenovo.testunitsample.MainActivity;
import com.example.lenovo.testunitsample.R;
import com.example.lenovo.testunitsample.SecondActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    public MainActivity mainActivity;

    private String mStringToBetyped;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(SecondActivity.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        mainActivity = mainTestRule.getActivity();
        mStringToBetyped = "Write something";
    }

    @Test
    public void testLaunch(){

        View button = mainActivity.findViewById(R.id.button);
        assertNotNull(button);

        onView(withId(R.id.button)).check(matches(allOf(withId(R.id.button), withText("BUTTON"))));

        waitPlease();
        onView(withId(R.id.editText)).perform(typeText(mStringToBetyped), closeSoftKeyboard());

        waitPlease();
        onView(withId(R.id.button)).perform(click());

        waitPlease();
        onView(withId(R.id.textView)).check(matches(withText(mStringToBetyped)));


    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }

    public void waitPlease(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}