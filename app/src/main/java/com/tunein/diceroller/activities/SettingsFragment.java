package com.tunein.diceroller.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import com.tunein.diceroller.R;
import com.tunein.diceroller.utilities.DiceSettings;

/**
 * Fragment to allow modifying the settings for the dice. E.g: number of dice and their upper value.
 */

public class SettingsFragment extends Fragment {

    private DiceSettings diceSettings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        diceSettings = new DiceSettings(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        prepareNumDiceNumberPicker((NumberPicker) view.findViewById(R.id.dice_number_picker));
        prepareDiceUpperValueNumberPicker((NumberPicker) view.findViewById(R.id.dice_range_picker));

        getActivity().invalidateOptionsMenu();

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_settings).setVisible(false);
        menu.findItem(R.id.action_done).setVisible(true);
    }

    private void prepareDiceUpperValueNumberPicker(NumberPicker diceRangePicker) {
        diceRangePicker.setMaxValue(DiceSettings.DICE_RANGE_MAX_UPPER_VALUE);
        diceRangePicker.setMinValue(DiceSettings.DICE_RANGE_MIN_UPPER_VALUE);
        diceRangePicker.setValue(diceSettings.getDiceUpperValue());

        diceRangePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                diceSettings.setDiceUpperValue(newVal);
            }
        });
    }

    private void prepareNumDiceNumberPicker(NumberPicker numDicePicker) {
        numDicePicker.setMaxValue(DiceSettings.NUM_DICE_MAX_VALUE);
        numDicePicker.setMinValue(DiceSettings.NUM_DICE_MIN_VALUE);
        numDicePicker.setValue(diceSettings.getNumDice());

        numDicePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                diceSettings.setNumDice(newVal);
            }
        });

    }
}
