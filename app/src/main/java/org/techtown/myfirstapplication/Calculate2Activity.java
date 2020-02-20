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


// 취득당시 기준시가 (19900830 이후) 계산 자바 코드
public class Calculate2Activity extends AppCompatActivity {

    EditText number4, number5;
    TextView textResult2;
    Button calculateButton2;
    String num4, num5;
    Long Price2;


    // 취득당시의 기준시가 계산 관련 메서드 처리
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Bundle bundle = getIntent().getExtras();
        String data = bundle.get("data2").toString();


        // view binder
        number4 = (EditText) findViewById(R.id.number4);
        number5 = (EditText) findViewById(R.id.number5);
        calculateButton2 = (Button) findViewById(R.id.calculateButton2);
        textResult2 = (TextView) findViewById(R.id.result2);
        number4.addTextChangedListener(new NumberTextWatcher4(number4));
        number5.addTextChangedListener(new NumberTextWatcher5(number5));
        calculateButton2.setOnClickListener(new View.OnClickListener() {

            // 콤마추가시 String 파싱 처리
            String getCommaRemovedString(String commaString) {
                DecimalFormat myFormatter = new DecimalFormat("###,###");
                try {
                    return myFormatter.parse(commaString).toString();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return null;
            }


            // 계산 처리
            @Override
            public void onClick(View v) {

                // editText 콤마 처리
                String num4 = getCommaRemovedString(number4.getText().toString());
                String num5 = getCommaRemovedString(number5.getText().toString());


                // EditText 값이 공백일 경우
                if (num4 == null) {
                    Toast.makeText(Calculate2Activity.this, "양도(취득)당시 개별공시지가를 입력하세요", Toast.LENGTH_SHORT).show();
                } else if (num5 == null) {
                    Toast.makeText(Calculate2Activity.this, "양도(취득)면적을 입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                    // editText값이 모두 들어갔을 경우
                    long Price2 = (Long.parseLong(num4) * Long.parseLong(num5));
                    String stringvalue2 = Long.toString(Price2);
                    textResult2.setText("취득당시 기준시가 : " + getCurrencyFormat2(stringvalue2) + "원/㎡");
                }
            }

            // TextView에 콤마 처리
            public String getCurrencyFormat2(String stringvalue2) {

                NumberFormat formatter2 = NumberFormat.getCurrencyInstance();
                String formattedStringvalue2 = null;

                DecimalFormat myFormatter = new DecimalFormat("###,###");
                myFormatter.setRoundingMode(RoundingMode.DOWN);
                try {
                    Number n2 = myFormatter.parse(stringvalue2);
                    formattedStringvalue2 = myFormatter.format(n2);
                } catch (NumberFormatException nfe2) {

                } catch (ParseException e) {

                }

                return formattedStringvalue2;
            }
        });
    }

}
