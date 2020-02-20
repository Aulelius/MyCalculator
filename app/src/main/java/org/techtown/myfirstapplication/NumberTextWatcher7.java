package org.techtown.myfirstapplication;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.ParseException;

public class NumberTextWatcher7 implements TextWatcher {

        private DecimalFormat df7;
        private DecimalFormat dfnd7;
        private boolean hasFractionalPart7;

        private EditText number7;

        public NumberTextWatcher7(EditText number7)
        {
            df7 = new DecimalFormat("#,###.##");
            df7.setDecimalSeparatorAlwaysShown(true);
            dfnd7 = new DecimalFormat("#,###");
            this.number7 = number7;
            hasFractionalPart7 = false;
        }

        @SuppressWarnings("unused")
        private static final String TAG7 = "NumberTextWatcher7";

        public void afterTextChanged(Editable s)
        {
            number7.removeTextChangedListener(this);

            try {
                int inilen7, endlen7;
                inilen7 = number7.getText().length();

                String v7 = s.toString().replace(String.valueOf(df7.getDecimalFormatSymbols().getGroupingSeparator()), "");
                Number n7 = df7.parse(v7);
                int cp7 = number7.getSelectionStart();
                if (hasFractionalPart7) {
                    number7.setText(df7.format(n7));
                } else {
                    number7.setText(dfnd7.format(n7));
                }
                endlen7 = number7.getText().length();
                int sel7 = (cp7 + (endlen7 - inilen7));
                if (sel7 > 0 && sel7 <= number7.getText().length()) {
                    number7.setSelection(sel7);
                } else {
                    // place cursor at the end?
                    number7.setSelection(number7.getText().length() - 1);
                }
            } catch (NumberFormatException nfe) {
                // do nothing?
            } catch (ParseException e) {
                // do nothing?
            }

            number7.addTextChangedListener(this);
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            if (s.toString().contains(String.valueOf(df7.getDecimalFormatSymbols().getDecimalSeparator())))
            {
                hasFractionalPart7 = true;
            } else {
                hasFractionalPart7 = false;
            }
        }
}
