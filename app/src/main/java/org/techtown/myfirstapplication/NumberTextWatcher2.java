package org.techtown.myfirstapplication;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.ParseException;

public class NumberTextWatcher2 implements TextWatcher {

    private DecimalFormat df2;
    private DecimalFormat dfnd2;
    private boolean hasFractionalPart2;

    private EditText number2;

    public NumberTextWatcher2(EditText number2)
    {
        df2 = new DecimalFormat("#,###.##");
        df2.setDecimalSeparatorAlwaysShown(true);
        dfnd2 = new DecimalFormat("#,###");
        this.number2 = number2;
        hasFractionalPart2 = false;
    }

    @SuppressWarnings("unused")
    private static final String TAG2 = "NumberTextWatcher2";

    public void afterTextChanged(Editable s2)
    {
        number2.removeTextChangedListener(this);

        try {
            int inilen2, endlen2;
            inilen2 = number2.getText().length();

            String v2 = s2.toString().replace(String.valueOf(df2.getDecimalFormatSymbols().getGroupingSeparator()), "");
            Number n2 = df2.parse(v2);
            int cp2 = number2.getSelectionStart();
            if (hasFractionalPart2) {
                number2.setText(df2.format(n2));
            } else {
                number2.setText(dfnd2.format(n2));
            }
            endlen2 = number2.getText().length();
            int sel = (cp2 + (endlen2 - inilen2));
            if (sel > 0 && sel <= number2.getText().length()) {
                number2.setSelection(sel);
            } else {
                // place cursor at the end?
                number2.setSelection(number2.getText().length() - 1);
            }
        } catch (NumberFormatException nfe) {
            // do nothing?
        } catch (ParseException e) {
            // do nothing?
        }

        number2.addTextChangedListener(this);
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {
    }

    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        if (s.toString().contains(String.valueOf(df2.getDecimalFormatSymbols().getDecimalSeparator())))
        {
            hasFractionalPart2 = true;
        } else {
            hasFractionalPart2 = false;
        }
    }


}


