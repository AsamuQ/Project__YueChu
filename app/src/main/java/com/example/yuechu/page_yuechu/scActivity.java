package com.example.yuechu.page_yuechu;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yuechu.R;

import java.io.File;

public class scActivity extends Activity {
    private ImageView back;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
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
                // 激活系统的照相机进行录像
                Intent intent = new Intent();
                intent.setAction("android.media.action.VIDEO_CAPTURE");
                intent.addCategory("android.intent.category.DEFAULT");

                // 保存录像到指定的路径
                File file = new File("/sdcard/video.3pg");
                Uri uri = Uri.fromFile(file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                startActivityForResult(intent, 0);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this, "调用照相机完毕", Toast.LENGTH_SHORT).show();
        super.onActivityResult(requestCode, resultCode, data);


    }
}