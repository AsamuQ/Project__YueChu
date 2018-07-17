package com.example.yuechu;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.yuechu.Page_Index.ItemView_Activity;
import com.example.yuechu.Page_Index.ItemDescriptionActivity;
import com.example.yuechu.Page_Index.RecyclerViewAdapter;

import org.jetbrains.annotations.Nullable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class FirstFragment extends Fragment implements ViewSwitcher.ViewFactory,View.OnTouchListener {
    private Context context;
    private TextView tv_everyday_more;
    private TextView tv_like_more;
    private ImageButton imagebtn_hot;
    private ImageButton imagebtn_weather;
    private ImageButton imagebtn_location;
    private ImageButton imagebtn_nutrition;
    private ImageSwitcher imageSwitcher;
    private LinearLayout linearLayout;
    private int currentPosition=0;
    private int[] images;                       //轮播图片
    private float downX;                        //按下点的X坐标
    private ImageView[] tips;                   //轮播图小圆点
    private List<Recipe> recipesList;
    private List<Recipe> recipesList2;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private RecyclerViewAdapter adapter;
    private RecyclerViewAdapter adapter2;
    private GridLayoutManager gridLayoutManager;
    private GridLayoutManager gridLayoutManager2;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg){
            if (msg.what==100){
                imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));
                imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_out));
                imageSwitcher.setImageResource(images[msg.arg1]);
            }
            else if (msg.what==101){
                imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.left_in));
                imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.right_out));
                imageSwitcher.setImageResource(images[msg.arg1]);
            }
            else if (msg.what==102){
                imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.right_in));
                imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.left_out));
                imageSwitcher.setImageResource(images[msg.arg1]);
            }
            setImageBackground(msg.arg1);
            if (msg.what==200){
                //将recyclerView设置为网格布局
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView2.setLayoutManager(gridLayoutManager2);
                //适配器adapter初始化，将数据(recipesList)填入适配器
                adapter = new RecyclerViewAdapter(recipesList,context);
                adapter2= new RecyclerViewAdapter(recipesList2,context);

                if (recipesList!=null) {
                    recyclerView.setAdapter(adapter);
                }
                if (recipesList2!=null) {
                    recyclerView2.setAdapter(adapter2);
                }
                //添加recyclerView中Item的点击时间，同时将点击的Item数据传出到指定Activity
                adapter.setOnItemClickListenner(new RecyclerViewAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void myClick(View view, int position) {
                        Recipe recipe = recipesList.get(position);
                        Intent intent = new Intent(context, ItemDescriptionActivity.class);
                        intent.putExtra("recipe", recipe);
                        startActivity(intent);
                    }
                });
                adapter2.setOnItemClickListenner(new RecyclerViewAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void myClick(View view, int position) {
                        Recipe recipe = recipesList2.get(position);
                        Intent intent = new Intent(context, ItemDescriptionActivity.class);
                        intent.putExtra("recipe", recipe);
                        startActivity(intent);
                    }
                });
            }
        }
    };

    public void setContext(Context context){
        this.context=context;
    }
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.activity_index,null);

        tv_everyday_more=(TextView)view.findViewById(R.id.tv_everyday_more);
        tv_like_more=(TextView)view.findViewById(R.id.tv_like_more);
        imagebtn_hot=(ImageButton)view.findViewById(R.id.imagebtn_hot);
        imagebtn_weather=(ImageButton)view.findViewById(R.id.imagebtn_weather);
        imagebtn_location=(ImageButton)view.findViewById(R.id.imagebtn_location);
        imagebtn_nutrition=(ImageButton)view.findViewById(R.id.imagebtn_nutrition);
        imageSwitcher=(ImageSwitcher)view.findViewById(R.id.imageSwitcher);
        linearLayout=(LinearLayout)view.findViewById(R.id.viewGroup);

        recyclerView=view.findViewById(R.id.item_every);
        recyclerView2=view.findViewById(R.id.item_like);
        gridLayoutManager=new GridLayoutManager(context,2);
        gridLayoutManager2=new GridLayoutManager(context,2);
        recipesList=new ArrayList<>();
        recipesList2=new ArrayList<>();
        //设置recyclerview条目
        getHttpData();

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView=new ImageView(getActivity());
                imageView.setBackgroundColor(Color.TRANSPARENT);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER); //居中显示
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT) ); //定义布局
                return imageView;
            }
        });
        images=new int[]{R.drawable.img1,R.drawable.img2,R.drawable.img3};
        imageSwitcher.setImageResource(images[0]);
        imageSwitcher.setOnTouchListener((View.OnTouchListener) this);

        //        设置轮播图小圆点
        tips=new ImageView[images.length];
        for (int i=0;i<images.length;i++){
            ImageView mImageView = new ImageView(view.getContext());
            tips[i] = mImageView;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT));
            layoutParams.rightMargin = 10;
            layoutParams.leftMargin = 10;

            mImageView.setBackgroundResource(R.drawable.page_indicator_unfocused);
            linearLayout.addView(mImageView, layoutParams);
        }

        imageSwitcher.setImageResource(images[currentPosition]);

        setImageBackground(currentPosition);

        //轮播图线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (currentPosition<=images.length){
                    Message msg=new Message();
                    msg.what=100;
                    msg.arg1=currentPosition;
                    handler.sendMessage(msg);
                    currentPosition++;
                    if (currentPosition>=images.length){
                        currentPosition=0;
                    }
                    try{
                        sleep(3000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        //点击跳转食谱浏览页面
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="";
                String title="";
                switch (v.getId()){
                    case R.id.imagebtn_hot:
                        url="https://home.meishichina.com/show-top-type-recipe.html";
                        title="热门食谱";
                        break;
                    case R.id.imagebtn_weather:
                        url="https://home.meishichina.com/show-top-type-recipe-order-quarter.html";
                        title="节气食谱";
                        break;
                    case R.id.imagebtn_location:
                        url="https://home.meishichina.com/show-top-type-recipe-order-star.html";
                        title="当地食谱";
                        break;
                    case R.id.imagebtn_nutrition:
                        url="https://home.meishichina.com/show-top-type-recipe-order-pop.html";
                        title="营养食谱";
                        break;
                }
                Intent intent=new Intent(getActivity(), ItemView_Activity.class);
                intent.putExtra("url",url);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        };
        tv_everyday_more.setOnClickListener(listener);
        tv_like_more.setOnClickListener(listener);
        imagebtn_hot.setOnClickListener(listener);
        imagebtn_weather.setOnClickListener(listener);
        imagebtn_location.setOnClickListener(listener);
        imagebtn_nutrition.setOnClickListener(listener);

        return view;
    }

    //设置小圆点状态
    private void setImageBackground(int selectItems){
        for(int i=0; i<tips.length; i++){
            if(i == selectItems){
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            }else{
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
    }

    //滑动切换图片
    public boolean onTouch(View v, MotionEvent event) {//判断图片左右滑动
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:{
                //手指按下的X坐标
                downX = event.getX();
                break;
            }
            case MotionEvent.ACTION_UP:{
                float lastX = event.getX();
                Message msg=new Message();
                //抬起的时候的X坐标大于按下的时候就显示上一张图片
                if(lastX > downX){
                    msg.what=101;
                    if(currentPosition > 0){
                        currentPosition -- ;
                    }else{
                        currentPosition=images.length - 1;
                    }
                }
                else if(lastX < downX){
                    msg.what=102;
                    if(currentPosition < images.length - 1){
                        currentPosition ++ ;
                    }else{
                        currentPosition=0;
                    }
                }
                msg.arg1=currentPosition;
                handler.sendMessage(msg);
            }
            break;
        }
        return true;
    }

    @Override
    public View makeView() {
        final ImageView i = new ImageView(getActivity());
        i.setBackgroundColor(0xff000000);
        i.setScaleType(ImageView.ScaleType.CENTER_CROP);
        i.setLayoutParams(new ImageSwitcher.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT));
        return i ;
    }

    public void getHttpData(){
        new Thread(new Runnable() {
            Message msg=new Message();
            @Override
            public void run() {
                try{
                    Document doc= (Document) Jsoup.connect("https://www.meishichina.com/").get();
                    Elements titleLinks = doc.select("div.w5").select("ul.on").select("li");
                    for (int j = 0; j < 4; j++) {
                        String imguri=titleLinks.get(j).select("img.imgLoad").attr("data-src");
                        String title = titleLinks.get(j).select("p").text();
                        String des = titleLinks.get(j).select("a.u").text();
                        String url=titleLinks.get(j).select("a").attr("href");
                        Recipe recipe=new Recipe(imguri,title,des,url);
                        recipesList.add(recipe);
                    }
                    for (int j = 4; j < 8; j++) {
                        String imguri=titleLinks.get(j).select("img.imgLoad").attr("data-src");
                        String title = titleLinks.get(j).select("p").text();
                        String des = titleLinks.get(j).select("a.u").text();
                        String url=titleLinks.get(j).select("a").attr("href");
                        Recipe recipe=new Recipe(imguri,title,des,url);
                        recipesList2.add(recipe);
                    }
                    msg.what=200;
                }catch (Exception e){
                    e.printStackTrace();
                }
                handler.sendMessage(msg);
            }
        }).start();
    }
}
