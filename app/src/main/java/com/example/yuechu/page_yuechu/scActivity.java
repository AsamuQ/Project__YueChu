package com.example.yuechu.page_yuechu;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.yuechu.R;

public class scActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sc);


        Button bla1 = (Button) findViewById(R.id.bt1);
        bla1 .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v4) {
                Intent it = new Intent(scActivity.this, sc1Activity.class);
                startActivity(it);
            }});

        Button bla2 = (Button) findViewById(R.id.bt2);
        bla2 .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v4) {
                Intent it = new Intent(scActivity.this, sc2Activity.class);
                startActivity(it);
            }});


        Button bla4 = (Button) findViewById(R.id.bt3);
        bla4 .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v4) {
                Intent it = new Intent(scActivity.this, sc3Activity.class);
                startActivity(it);
            }});
    }
}