package com.example.yuechu;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuechu.page_search.SearchActivity;

import org.jetbrains.annotations.Nullable;

public class SecondFragment extends Fragment {
    private Context context;
    private TextView tv;
    private TextView tv1;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;

    public void setContext(Context context){
        this.context=context;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.search,null);

        tv = (TextView) view.findViewById(R.id.tv_main);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,SearchActivity.class);
                //获取控件在屏幕中的坐标
                int location[] = new int[2];
                tv.getLocationOnScreen(location);
                intent.putExtra("x",location[0]);
                intent.putExtra("y",location[1]);
                startActivity(intent);
                getActivity().overridePendingTransition(0,0);
            }
        });



        imageView1=(ImageView)view.findViewById(R.id.tupian1);
        imageView2=(ImageView)view.findViewById(R.id.tupian2);
        imageView3=(ImageView)view.findViewById(R.id.tupian3);
        imageView4=(ImageView)view.findViewById(R.id.tupian4);
        tv1=(TextView)view.findViewById(R.id.like);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,SearchActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
