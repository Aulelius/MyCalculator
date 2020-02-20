package org.techtown.myfirstapplication;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.ParseException;

public class NumberTextWatcher9 implements TextWatcher{


    private DecimalFormat df9;
    private DecimalFormat dfnd9;
    private boolean hasFractionalPart9;

    private EditText number9;

    public NumberTextWatcher9(EditText number9)
    {
        df9 = new DecimalFormat("#,###.##");
        df9.setDecimalSeparatorAlwaysShown(true);
        dfnd9 = new DecimalFormat("#,###");
        this.number9 = number9;
        hasFractionalPart9 = false;
    }

    @SuppressWarnings("unused")
    private static final String TAG9 = "NumberTextWatcher9";

    public void afterTextChanged(Editable s)
    {
        number9.removeTextChangedListener(this);

        try {
            int inilen9, endlen9;
            inilen9 = number9.getText().length();

            String v9 = s.toString().replace(String.valueOf(df9.getDecimalFormatSymbols().getGroupingSeparator()), "");
            Number n9 = df9.parse(v9);
            int cp9 = number9.getSelectionStart();
            if (hasFractionalPart9) {
                number9.setText(df9.format(n9));
            } else {
                number9.setText(dfnd9.format(n9));
            }
            endlen9 = number9.getText().length();
            int sel9 = (cp9 + (endlen9 - inilen9));
            if (sel9 > 0 && sel9 <= number9.getText().length()) {
                number9.setSelection(sel9);
            } else {
                // place cursor at the end?
                number9.setSelection(number9.getText().length() - 1);
            }
        } catch (NumberFormatException nfe) {
            // do nothing?
        } catch (ParseException e) {
            // do nothing?
        }

        number9.addTextChangedListener(this);
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {
    }

    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        if (s.toString().contains(String.valueOf(df9.getDecimalFormatSymbols().getDecimalSeparator())))
        {
            hasFractionalPart9 = true;
        } else {
            hasFractionalPart9 = false;
        }
    }
}