package org.techtown.myfirstapplication;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.ParseException;

public class NumberTextWatcher3 implements TextWatcher {

    private DecimalFormat df3;
    private DecimalFormat dfnd3;
    private boolean hasFractionalPart3;

    private EditText number3;

    public NumberTextWatcher3(EditText number3)
    {
        df3 = new DecimalFormat("#,###.##");
        df3.setDecimalSeparatorAlwaysShown(true);
        dfnd3 = new DecimalFormat("#,###");
        this.number3 = number3;
        hasFractionalPart3 = false;
    }

    @SuppressWarnings("unused")
    private static final String TAG3 = "NumberTextWatcher3";

    public void afterTextChanged(Editable s3)
    {
        number3.removeTextChangedListener(this);

        try {
            int inilen3, endlen3;
            inilen3 = number3.getText().length();

            String v3 = s3.toString().replace(String.valueOf(df3.getDecimalFormatSymbols().getGroupingSeparator()), "");
            Number n = df3.parse(v3);
            int cp3 = number3.getSelectionStart();
            if (hasFractionalPart3) {
                number3.setText(df3.format(n));
            } else {
                number3.setText(dfnd3.format(n));
            }
            endlen3 = number3.getText().length();
            int sel = (cp3 + (endlen3 - inilen3));
            if (sel > 0 && sel <= number3.getText().length()) {
                number3.setSelection(sel);
            } else {
                // place cursor at the end?
                number3.setSelection(number3.getText().length() - 1);
            }
        } catch (NumberFormatException nfe) {
            // do nothing?
        } catch (ParseException e) {
            // do nothing?
        }

        number3.addTextChangedListener(this);
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {
    }

    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        if (s.toString().contains(String.valueOf(df3.getDecimalFormatSymbols().getDecimalSeparator())))
        {
            hasFractionalPart3 = true;
        } else {
            hasFractionalPart3 = false;
        }
    }

}


