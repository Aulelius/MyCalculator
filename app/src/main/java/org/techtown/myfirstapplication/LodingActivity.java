package org.techtown.myfirstapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;


    // 어플 로딩화면 자바 코드
public class LodingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        startLoading();
    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);

    }
}
