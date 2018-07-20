package com.example.yuechu.Page_Index;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.yuechu.R;
import com.example.yuechu.Recipe;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ItemView_Activity extends Activity {
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private RecyclerViewAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private int currentPage=2;
    private String Url;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==200) {
                if (msg.arg1==2){
                    refreshLayout.finishRefresh(2000);
                    adapter.refreshRecipes((List<Recipe>)msg.obj);
                }else {
                    refreshLayout.finishLoadMore(2000);
                    adapter.addRecipes((List<Recipe>)msg.obj);
                }
            }else {
                Toast.makeText(getApplicationContext(),"抓取失败，请检查网络！",Toast.LENGTH_SHORT).show();
            }
        }
    };

private void init(){
    //将recyclerView设置为网格布局
    recyclerView.setLayoutManager(gridLayoutManager);
    //适配器adapter初始化，将数据(recipesList)填入适配器
    adapter = new RecyclerViewAdapter(getApplicationContext());

    recyclerView.setAdapter(adapter);

    //添加recyclerView中Item的点击时间，同时将点击的Item数据传出到指定Activity
    adapter.setOnItemClickListenner(new RecyclerViewAdapter.OnRecyclerViewItemClickListener() {
        @Override
        public void myClick(View view, int position) {
            Recipe recipe = adapter.getRecipe(position);
            Intent intent = new Intent(getApplicationContext(), ItemDescriptionActivity.class);
            intent.putExtra("recipe", recipe);
            startActivity(intent);
        }
    });
}
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_view);

        recyclerView=findViewById(R.id.recyleview_everyday);
        gridLayoutManager=new GridLayoutManager(this,2);
        refreshLayout=findViewById(R.id.refreshLayout);

        setTitle(getIntent().getExtras().getString("title"));
        Url=getIntent().getExtras().getString("url");

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refresh();
                refreshLayout.finishRefresh(2000);
                Toast.makeText(getApplicationContext(),"刷新成功",Toast.LENGTH_SHORT).show();
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });

        refresh();
        init();
    }

    private void loadMore(){getHttpData(++currentPage);}

    private void refresh(){getHttpData(currentPage);}

    public void getHttpData(final int page){
        new Thread(new Runnable() {
            Message msg=new Message();
            @Override
            public void run() {
                try{
                    List<Recipe> recipesList=new ArrayList<>();
                    Document doc=(Document)Jsoup.connect(Url+page+".html").get();
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
                    msg.arg1=page;
                    msg.obj=recipesList;
                    handler.sendMessage(msg);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
