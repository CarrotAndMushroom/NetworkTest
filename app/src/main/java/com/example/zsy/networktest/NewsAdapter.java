package com.example.zsy.networktest;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ZSY on 2017/4/28.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    private int resourceId;

    public NewsAdapter(Context context, int textViewResourceId, List<News> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        News news = getItem(position);
        View view;
        if (convertView== null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }else {
            view=convertView;
        }
        TextView ctime =(TextView) view.findViewById(R.id.news_ctime);
        TextView description=(TextView) view.findViewById(R.id.description);
        TextView picUrl=(TextView) view.findViewById(R.id.picUrl);
        TextView url=(TextView) view.findViewById(R.id.url);
        TextView title =(TextView) view.findViewById(R.id.news_title1);
        title.setText(news.getTitle());
        url.setText(news.getUrl());
        picUrl.setText(news.getPicUrl());
        description.setText(news.getDescription());
        ctime.setText(news.getCtime());
        return view;
    }
}
