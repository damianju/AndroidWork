package com.lec.android.a015_web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Main3Activity extends AppCompatActivity {
    public static final String API_KEY = "554272615264616d3132394273656947";
    TextView tvResult;
    String url_address;
    EditText editText;
    String reqType = "xml";


    int startIndex=1;
    int endIndex=5;
    String statnNm;
    final StringBuilder sb = new StringBuilder();

    Handler handler = new Handler();
    // XML 파싱
    DocumentBuilderFactory dbFactory;
    DocumentBuilder dBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvResult = findViewById(R.id.tvResult);
        editText = findViewById(R.id.editText);
        Button btnXML = findViewById(R.id.btnXML);
        Button btnJSON = findViewById(R.id.btnJSON);
        Button btnParse = findViewById(R.id.btnParse);

        statnNm = editText.getText().toString().trim();

        btnXML.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                reqType = "xml";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            url_address = buildUrlAddress(reqType, startIndex, endIndex, statnNm);
                            request(url_address);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });

        btnJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reqType = "json";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            url_address = buildUrlAddress(reqType, startIndex, endIndex, statnNm);
                            request(url_address);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvResult.setText("");

                switch(reqType){
                    case "xml":
                        parseXML();
                        break;
                    case "json":
                        parseJSON();
                        break;
                }
            }
        });

    } // end onCreate

    public void request(String url_address) {


        BufferedReader reader = null;
        HttpURLConnection conn = null;

        try {
            URL url = new URL(url_address);
            conn =(HttpURLConnection) url.openConnection();

            if(conn != null){
                conn.setConnectTimeout(5000);   // timeout 시간 설정. 경과하면 SocketTimeoutException 발생
                conn.setUseCaches(false); // 캐시 사용 안함
                conn.setRequestMethod("GET"); // GET 방식 request
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

                conn.setDoInput(true); // URLConnection 을 입력으로 사용. (true),  (false) --> 출력용

                int responseCode= conn.getResponseCode(); // response code 값 성공하면 200
                if(responseCode == HttpURLConnection.HTTP_OK){ // HTTP_OK: 200

                    reader =  new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;
                    while(true) {
                        line = reader.readLine();
                        if(line ==null) break;
                        sb.append(line +"\n");
                    }

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader != null) reader.close();
                if(conn != null) conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                    tvResult.setText(sb.toString());
            }
        });


    } // end request

    public static String buildUrlAddress(String reqType, int startIndex, int endIndex, String statnNm) throws UnsupportedEncodingException {
        String url_address = String.format("http://swopenapi.seoul.go.kr/api/subway/%s/%s/stationInfo/%d/%d/%s",
                API_KEY, reqType, startIndex, endIndex, URLEncoder.encode(statnNm, "utf-8"));

        return url_address;
    }

    void parseXML() {
        InputSource is = new InputSource(new StringReader(sb.toString()));

        // DOM parser 객체 생성
        Document doc;  // 곧바로 InputStream 으로부터 받아 파싱

        try {
            doc = dBuilder.parse(is);
            Element element = doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("row");  // 서울시 지하철은 <row>~</row> 로 구성됨

            Log.d("myapp", "nList.getLength() = " + nList.getLength());

            for(int i = 0; i < nList.getLength(); i++){
                Node node = nList.item(i);
                Element rowElement = (Element)node;   // 원래는 node.getNodeType() == Node.ELEMENT_NODE 체크해봐야 한다
                final String subwayId =
                        rowElement.getElementsByTagName("subwayId").item(0).getChildNodes().item(0).getNodeValue();
                final String subwayNm =
                        rowElement.getElementsByTagName("subwayNm").item(0).getChildNodes().item(0).getNodeValue();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvResult.setText(tvResult.getText().toString() +
                                "--------------\nId:" + subwayId + "\n호선:" + subwayNm + "\n");
                    }
                }); // end post
            } // end for
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }



    } // end parseXML

    void parseJSON() {

        try {
            JSONObject obj = new JSONObject(sb.toString());
            JSONArray stationArr = obj.getJSONArray("stationList");

            for(int i = 0; i < stationArr.length(); i++){
                JSONObject station = (JSONObject)stationArr.get(i);
                final String subwayId = station.getString("subwayId");
                final String subwayNm = station.getString("subwayNm");

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvResult.setText(tvResult.getText().toString() +
                                "--------------\nId:" + subwayId + "\n호선:" + subwayNm + "\n");
                    }
                }); // end post

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    } // end method



} // end Main3Activity
