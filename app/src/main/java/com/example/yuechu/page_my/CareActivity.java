package com.example.yuechu.page_my;

import android.app.Activity;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.yuechu.R;

import java.util.ArrayList;

public class CareActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care);

        ArrayList<Material> care_list=new ArrayList<Material>();

        Uri uri1 = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + getResources().getResourcePackageName(R.drawable.acount1) + "/"
                + getResources().getResourceTypeName(R.drawable.acount1) + "/"
                + getResources().getResourceEntryName(R.drawable.acount1));
        Material material1=new Material("约厨小秘书",uri1.toString());
        care_list.add(material1);

        Material material2=new Material("管理员",uri1.toString());
        care_list.add(material2);

        Material material3=new Material("呆妹",uri1.toString());
        care_list.add(material3);

        MenuAdapter careAdapter = new MenuAdapter(CareActivity.this, R.layout.attention, care_list);
        ListView carelistView = (ListView) findViewById(R.id.carelistview);
        carelistView.setAdapter(careAdapter);

        ImageButton imageButton=(ImageButton)findViewById(R.id.return_imagebtn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
