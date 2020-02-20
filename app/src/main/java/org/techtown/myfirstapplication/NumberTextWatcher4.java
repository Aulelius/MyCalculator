package org.techtown.myfirstapplication;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.ParseException;


public class NumberTextWatcher4 implements TextWatcher {

    private DecimalFormat df4;
    private DecimalFormat dfnd4;
    private boolean hasFractionPart4;

    private EditText number4;

    public NumberTextWatcher4(EditText number4) {
        df4 = new DecimalFormat("#,###.##");
        df4.setDecimalSeparatorAlwaysShown(true);
        dfnd4 = new DecimalFormat("#,###");
        this.number4 = number4;
        hasFractionPart4 = false;
    }


    @SuppressWarnings("unused")
    private static final String TAG4 = "NumberTextWatcher4";

    public void afterTextChanged(Editable s4) {
        number4.removeTextChangedListener(this);

        try {
            int inilen4, endlen4;
            inilen4 = number4.getText().length();
            String v4 = s4.toString().replace(String.valueOf(df4.getDecimalFormatSymbols().getGroupingSeparator()), "");
            Number n4 = df4.parse(v4);
            int cp4 = number4.getSelectionStart();
            if (hasFractionPart4) {
                number4.setText(df4.format(n4));
            } else {
                number4.setText(dfnd4.format(n4));
            }
            endlen4 = number4.getText().length();

            int sel4 = (cp4 + (endlen4 - inilen4));
            if (sel4 > 0 && sel4 <= number4.getText().length()) {
                number4.setSelection(sel4);
            } else {
                number4.setSelection(number4.getText().length() - 1);
            }

        } catch (NumberFormatException nfe4) {

        } catch (ParseException e) {
        }

        number4.addTextChangedListener(this);

    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().contains(String.valueOf(df4.getDecimalFormatSymbols().getDecimalSeparator()))) {
            hasFractionPart4 = true;
        } else {
            hasFractionPart4 = false;
        }
    }
}
