package org.techtown.myfirstapplication;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.ParseException;

public class NumberTextWatcher10 implements TextWatcher {

    private DecimalFormat df10;
    private DecimalFormat dfnd10;
    private boolean hasFractionalPart10;

    private EditText number10;

    public NumberTextWatcher10(EditText number10){
        df10 = new DecimalFormat("#,###.##");
        df10.setDecimalSeparatorAlwaysShown(true);
        dfnd10 = new DecimalFormat("#,###");
        this.number10 = number10;
        hasFractionalPart10 = false;
    }

    private static final String TAG10 = "NumberTextWatcher10";

    public void afterTextChanged(Editable s) {
        number10.removeTextChangedListener(this);

        try {
            int inilen10, endlen10;
            inilen10 = number10.getText().length();

            String v10 = s.toString().replace(String.valueOf(df10.getDecimalFormatSymbols().getGroupingSeparator()), "");
            Number n10 = df10.parse(v10);
            int cp10 = number10.getSelectionStart();
            if (hasFractionalPart10) {
                number10.setText(df10.format(n10));
            } else {
                number10.setText(dfnd10.format(n10));
            }
            endlen10 = number10.getText().length();
            int sel10 = (cp10 + (endlen10 - inilen10));
            if (sel10 > 0 && sel10 <= number10.getText().length()) {
                number10.setSelection(sel10);
            } else {
                number10.setSelection(number10.getText().length() -1);
            }
        }  catch(NumberFormatException nfe) {
            // do nothing?
        } catch(ParseException e) {
            // do nothing?
        }
        number10.addTextChangedListener(this);
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    public void onTextChanged(CharSequence s,int start, int before, int count) {
        if(s.toString().contains(String.valueOf(df10.getDecimalFormatSymbols().getDecimalSeparator()))) {
            hasFractionalPart10 = true;
        } else {
            hasFractionalPart10 = false;
        }
    }
}
