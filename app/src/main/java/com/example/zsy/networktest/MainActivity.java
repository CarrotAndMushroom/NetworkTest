package com.example.zsy.networktest;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private List<News> newsList = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView =(ListView) findViewById(R.id.list_view);
        sendRequestWithOKHttp();
    }

    private void sendRequestWithOKHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://api.tianapi.com/social/?key=0d46fee50a97bc5103af7af84f5055ca&num=10").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithJSON(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void parseJSONWithJSON(String jsonData){
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray newslist = jsonObject.getJSONArray("newslist");
            for(int i=0;i<newslist.length();i++){
                JSONObject jo =newslist.getJSONObject(i);
                String ctime = jo.getString("ctime");
                String title = jo.getString("title");
                String description = jo.getString("description");
                String picUrl = jo.getString("picUrl");
                String url = jo.getString("url");

                News news =new News();
                news.setCtime(jo.getString("ctime"));
                news.setTitle(jo.getString("title"));
                news.setDescription(jo.getString("description"));
                news.setPicUrl(jo.getString("picUrl"));
                news.setUrl(jo.getString("url"));
                newsList.add(news);

                Log.d("MainActivity","ctime is  "+ctime);
                Log.d("MainActivity","title is  "+title);
                Log.d("MainActivity","description is  "+description);
                Log.d("MainActivity","picUrl is  "+picUrl);
                Log.d("MainActivity","url is  "+url);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        NewsAdapter adapter = new NewsAdapter(MainActivity.this,R.layout.news_item,newsList);
        showListview(adapter);
    }
    private void showListview(final NewsAdapter adapter){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listView.setAdapter(adapter);
            }
        });
    }

}
//"ctime":"2017-04-28 17:10",
// "title":"官方回应“101岁老人捡垃圾谋生”：生活节俭成习惯",
// "description":"搜狐社会",
// "picUrl":"http:\/\/photocdn.sohu.com\/20170428\/Img491088673_ss.jpeg",
// "url":"http:\/\/news.sohu.com\/20170428\/n491095223.shtml"}

//    NewsAdapter adapter = new NewsAdapter(MainActivity.this,R.layout.news_item,newsList);
//        listView.setAdapter(adapter);