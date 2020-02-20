package org.techtown.myfirstapplication;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.ParseException;

public class NumberTextWatcher implements TextWatcher {

    private DecimalFormat df;
    private DecimalFormat dfnd;
    private boolean hasFractionalPart;

    private EditText number1;

    public NumberTextWatcher(EditText number1) {
        df = new DecimalFormat("#,###.##");
        df.setDecimalSeparatorAlwaysShown(true);
        dfnd = new DecimalFormat("#,###");
        this.number1 = number1;
        hasFractionalPart = false;
    }


    private static final String TAG = "NumberTextWatcher";


    public void afterTextChanged(Editable st) {
          number1.removeTextChangedListener(this);

        try {
            int inilen, endlen;
            inilen = number1.getText().length();
            String v = st.toString().replace(String.valueOf(df.getDecimalFormatSymbols().getGroupingSeparator()), "");
            Number n = df.parse(v);
            int cp = number1.getSelectionStart();
            if (hasFractionalPart) {
                number1.setText(df.format(n));
            } else {
                number1.setText(dfnd.format(n));
            }
            endlen = number1.getText().length();

            int sel = (cp + (endlen - inilen));
            if (sel > 0 && sel <= number1.getText().length()) {
                number1.setSelection(sel);
            } else {
                number1.setSelection(sel);
            }

        } catch (NumberFormatException nfe) {
            // do Nothing?
        } catch (ParseException e) {
            // do Nothing?
        }

        number1.addTextChangedListener(this);

    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(s.toString().contains(String.valueOf(df.getDecimalFormatSymbols().getDecimalSeparator()))) {
           hasFractionalPart = true;
        } else {
            hasFractionalPart = false;
        }
    }

}