package org.techtown.myfirstapplication;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.ParseException;

public class NumberTextWatcher8 implements TextWatcher{

    private DecimalFormat df8;
    private DecimalFormat dfnd8;
    private boolean hasFractionalPart8;

    private EditText number8;

    public NumberTextWatcher8(EditText number8)
    {
        df8 = new DecimalFormat("#,###.##");
        df8.setDecimalSeparatorAlwaysShown(true);
        dfnd8 = new DecimalFormat("#,###");
        this.number8 = number8;
        hasFractionalPart8 = false;
    }

    @SuppressWarnings("unused")
    private static final String TAG8 = "NumberTextWatcher8";

    public void afterTextChanged(Editable s)
    {
        number8.removeTextChangedListener(this);

        try {
            int inilen8, endlen8;
            inilen8 = number8.getText().length();

            String v8 = s.toString().replace(String.valueOf(df8.getDecimalFormatSymbols().getGroupingSeparator()), "");
            Number n8 = df8.parse(v8);
            int cp8 = number8.getSelectionStart();
            if (hasFractionalPart8) {
                number8.setText(df8.format(n8));
            } else {
                number8.setText(dfnd8.format(n8));
            }
            endlen8 = number8.getText().length();
            int sel8 = (cp8 + (endlen8 - inilen8));
            if (sel8 > 0 && sel8 <= number8.getText().length()) {
                number8.setSelection(sel8);
            } else {
                // place cursor at the end?
                number8.setSelection(number8.getText().length() - 1);
            }
        } catch (NumberFormatException nfe) {
            // do nothing?
        } catch (ParseException e) {
            // do nothing?
        }

        number8.addTextChangedListener(this);
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {
    }

    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        if (s.toString().contains(String.valueOf(df8.getDecimalFormatSymbols().getDecimalSeparator())))
        {
            hasFractionalPart8 = true;
        } else {
            hasFractionalPart8 = false;
        }
    }
}

