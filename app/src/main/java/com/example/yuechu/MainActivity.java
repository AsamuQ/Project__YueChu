package com.example.yuechu;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends Activity {
    private ImageSwitcher imageSwitcher;
    private int x=0;
    private int index=0;
    private int[] images;


    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        //ImageSwitcher初始化
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() { //设置工厂
            @Override
            public View makeView() { //重写makeView
                ImageView imageView=new ImageView(MainActivity.this);
                imageView.setBackgroundColor(0xFFFFFFFF); //白色背景
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER); //居中显示
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT) ); //定义布局
                return imageView;
            }
        });
        images = new int[]{
                R.drawable.img1,
                R.drawable.img2,
                R.drawable.img3
        };


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageSwitcher.setImageResource(images[index++]);
                imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.fade_in));
                imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.fade_out));
                if (index>=images.length){
                    index=0;
                }
                handler.postDelayed(this,2000);
            }
        },2000);

    }

    private void init(){
        imageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher);
    }
}
