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

public class Calculate4Activity extends AppCompatActivity {

    TextView textResult4; // 계산된 결과값을 저장하는 변수
    EditText number10; // 계산하기 위한 값들
    Button calculateButton4; // 버튼
    Long Price4;

    // 필요경비 계산 관련 메서드 처리

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        Bundle bundle = getIntent().getExtras();
        String data = bundle.get("data4").toString();

        // View binding
        number10 = (EditText)findViewById(R.id.number10);

        number10.addTextChangedListener(new NumberTextWatcher10(number10));

        calculateButton4 = (Button)findViewById(R.id.calculateButton4);

        textResult4 = (TextView) findViewById(R.id.result8);

        calculateButton4.setOnClickListener(new View.OnClickListener() {

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

            @Override
            public void onClick(View v) {
            // 계산 메서드
            // EditText의 값 콤마 출력 메서드
            String num10 = getCommaRemovedString(number10.getText().toString());

            // 값이 공백일 경우 처리
            if(num10 == null) {
                Toast.makeText(Calculate4Activity.this, "취득당시 기준시가를 입력하세요", Toast.LENGTH_SHORT).show();
            } else {
                // 모든 값이 입력되었을 때 버튼을 누를 시 계산을 하도록 한다.
                long longvalue8 = (Long.parseLong(num10));
                String stringResult8 = Long.toString((longvalue8 * 3) /100);
                textResult4.setText("필요경비 : " + getCurrencyFormat4(stringResult8)  + "원");

            }

            }


            // TextView 콤마 메서드 처리
            public String getCurrencyFormat4(String stringvalue4) {

                NumberFormat formatter = NumberFormat.getCurrencyInstance();
                String formattedStringResult4 = null;

                DecimalFormat myformatter = new DecimalFormat("###,###");
                myformatter.setRoundingMode(RoundingMode.DOWN); // 소수점 3째자리 내림
                try {
                    Number n = myformatter.parse(stringvalue4);
                    formattedStringResult4 = myformatter.format(n);
                } catch (NumberFormatException nfe4) {

                } catch (ParseException e) {

                }
                return formattedStringResult4;
            }

        });
    }


}

