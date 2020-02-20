package org.techtown.myfirstapplication;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.ParseException;

public class NumberTextWatcher6 implements TextWatcher {

private DecimalFormat df6;
private DecimalFormat dfnd6;
private boolean hasFractionalPart6;

private EditText number6;

public NumberTextWatcher6(EditText number6) {
    df6 = new DecimalFormat("#,###.##");
    df6.setDecimalSeparatorAlwaysShown(true);
    dfnd6 = new DecimalFormat("#,###");
    this.number6 = number6;
    hasFractionalPart6 = false;
}

    @SuppressWarnings("unused")
    private static final String TAG6 = "NumberTextWatcher6";

    public void afterTextChanged(Editable s)
    {
        number6.removeTextChangedListener(this);

        try {
            int inilen6, endlen6;
            inilen6 = number6.getText().length();

            String v6 = s.toString().replace(String.valueOf(df6.getDecimalFormatSymbols().getGroupingSeparator()), "");
            Number n6 = df6.parse(v6);
            int cp6 = number6.getSelectionStart();
            if (hasFractionalPart6) {
                number6.setText(df6.format(n6));
            } else {
                number6.setText(dfnd6.format(n6));
            }
            endlen6 = number6.getText().length();
            int sel6 = (cp6 + (endlen6 - inilen6));
            if (sel6 > 0 && sel6 <= number6.getText().length()) {
                number6.setSelection(sel6);
            } else {
                // place cursor at the end?
                number6.setSelection(number6.getText().length() - 1);
            }
        } catch (NumberFormatException nfe) {
            // do nothing?
        } catch (ParseException e) {
            // do nothing?
        }

        number6.addTextChangedListener(this);
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {
    }

    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        if (s.toString().contains(String.valueOf(df6.getDecimalFormatSymbols().getDecimalSeparator())))
        {
            hasFractionalPart6 = true;
        } else {
            hasFractionalPart6 = false;
        }
    }
}