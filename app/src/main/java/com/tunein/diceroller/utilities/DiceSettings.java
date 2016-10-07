package com.tunein.diceroller.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Settings class used to communicate and persist the settings for the game (number of dice and the upper range of the
 * dice). Uses {@link SharedPreferences} to store the values.
 */
public class DiceSettings {

    public static final int DICE_RANGE_MAX_UPPER_VALUE = 12;
    public static final int NUM_DICE_MAX_VALUE = 5;

    public static final int DICE_RANGE_MIN_UPPER_VALUE = 1;
    public static final int NUM_DICE_MIN_VALUE = 1;

    private static final int DEFAULT_NUM_DICE = 1;
    private static final int DEFAULT_DICE_UPPER_VALUE = 6;

    private static final String DICE_PREFS = "DICE_PREFS";
    private static final String NUM_DICE_KEY = "num_dice_key";
    private static final String DICE_UPPER_VALUE_KEY = "dice_max_range_key";

    private final Context mContext;

    public DiceSettings(Context context) {
        mContext = context;
    }

    public void setNumDice(int numDice) {
        getSharedPrefs().edit().putInt(NUM_DICE_KEY, numDice).commit();
    }

    public int getNumDice() {
        return getSharedPrefs().getInt(NUM_DICE_KEY, DEFAULT_NUM_DICE);
    }

    public void setDiceUpperValue(int diceUpperValue) {
        getSharedPrefs().edit().putInt(DICE_UPPER_VALUE_KEY, diceUpperValue).commit();
    }

    public int getDiceUpperValue() {
        return getSharedPrefs().getInt(DICE_UPPER_VALUE_KEY, DEFAULT_DICE_UPPER_VALUE);
    }

    private SharedPreferences getSharedPrefs() {
        return mContext.getSharedPreferences(DICE_PREFS, Context.MODE_PRIVATE);
    }
}
