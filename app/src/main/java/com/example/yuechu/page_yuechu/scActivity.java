package com.example.yuechu.page_yuechu;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.yuechu.R;

public class scActivity extends Activity {
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sc);

        back=(ImageView)findViewById(R.id.toolbar_back);

        //返回事件
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView bla1 = (ImageView) findViewById(R.id.iv1);
        bla1 .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v4) {
                Intent it = new Intent(scActivity.this, sc1Activity.class);
                startActivity(it);
            }});

        ImageView bla2 = (ImageView) findViewById(R.id.iv2);
        bla2 .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v4) {
                Intent it = new Intent(scActivity.this, sc2Activity.class);
                startActivity(it);
            }});

        ImageView bla3 = (ImageView) findViewById(R.id.iv3);
        bla3 .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v4) {
                Intent it = new Intent(scActivity.this, sc3Activity.class);
                startActivity(it);
            }});
    }
}