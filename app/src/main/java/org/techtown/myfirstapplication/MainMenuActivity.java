package org.techtown.myfirstapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

// 환산취득가액 어플 메인 화면 자바 코드
public class MainMenuActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private long backBntTime = 0;

    // Main2Activity가 최초 실행될 때 실행된다.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Activity의 UI를 R.layout.activity_main2로 지정한다.
        setContentView(R.layout.activity_main2);
        Intent intent = new Intent(this, LodingActivity.class);
        startActivity(intent);

        // "환산취득가액 계산기 어플입니다." 메세지를 잠시 보여준다.
        Toast.makeText(getApplicationContext(), "환산취득가액 계산기 어플입니다. ", Toast.LENGTH_LONG).show();

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Button button = (Button) findViewById(R.id.nextbutton);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("원하는 계산을 선택하세요");
        categories.add("환산취득가액 계산");
        categories.add("취득당시 기준시가(90년대이후)");
        categories.add("취득당시 기준시가(90년대이전)");
        categories.add("개별 공시지가 조회");
        categories.add("표준 공시지가 조회");
        categories.add("환산취득가액에 필요한 경비");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinner.getSelectedItem().toString().equals("원하는 계산을 선택하세요")) {
                    Toast.makeText(MainMenuActivity.this,"원하는 계산을 선택하세요", Toast.LENGTH_SHORT).show();
                }else if(spinner.getSelectedItem().toString().equals("환산취득가액 계산")) {
                    Intent intent = new Intent(MainMenuActivity.this, Calculate1Activity.class);
                    intent.putExtra("data", String.valueOf(spinner.getSelectedItem()));
                    startActivity(intent);
                } else if(spinner.getSelectedItem().toString().equals("취득당시 기준시가(90년대이후)")) {
                    Intent intent3 = new Intent(MainMenuActivity.this, Calculate2Activity.class);
                    intent3.putExtra("data2", String.valueOf(spinner.getSelectedItem()));
                    startActivity(intent3);
                } else if(spinner.getSelectedItem().toString().equals("취득당시 기준시가(90년대이전)")) {
                    Intent intent4 = new Intent(MainMenuActivity.this, Calculate3Activity.class);
                    intent4.putExtra("data3", String.valueOf(spinner.getSelectedItem()));
                    startActivity(intent4);
                } else if(spinner.getSelectedItem().toString().equals("환산취득가액에 필요한 경비")) {
                    Intent intent8 = new Intent(MainMenuActivity.this, Calculate4Activity.class);
                    intent8.putExtra("data4", String.valueOf(spinner.getSelectedItem()));
                    startActivity(intent8);
                } else if(spinner.getSelectedItem().toString().equals("개별 공시지가 조회")) {
                    Intent intent7 = new Intent(MainMenuActivity.this, Individual_LandPriceActivity.class);
                    intent7.putExtra("data7", String.valueOf(spinner.getSelectedItem()));
                    startActivity(intent7);
                } else if(spinner.getSelectedItem().toString().equals("표준 공시지가 조회")) {
                    Intent intent6 = new Intent(MainMenuActivity.this, Standard_LandPriceActivity.class);
                    intent6.putExtra("data6", String.valueOf(spinner.getSelectedItem()));
                    startActivity(intent6);
                }
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        // On selecting a spinner item
    }

    public void onNothingSelected(AdapterView<?> arg0) {
    }

    // 두번 버튼 눌렀을때 뒤로가는 이벤트 설정
    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBntTime;

        if (0 <= gapTime && 2000 >= gapTime) {
            super.onBackPressed();

        } else {
            backBntTime = curTime;
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }

    }

    public void mOnClick3(View view) {


    }
}