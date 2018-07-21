package com.example.yuechu.page_search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuechu.Page_Index.ItemDescriptionActivity;
import com.example.yuechu.Page_Index.RecyclerViewAdapter;
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

public class Search_ItemActivity extends Activity {
    private String search;
    private TextView title;
    private ImageView back;
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private RecyclerViewAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private int currentPage = 2;
    private String Url;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==200) {
                if (msg.arg1==0){
                    Toast.makeText(getApplicationContext(),"未搜索此类",Toast.LENGTH_SHORT).show();
                }
                else if (msg.arg1==2){
                    refreshLayout.finishRefresh(2000);
                    adapter.refreshRecipes((List<Recipe>)msg.obj);
                }else {
                    refreshLayout.finishLoadMore(2000);
                    adapter.addRecipes((List<Recipe>)msg.obj);
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__item);

        //设置标题
        search = getIntent().getExtras().getString("search");
        title = (TextView) findViewById(R.id.txt_topbar);
        title.setText("搜索“" + search + "”结果");

        //返回事件
        back = (ImageView) findViewById(R.id.toolbar_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView=findViewById(R.id.recyleview_everyday);
        gridLayoutManager=new GridLayoutManager(this,2);
        refreshLayout=findViewById(R.id.refreshLayout);
        Url=new String("https://home.meishichina.com/show-top-type-recipe-page-");

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

    private void init() {
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

    private void loadMore(){
        currentPage+=3;
        getHttpData(currentPage);
    }
    private void refresh(){getHttpData(currentPage);}

    public void getHttpData(final int page){
        new Thread(new Runnable() {
            Message msg=new Message();
            @Override
            public void run() {
                try{
                    List<Recipe> recipesList=new ArrayList<>();
                    for (int i=page;i<page+5;i++){
                        Document doc=(Document) Jsoup.connect(Url+i+".html").get();
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
                    }
                    msg.what=200;
                    msg.arg1=page;
                    msg.obj=getSearchResults(recipesList,search);
                    if (msg.obj==null){msg.arg1=0;}
                    handler.sendMessage(msg);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public List<Recipe> getSearchResults(List<Recipe> list,String search) {
        List<Recipe> result=new ArrayList<>();
        for (Recipe r: list
             ) {
            if (r.getName().contains(search)){
                result.add(r);
            }
        }
        if (result.isEmpty()){return null;}
        return result;
    }
}


