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


// 환산 취득가액 계산 자바 코드
public class Calculate1Activity extends AppCompatActivity {

    TextView textResult;   // 계산된 결과 값을 저장하는 변수
    EditText number1, number2, number3; // 계산하기 위한 값들
    Button calculateButton; // 버튼
    Long Price;
    // 환산취득가액 계산 관련 메서드 처리


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
     //   String data = bundle.get("data").toString();


        // View binding
        number1 = (EditText)findViewById(R.id.number1);
        number2 = (EditText)findViewById(R.id.number2);
        number3 = (EditText)findViewById(R.id.number3);

        number1.addTextChangedListener(new NumberTextWatcher(number1));
        number2.addTextChangedListener(new NumberTextWatcher2(number2));
        number3.addTextChangedListener(new NumberTextWatcher3(number3));

        calculateButton = (Button)findViewById(R.id.calculateButton);


        textResult = (TextView)findViewById(R.id.result);


            calculateButton.setOnClickListener(new View.OnClickListener() {

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
                    public void onClick (View v){

                        // 계산 메서드
                        // editText의 값 콤마 출력 메서드
                        String num1 = getCommaRemovedString(number1.getText().toString());
                        String num2 = getCommaRemovedString(number2.getText().toString());
                        String num3 = getCommaRemovedString(number3.getText().toString());

                        // 값이 공백일 경우 처리
                        if (num1 == null) {
                            Toast.makeText(Calculate1Activity.this, "양도당시 실지거래가액을 입력하세요", Toast.LENGTH_SHORT).show();
                        } else if (num2 == null) {
                            Toast.makeText(Calculate1Activity.this, "취득당시 기준시가를 입력하세요", Toast.LENGTH_SHORT).show();
                        } else if (num3 == null) {
                            Toast.makeText(Calculate1Activity.this, " 양도당시 기준시가를 입력하세요", Toast.LENGTH_SHORT).show();
                        } else {
                            // 모든 값이 입력되었을 때 버튼을 누를 시 계산을 하도록 한다.
                            long longValue = (Long.parseLong(num1) * Long.parseLong(num2) / Long.parseLong(num3));
                            String stringResult = Long.toString(longValue);
                            textResult.setText("환산취득가액 : " + getCurrencyFormat(stringResult) + "원");

                        }


                    }

                // TextView 콤마 Method 처리
                public String getCurrencyFormat(String stringResult){

                    NumberFormat formatter = NumberFormat.getCurrencyInstance();
                    String formattedStringResult = null;

                    DecimalFormat myFormatter = new DecimalFormat("###,###");
                    myFormatter.setRoundingMode(RoundingMode.DOWN);//소수점 3째자리 내림
                    try{
                        Number n = myFormatter.parse(stringResult);
                        formattedStringResult = myFormatter.format(n);
                    } catch (NumberFormatException nfe) {

                    } catch (ParseException e) {

                    }

                    return formattedStringResult;
                }



        });


            }





    }



