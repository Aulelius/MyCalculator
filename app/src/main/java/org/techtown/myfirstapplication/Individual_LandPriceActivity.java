package org.techtown.myfirstapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

public class Individual_LandPriceActivity extends Activity {

    // 공공데이터포털 개별공시지가 조회 자바 코드
    EditText edit;
    TextView text;

    XmlPullParser xpp;
    String key = "p7ESdOU1KDhw7I9Pr4dgfwIw%2Bcyqet79u6uSqcB771tmFGhaUsLWba%2FrMvKlI%2BP1hmIId8AL7epLPhsia93WMw%3D%3D";
// 공공데이터포털 키

    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        edit = (EditText) findViewById(R.id.edit7);
        text = (TextView) findViewById(R.id.result7);
    }

    // Button을 클릭했을 때 자동으로 호출되는 callback method.......
    public void mOnClick2(View v) {

        switch (v.getId()) {
            case R.id.button7:

                // Android 4.0 이상부터는 네트워크를 이용할 때 반드시 Thread 사용해야 함
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        data = getXmlData();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                text.setText(data);
                            }
                        });
                    }
                }).start();
                break;
        }
    } // mOnClick method....

    String getXmlData() {

        StringBuffer buffer = new StringBuffer();

        String pu = edit.getText().toString();
        String pnu = URLEncoder.encode(pu);
        String query = "";

        String queryUrl = "http://apis.data.go.kr/1611000/nsdi/IndvdLandPriceService/attr/getIndvdLandPriceAttr?" +
                "ServiceKey=p7ESdOU1KDhw7I9Pr4dgfwIw%2Bcyqet79u6uSqcB771tmFGhaUsLWba%2FrMvKlI%2BP1hmIId8AL7epLPhsia93WMw%3D%3D&" +
                "pnu=" + pnu + "&stdrYear=2015&format=xml&numOfRows=10&pageNo=1";

        try {
            URL url = new URL(queryUrl);
            InputStream is = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8"));

            String tag;

            xpp.next();
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName(); // 태그 이름 얻어오기

                        if (tag.equals("field")) {
                        } else if (tag.equals("pblntfPclnd")) {
                            buffer.append("공시지가 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("ldCodeNm")) {
                            buffer.append("법정동명 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("mnnmSlno")) {
                            buffer.append("지번 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("stdrYear")) {
                            buffer.append("기준년도 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("pblntfDe")) {
                            buffer.append("공시일자 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("lastUpdtDt")) {
                            buffer.append("데이터기준일자 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xpp.getName();


                        if (tag.equals("field")) buffer.append("\n");
                        break;
                }

                eventType = xpp.next();
            }
        }catch (XmlPullParserException e) {
            e.printStackTrace(); // 파서 공장 실패

        } catch (IOException e) {
            e.printStackTrace(); // 스트림 실패

        }
        buffer.append("검색 종료\n");
        return buffer.toString(); // StringBuffer 문자열 객체 반환
    } // getXmlData method ...

} // Individual_LandPriceActivity class..
