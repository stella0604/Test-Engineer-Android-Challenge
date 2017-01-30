package com.tunein.diceroller.libraries;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.view.View;
import android.widget.TextView;

import com.tunein.diceroller.R;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.hamcrest.Matcher;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.String.valueOf;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by Stella on 1/29/17.
 */

public class RollDiceHelper {

    public static void openSettings(){
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Settings"), isDisplayed()));
        appCompatTextView.perform(click());
    }

    public static void closeSettings(){
        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.action_done), withText("Done"), withContentDescription("Done"), isDisplayed()));
        actionMenuItemView.perform(click());
    }

    public static void selectNumberofDice(int diceNumber){
        if (diceNumber>5 || diceNumber <=0){
            new AssertionFailedError("::: The number of dices is limited to 5. The current Input is out of range");
            Assert.fail();
        }
        else{
            ViewInteraction customEditText = onView(
                    allOf(withClassName(is("android.widget.NumberPicker$CustomEditText")),
                            withParent(withId(R.id.dice_number_picker)),
                            isDisplayed()));
            customEditText.perform(clearText()
                    , typeText(valueOf(diceNumber))
                    , closeSoftKeyboard());
        }
    }

    public static void selectDiceUpperRange(int diceUpperRange){
        if (diceUpperRange>12 || diceUpperRange <=0){
            new AssertionFailedError("::: The dice uppper range is limited to 12. The current Input is out of range");
            Assert.fail();
        }
        else{
            ViewInteraction customEditText = onView(
                    allOf(withClassName(is("android.widget.NumberPicker$CustomEditText")),
                            withParent(withId(R.id.dice_range_picker)),
                            isDisplayed()));
            customEditText.perform(clearText()
                    , typeText(valueOf(diceUpperRange))
                    , closeSoftKeyboard());
        }
    }

    public static void rollDices(){
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab), isDisplayed()));
        floatingActionButton.perform(click());
    }

    public static void rollingResult(int diceNumber, int diceUpperRange){
        boolean result = validateDiceResult(diceNumber, diceUpperRange);
        if (!result){
            new AssertionFailedError("Result is not as expected!");
            Assert.fail();
        }
        else{
            System.out.println("This test passed");
        }
    }

    public static boolean validateDiceResult(int diceNumber, int diceUpperRange){
        String rollResult=getText(withId(R.id.roll_result_text));
        String[] resultString = rollResult.split("\\s+");
        if (resultString.length!=diceNumber){
            //new AssertionFailedError("Error::: The number of dices rolled is not the same as number of dices defined!!!");
            System.out.println("Error::: The number of dices rolled is not the same as number of dices defined!!!");
            return false;
        }else{
            for (String result:resultString) {
                System.out.println("Result:::" + result);
                if (Integer.parseInt(result)>diceUpperRange){
                    //new AssertionFailedError("Error::: The number of dices rolled is not the same as number of dices defined!!!");
                    System.out.println("Error::: The range of dices rolled is not the same as range defined!!!");
                    return false;
                }
            }
        }
        return true;
    }

    private static String getText(final Matcher<View> matcher) {
        final String[] stringHolder = { null };
        onView(matcher).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "getting text from a TextView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView tv = (TextView)view; //Save, because of check in getConstraints()
                stringHolder[0] = tv.getText().toString();
            }
        });
        return stringHolder[0];
    }

}
