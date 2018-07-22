package com.example.yuechu;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.yuechu.page_my.MenuActivity;
import com.example.yuechu.page_my.PersonalActivity;

public class FourthFragment extends Fragment {
    private Context context;
    private String name;
    private TextView menu;
    private TextView username;

    public void setContext(Context context){
        this.context=context;
    }
    public void setUsername(String name){this.name=name;}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.activity_personal,null);

        username=(TextView)view.findViewById(R.id.tv_username);
        username.setText(name);

        menu=(TextView) view.findViewById(R.id.foodmenu);
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,MenuActivity.class);
                startActivity(intent);
            }
        };
        menu.setOnClickListener(listener);

        return view;
    }
}
