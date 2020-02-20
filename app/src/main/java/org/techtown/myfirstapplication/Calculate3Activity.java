package org.techtown.myfirstapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;


// 취득당시 기준시가 (19900830 이전) 계산 자바 코드
public class Calculate3Activity extends AppCompatActivity {


    EditText number6, number7, number8, number9;
    TextView textResult3;
    Button calculateButton3;
    String num6, num7, num8, num9;
    Long Price3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Bundle bundle = getIntent().getExtras();
        String data = bundle.get("data3").toString();


        // View binder
        number6 = (EditText) findViewById(R.id.number6);
        number7 = (EditText) findViewById(R.id.number7);
        number8 = (EditText) findViewById(R.id.number8);
        number9 = (EditText) findViewById(R.id.number9);
        calculateButton3 = (Button) findViewById(R.id.calculateButton3);
        textResult3 = (TextView) findViewById(R.id.result3);

        number6.addTextChangedListener(new NumberTextWatcher6(number6));
        number7.addTextChangedListener(new NumberTextWatcher7(number7));
        number8.addTextChangedListener(new NumberTextWatcher8(number8));
        number9.addTextChangedListener(new NumberTextWatcher9(number9));


        calculateButton3.setOnClickListener(new View.OnClickListener() {

            // 콤마 추가시 String 파싱 처리
            String getCommaRemovedString(String commaString) {
                DecimalFormat myFormatter = new DecimalFormat("###,###");
                try {
                    return myFormatter.parse(commaString).toString();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return null;
            }


            @Override
            public void onClick(View v) {

                // 계산 메서드


                // EditText 값 콤마 출력
                String num6 = getCommaRemovedString(number6.getText().toString());
                String num7 = getCommaRemovedString(number7.getText().toString());
                String num8 = getCommaRemovedString(number8.getText().toString());
                String num9 = getCommaRemovedString(number9.getText().toString());


                // editText의 값이 공백일 경우 처리
                if (num6 == null) {
                    Toast.makeText(Calculate3Activity.this, "1990.1.1 기준 공시지가를 입력하세요", Toast.LENGTH_SHORT).show();
                } else if (num7 == null) {
                    Toast.makeText(Calculate3Activity.this, "취득당시의 토지등급가액을 입력하세요", Toast.LENGTH_SHORT).show();
                } else if (num8 == null) {
                    Toast.makeText(Calculate3Activity.this, "1990.08.30 기준 토지등급가액을 입력하세요", Toast.LENGTH_SHORT).show();
                } else if (num9 == null) {
                    Toast.makeText(Calculate3Activity.this, "그 직전에 결정된 토지등급가액을 입력하세요", Toast.LENGTH_SHORT).show();
                    // 모든 editText의 값이 입력된 경우 계산 메서드
                } else {
                    long Price3 = (Long.parseLong(num6) * Long.parseLong(num7) / ((Long.parseLong(num8) + Long.parseLong(num9)) / 2));
                    String stringvalue3 = Long.toString(Price3);
                    textResult3.setText("취득당시 기준시가 : " + getCurrencyFormat3(stringvalue3) + "원/㎡");

                }
            }


            // TextView 콤마 처리 메서드
            public String getCurrencyFormat3(String stringvalue3) {

                NumberFormat formatter3 = NumberFormat.getCurrencyInstance();
                String formattedStringvalue3 = null;

                DecimalFormat myFormatter3 = new DecimalFormat("###,###");
                myFormatter3.setRoundingMode(RoundingMode.DOWN); // 소수점 3째짜리 내림
                try {
                    Number n3 = myFormatter3.parse(stringvalue3);
                    formattedStringvalue3 = myFormatter3.format(n3);
                } catch (NumberFormatException nfe2) {

                } catch (ParseException e) {

                }

                return formattedStringvalue3;
            }
        });


    }
}
