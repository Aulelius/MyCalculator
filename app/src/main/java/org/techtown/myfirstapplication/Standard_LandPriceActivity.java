package org.techtown.myfirstapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;


public class Standard_LandPriceActivity extends Activity {

    // 공공데이터포털 표준 공시지가 api 처리 자바 코드
    EditText edit;
    TextView text;

    XmlPullParser xpp;
    String key = "p7ESdOU1KDhw7I9Pr4dgfwIw%2Bcyqet79u6uSqcB771tmFGhaUsLWba%2FrMvKlI%2BP1hmIId8AL7epLPhsia93WMw%3D%3D";
    // 공공데이터 포털 공시지가 데이터 검색 키
    String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

     //   Bundle bundle = getIntent().getExtras();
     //   String data = bundle.get("data6").toString();

        edit = (EditText) findViewById(R.id.edit);
        text = (TextView) findViewById(R.id.result4);

        /*final Spinner spinner = (Spinner) findViewById(R.id.stdyear);

        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<>();
        categories.add("년도");
        categories.add("2019");
        categories.add("2018");
        categories.add("2017");
        categories.add("2016");
        categories.add("2015");
        categories.add("2014");
        categories.add("2013");
        categories.add("2012");
        categories.add("2011");
        categories.add("2010");
        categories.add("2009");
        categories.add("2008");
        categories.add("2007");
        categories.add("2006");
        categories.add("2005");
        categories.add("2004");
        categories.add("2003");
        categories.add("2002");
        categories.add("2001");
        categories.add("2000");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);




    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }
*/

    }




    // Button을 클릭했을 때 자동으로 호출되는 callback method
    public void mOnClick(View v) {




            switch (v.getId()) {
            case R.id.button:

                // Android 4.0 이상부터는 네트워크를 이용할 때 반드시 Thread를 사용해야 한다.
                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        data = getXmlData(); // 아래 메소드를 호출하여 XML data를 파싱해서 String 객체로 얻어오기

                        // UI Thread(Main Thread)를 제외한 어떤 Thread도 화면을 변경할 수 없기때문에
                        // runOnUiThread()를 이용하여 UI Thread가 TextView 글씨 변경하도록 함
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                text.setText(data); // TextView에 문자열 data 출력
                            }
                        });
                    }
                }).start();
                break;
        }

    } // mOnClick method

    // XmlPullParser를 이용하여 공공데이터포털에서 제공하는 OpenAPI XML 파일 파싱하기(parsing)
    String getXmlData() {

        StringBuffer buffer = new StringBuffer();

        String lc = edit.getText().toString(); // EditText에 작성돤 Text 얻어오기
        String ldCode = URLEncoder.encode(lc); // 한글의 경우 인식이 안되기에 utf-8 방식으로 encode한다.
        String query = "";


        String queryUrl = "http://apis.data.go.kr/1611000/nsdi/ReferLandPriceService/attr/getReferLandPriceAttr?" +
                "ServiceKey=p7ESdOU1KDhw7I9Pr4dgfwIw%2Bcyqet79u6uSqcB771tmFGhaUsLWba%2FrMvKlI%2BP1hmIId8AL7epLPhsia93WMw%3D%3D&" +
                "ldCode="+ ldCode +"&stdrYear=2015&format=xml&numOfRows=10&pageNo=1";

        try {
            URL url = new URL(queryUrl); // 문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is = url.openStream(); // url위치로 엽력스트림 연결

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            // inputstream으로부터 xml 입력 받기
            xpp.setInput(new InputStreamReader(is, "UTF-8"));
            // inputstream으로부터 xml 입력

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

                        if (tag.equals("field"))  {
                    // 검색 버튼을 눌렀을 경우 검색결과
                        } else if (tag.equals("pblntfPclnd")) {
                          buffer.append("공시지가(원/㎡) :");
                          xpp.next();
                          buffer.append(xpp.getText());
                          buffer.append("\n");
                        } else if (tag.equals("ldCodeNm")) {
                            buffer.append("법정동주소 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("roadSideCodeNm")) {
                            buffer.append("도로측면 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("stdrLandSn")) {
                            buffer.append("표준지일련번호 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("stdrYear")) {
                            buffer.append("기준년도 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("regstrSeCodeNm")) {
                            buffer.append("특수지구분명 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("prposAreaNm1")) {
                            buffer.append("용도지역명1 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("ladUseSittnNm")) {
                            buffer.append("토지이용상황 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("tpgrphHgCodeNm")) {
                            buffer.append("지형높이 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("posesnStleNm")) {
                            buffer.append("소유형태명 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xpp.getName(); // 태그 이름 얻어오기

                        if (tag.equals("field")) buffer.append("\n"); // 첫번째 검색결과 종료 후 줄바꿈
                        break;
                }

                eventType = xpp.next();
            }


        } catch (Exception e) {

        }

        buffer.append("검색 종료\n");
        return buffer.toString(); // 파싱 다 종료 후 StringBuffer 문자열 객체 변환



    }  // getXmlData method......
}  // Standard_LandPriceActivity class..

