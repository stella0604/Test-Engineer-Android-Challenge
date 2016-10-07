package com.tunein.diceroller.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tunein.diceroller.R;
import com.tunein.diceroller.utilities.DiceSettings;
import com.tunein.diceroller.utilities.Roller;

import java.util.List;

/**
 * Fragment to allow rolling the dice and displaying the results.
 */

public class RollFragment extends Fragment {
    private Roller mRoller;
    private View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRoller = new Roller(new DiceSettings(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_roll, container, false);

        FloatingActionButton fab = (FloatingActionButton) mView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roll();
            }
        });

        return mView;
    }

    public void roll() {
        List<Integer> rolls = mRoller.roll();
        TextView textView = (TextView) mView.findViewById(R.id.roll_result_text);

        textView.setVisibility(View.VISIBLE);
        textView.setText(getRollText(rolls));
    }

    /**
     * Quick and dirty method of displaying building the results for display.
     */
    private String getRollText(List<Integer> results) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer roll : results) {
            stringBuilder.append(roll);
            stringBuilder.append("  ");
        }
        return stringBuilder.toString().trim();
    }
}
