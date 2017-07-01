package com.example.didgu.money_keeping;

import android.app.DialogFragment;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by didgu on 2017-06-30.
 */

// Fragment for adding a new entry of Expenditure
public class AddEntryFragment extends Fragment {

    // RootView that the Fragment is in
    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_newentry, container, false);

        EditText dateEditText = (EditText) rootView.findViewById(R.id.expenditure_date);

        // Sets onClickListener for date field click
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pop up a DatePickerDialog then format
                DialogFragment newFragment = new DatePickerFragment();

                // Gets the current Activity it is in and shows the DatePicker
                newFragment.show(getActivity().getFragmentManager(), "Date Picker");
            }
        });

        Button addButton = (Button) rootView.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                try {
                    addExpend();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        return rootView;
    }

    /**
     *
     * Receives information from EditText fields and creates a new instantiation of
     * Expenditure class
     * @throws ParseException
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addExpend() throws ParseException {
        String name = ((EditText) rootView.findViewById(R.id.expenditure_name)).getText().toString();

        String amount_str = ((EditText) rootView.findViewById(R.id.money_amount)).getText().toString();
        float amount = Float.parseFloat(amount_str);

        // parses and formats the date and converts into Date class
        String date_str = ((EditText) rootView.findViewById(R.id.expenditure_date)).getText().toString();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date date = df.parse(date_str);

        EditText desc_field = (EditText) rootView.findViewById(R.id.description);
        String description = desc_field.getText().toString();

        // Add to the ArrayList
        Expenditure newExpenditure = Expenditure.createExpenditure(name, amount, date, description);

        /*
        Convert float amount to String of currency
         */
        //NumberFormat form = NumberFormat.getCurrencyInstance();
        //String temp = form.format(amount);
    }

}