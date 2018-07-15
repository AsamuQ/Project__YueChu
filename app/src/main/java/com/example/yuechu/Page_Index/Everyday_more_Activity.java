package com.example.yuechu.Page_Index;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.yuechu.R;
import com.example.yuechu.Recipe;

import java.util.ArrayList;
import java.util.List;

public class Everyday_more_Activity extends Activity {
    private List<Recipe> recipesList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.everyday_more);

        recyclerView=findViewById(R.id.recyleview_everyday);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);

        initData();     //测试用数据
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(recipesList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListenner(new RecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void myClick(View view, int position) {
                Recipe recipe=recipesList.get(position);
                Intent intent=new Intent(getApplicationContext(),ItemDescriptionActivity.class);
                intent.putExtra("recipe",recipe);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        recipesList = new ArrayList<Recipe>();
        recipesList.add(new Recipe(R.mipmap.ic_launcher, "艾斯", "烧烧果实，控制火的能力"));
        recipesList.add(new Recipe(R.mipmap.ic_launcher, "路飞", "橡皮果实，是一个橡皮人"));
        recipesList.add(new Recipe(R.mipmap.ic_launcher, "罗", "手术果实，是一个很厉害的医生"));
        recipesList.add(new Recipe(R.mipmap.ic_launcher, "艾斯", "烧烧果实，控制火的能力"));
        recipesList.add(new Recipe(R.mipmap.ic_launcher, "路飞", "橡皮果实，是一个橡皮人"));
        recipesList.add(new Recipe(R.mipmap.ic_launcher, "罗", "手术果实，是一个很厉害的医生"));
        recipesList.add(new Recipe(R.mipmap.ic_launcher, "艾斯", "烧烧果实，控制火的能力"));
        recipesList.add(new Recipe(R.mipmap.ic_launcher, "路飞", "橡皮果实，是一个橡皮人"));
        recipesList.add(new Recipe(R.mipmap.ic_launcher, "罗", "手术果实，是一个很厉害的医生"));
        recipesList.add(new Recipe(R.mipmap.ic_launcher, "艾斯", "烧烧果实，控制火的能力"));
        recipesList.add(new Recipe(R.mipmap.ic_launcher, "路飞", "橡皮果实，是一个橡皮人"));
        recipesList.add(new Recipe(R.mipmap.ic_launcher, "罗", "手术果实，是一个很厉害的医生"));
        recipesList.add(new Recipe(R.mipmap.ic_launcher, "艾斯", "烧烧果实，控制火的能力"));
        recipesList.add(new Recipe(R.mipmap.ic_launcher, "路飞", "橡皮果实，是一个橡皮人"));
        recipesList.add(new Recipe(R.mipmap.ic_launcher, "罗", "手术果实，是一个很厉害的医生"));
    }
}
