package org.techtown.myfirstapplication;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.ParseException;

public class NumberTextWatcher5 implements TextWatcher {

    private DecimalFormat df5;
    private DecimalFormat dfnd5;
    private boolean hasFractionalPart5;

    private EditText number5;

    public NumberTextWatcher5(EditText number5)
    {
        df5 = new DecimalFormat("#,###.##");
        df5.setDecimalSeparatorAlwaysShown(true);
        dfnd5 = new DecimalFormat("#,###");
        this.number5 = number5;
        hasFractionalPart5 = false;
    }

    @SuppressWarnings("unused")
    private static final String TAG5 = "NumberTextWatcher5";

    public void afterTextChanged(Editable s5)
    {
        number5.removeTextChangedListener(this);

        try {
            int inilen5, endlen5;
            inilen5 = number5.getText().length();

            String v5 = s5.toString().replace(String.valueOf(df5.getDecimalFormatSymbols().getGroupingSeparator()), "");
            Number n5 = df5.parse(v5);
            int cp5 = number5.getSelectionStart();
            if (hasFractionalPart5) {
                number5.setText(df5.format(n5));
            } else {
                number5.setText(dfnd5.format(n5));
            }
            endlen5 = number5.getText().length();
            int sel5 = (cp5 + (endlen5 - inilen5));
            if (sel5 > 0 && sel5 <= number5.getText().length()) {
                number5.setSelection(sel5);
            } else {
                // place cursor at the end?
                number5.setSelection(number5.getText().length() - 1);
            }
        } catch (NumberFormatException nfe) {
            // do nothing?
        } catch (ParseException e) {
            // do nothing?
        }

        number5.addTextChangedListener(this);
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {
    }

    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        if (s.toString().contains(String.valueOf(df5.getDecimalFormatSymbols().getDecimalSeparator())))
        {
            hasFractionalPart5 = true;
        } else {
            hasFractionalPart5 = false;
        }
    }
}
