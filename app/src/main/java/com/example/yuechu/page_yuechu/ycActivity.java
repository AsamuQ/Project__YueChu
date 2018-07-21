package com.example.yuechu.page_yuechu;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.yuechu.R;

public class ycActivity extends Activity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yc);


        LinearLayout bla1 = (LinearLayout) findViewById(R.id.la1);
        bla1 .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v1) {
                //天猫超市的网址
                Uri uri= Uri.parse("https://chaoshi.tmall.com/");
                //进行跳转的intent
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                //进行跳转
                startActivity(intent);
            }});

        LinearLayout bla2 = (LinearLayout) findViewById(R.id.la2);
        bla2 .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                Intent it = new Intent(ycActivity.this, xsActivity.class);
                startActivity(it);
            }});

        LinearLayout bla3 = (LinearLayout) findViewById(R.id.la3);
        bla3 .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v3) {
                Intent it = new Intent(ycActivity.this, phbActivity.class);
                startActivity(it);
            }});

        LinearLayout bla4 = (LinearLayout) findViewById(R.id.la4);
        bla4 .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v4) {
                Intent it = new Intent(ycActivity.this, scActivity.class);
                startActivity(it);
            }});


    }
}