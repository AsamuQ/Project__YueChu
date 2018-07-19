package com.example.yuechu;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.yuechu.page_yuechu.phbActivity;
import com.example.yuechu.page_yuechu.scActivity;
import com.example.yuechu.page_yuechu.xsActivity;

public class ThirdFragment extends Fragment {
    private Context context;
    private LinearLayout bla1;
    private LinearLayout bla2;
    private LinearLayout bla3;
    private LinearLayout bla4;

    public void setContext(Context context){
        this.context=context;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.activity_yc,null);

        bla1=view.findViewById(R.id.la1);
        bla2=view.findViewById(R.id.la2);
        bla3=view.findViewById(R.id.la3);
        bla4=view.findViewById(R.id.la4);

        bla1 .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v1) {
                //天猫超市的网址
                Uri uri= Uri.parse("https://chaoshi.tmall.com/");
                //进行跳转的intent
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                //进行跳转
                startActivity(intent);
            }});

        bla2 .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                Intent it = new Intent(context, xsActivity.class);
                startActivity(it);
            }});

        bla3 .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v3) {
                Intent it = new Intent(context, phbActivity.class);
                startActivity(it);
            }});

        bla4 .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v4) {
                Intent it = new Intent(context, scActivity.class);
                startActivity(it);
            }});

        return view;
    }
}
