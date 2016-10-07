package com.tunein.diceroller.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Roll api which returns a set of random roll results based on the provided settings.
 */
public class Roller {
    private final DiceSettings mDiceSettings;
    private final Random mRandom;

    public Roller(DiceSettings diceSettings) {
        mDiceSettings = diceSettings;
        mRandom = new Random();
    }

    public List<Integer> roll() {
        int numDice = mDiceSettings.getNumDice();
        int upperValue = mDiceSettings.getDiceUpperValue();

        List<Integer> rollResults = new ArrayList<>(numDice);

        for (int i = 0; i < numDice; i++) {
            int rollResult = mRandom.nextInt(upperValue) + 1;
            rollResults.add(rollResult);
        }

        return rollResults;
    }
}
