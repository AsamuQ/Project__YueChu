package com.example.yuechu.Page_Index;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.yuechu.R;
import com.example.yuechu.Recipe;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ItemView_Activity extends Activity {
    private List<Recipe> recipesList;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private GridLayoutManager gridLayoutManager;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==200) {
                //将recyclerView设置为网格布局
                recyclerView.setLayoutManager(gridLayoutManager);
                //适配器adapter初始化，将数据(recipesList)填入适配器
                adapter = new RecyclerViewAdapter(recipesList,getApplicationContext());

                if (recipesList!=null) {
                    recyclerView.setAdapter(adapter);
                }
                //添加recyclerView中Item的点击时间，同时将点击的Item数据传出到指定Activity
                adapter.setOnItemClickListenner(new RecyclerViewAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void myClick(View view, int position) {
                        Recipe recipe = recipesList.get(position);
                        Intent intent = new Intent(getApplicationContext(), ItemDescriptionActivity.class);
                        intent.putExtra("recipe", recipe);
                        startActivity(intent);
                    }
                });
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_view);

        recyclerView=findViewById(R.id.recyleview_everyday);
        gridLayoutManager=new GridLayoutManager(this,2);
        recipesList=new ArrayList<>();

        setTitle(getIntent().getExtras().getString("title"));
        getHttpData(getIntent().getExtras().getString("url"));
    }

    public void getHttpData(final String url){
        new Thread(new Runnable() {
            Message msg=new Message();
            @Override
            public void run() {
                try{
                    Document doc=(Document)Jsoup.connect(url).get();
                    Elements titleLinks=doc.select("div.wrap").select("div.w.clear").select("div.space_left")
                            .select("div#J_list.ui_newlist_1.get_num").select("ul").select("li");
                    for (int j=0;j<titleLinks.size();j++){
                        String imguri=titleLinks.get(j).select("img.imgLoad").attr("data-src");
                        String title = titleLinks.get(j).select("h2").select("a").text();
                        String des = titleLinks.get(j).select("p.subline").select("a").text();
                        String url=titleLinks.get(j).select("h2").select("a").attr("href");
                        Recipe recipe=new Recipe(imguri,title,des,url);
                        recipesList.add(recipe);
                    }
                    msg.what=200;
                    msg.arg1=titleLinks.size();
                    msg.obj=url;
                }catch (Exception e){
                    e.printStackTrace();
                }
                handler.sendMessage(msg);
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
