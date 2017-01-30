package com.tunein.diceroller.activities;

/**
 * This group of tests are for positive valid testing to verify when inputs are valid, whether the results are expected.
 * Created by Stella on 1/29/17.
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static com.tunein.diceroller.libraries.RollDiceHelper.openSettings;
import static com.tunein.diceroller.libraries.RollDiceHelper.closeSettings;
import static com.tunein.diceroller.libraries.RollDiceHelper.selectDiceUpperRange;
import static com.tunein.diceroller.libraries.RollDiceHelper.selectNumberofDice;
import static com.tunein.diceroller.libraries.RollDiceHelper.rollDices;
import static com.tunein.diceroller.libraries.RollDiceHelper.rollingResult;

@RunWith(AndroidJUnit4.class)
public class DiceGameActivityTest {

    @Rule
    public ActivityTestRule<DiceGameActivity> mActivityTestRule = new ActivityTestRule<>(DiceGameActivity.class);

    @Test
    public void diceGameActivityTest_Max() {
        //Limit of number of dices is 5, limit of dice range is 12
        int diceNumber = 5;
        int diceUpperRange = 12;
        openSettings();
        selectNumberofDice(diceNumber);
        //SystemClock.sleep(1000);
        selectDiceUpperRange(diceUpperRange);
        //SystemClock.sleep(1000);
        closeSettings();
        rollDices();
        rollingResult(diceNumber, diceUpperRange);
    }


    @Test
    public void diceGameActivityTest_Min() {
        //Limit of number of dices is 5, limit of dice range is 12
        int diceNumber = 1;
        int diceUpperRange = 1;
        openSettings();
        selectNumberofDice(diceNumber);
        //SystemClock.sleep(1000);
        selectDiceUpperRange(diceUpperRange);
        //SystemClock.sleep(1000);
        closeSettings();
        rollDices();
        rollingResult(diceNumber, diceUpperRange);
    }


    @Test
    public void diceGameActivityTest_OutOfRange1() {
        //Limit of number of dices is 5, limit of dice range is 12
        int diceNumber = 6;
        int diceUpperRange = 10;
        openSettings();
        selectNumberofDice(diceNumber);
        //SystemClock.sleep(1000);
        selectDiceUpperRange(diceUpperRange);
        //SystemClock.sleep(1000);
        closeSettings();
        rollDices();
        rollingResult(diceNumber, diceUpperRange);
    }

    @Test
    public void diceGameActivityTest_OutOfRange2() {
        //Limit of number of dices is 5, limit of dice range is 12
        int diceNumber = 4;
        int diceUpperRange = 13;
        openSettings();
        selectNumberofDice(diceNumber);
        //SystemClock.sleep(1000);
        selectDiceUpperRange(diceUpperRange);
        //SystemClock.sleep(1000);
        closeSettings();
        rollDices();
        rollingResult(diceNumber, diceUpperRange);
    }

    @Test
    public void diceGameActivityTest_OutOfRange3() {
        //Limit of number of dices is 5, limit of dice range is 12
        int diceNumber = -1;
        int diceUpperRange = 13;
        openSettings();
        selectNumberofDice(diceNumber);
        //SystemClock.sleep(1000);
        selectDiceUpperRange(diceUpperRange);
        //SystemClock.sleep(1000);
        closeSettings();
        rollDices();
        rollingResult(diceNumber, diceUpperRange);
    }
}
