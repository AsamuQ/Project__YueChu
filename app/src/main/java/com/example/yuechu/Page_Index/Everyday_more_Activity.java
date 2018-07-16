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

import com.example.yuechu.R;
import com.example.yuechu.Recipe;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Everyday_more_Activity extends Activity {
    private List<Recipe> recipesList;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private GridLayoutManager gridLayoutManager;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==200) {
                recyclerView.setLayoutManager(gridLayoutManager);
                adapter = new RecyclerViewAdapter(recipesList,getApplicationContext());

                if (recipesList!=null) {
                    recyclerView.setAdapter(adapter);
                }
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
        setContentView(R.layout.everyday_more);

        recyclerView=findViewById(R.id.recyleview_everyday);
        gridLayoutManager=new GridLayoutManager(this,2);
        recipesList=new ArrayList<>();

        setTitle("每日推荐");
        getHttpData();
    }

    public void getHttpData(){
        new Thread(new Runnable() {
            Message msg=new Message();
            @Override
            public void run() {
                try{
                    Document doc= (Document) Jsoup.connect("https://www.meishichina.com/").get();
                    Elements titleLinks = doc.select("div.w5").select("ul.on").select("li");
                    //for循环遍历获取到每条新闻的四个数据并封装到News实体类中
                    for (int j = 0; j < titleLinks.size(); j++) {
                        String imguri=titleLinks.get(j).select("img.imgLoad").attr("data-src");
                        String title = titleLinks.get(j).select("p").text();
                        String des = titleLinks.get(j).select("a.u").text();
                        String url=titleLinks.get(j).select("a").attr("href");
                        Recipe recipe=new Recipe(imguri,title,des,url);
                        recipesList.add(recipe);
                        System.out.println(recipe.getPortrait());
                    }
                    msg.what=200;
                }catch (Exception e){
                    e.printStackTrace();
                    msg.what=201;
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
