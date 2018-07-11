package com.example.yuechu;

import android.app.Activity;
import android.media.ImageWriter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;
import android.view.ViewGroup.LayoutParams;

public class MainActivity extends Activity implements View.OnTouchListener{
    private ImageSwitcher imageSwitcher;
    private int x=0;
    private int index=0;
    private int[] images;
    private float downX;                        //按下点的X坐标
    private boolean isSlip=false;


    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what==111){
                imageSwitcher.setImageResource(images[msg.arg1]);
                imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in));
                imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out));

            }
            else if (msg.what==112){
                imageSwitcher.setImageResource(images[msg.arg1]);
                imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.left_in));
                imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.right_out));
            }
            else if (msg.what==113){
                imageSwitcher.setImageResource(images[msg.arg1]);
                imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.right_in));
                imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.left_out));
            }
        }
    };


    private void init(){
        imageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher);
    }

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
        imageSwitcher.setOnTouchListener(this);
        images = new int[]{
                R.drawable.img1,
                R.drawable.img2,
                R.drawable.img3
        };

        Runnable r=new Runnable() {
            @Override
            public void run() {
                while (index <= images.length&&isSlip==false) {
                    Message msg = new Message();
                    msg.what = 111;
                    msg.arg1 = index;
                    handler.sendMessage(msg);
                    index++;
                    if (index >= images.length) {
                        index = 0;
                    }
                    try {
                        Thread.sleep(2000); //暂停2秒继续，try…catch省略
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(r).start();


    }


    public boolean onTouch(View v, MotionEvent event) {//判断图片左右滑动
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:{
                //手指按下的X坐标
                downX = event.getX();
                break;
            }
            case MotionEvent.ACTION_UP:{
                float lastX = event.getX();
                //抬起的时候的X坐标大于按下的时候就显示上一张图片
                if(lastX > downX){
                    if(index > 0){
                        index -- ;
                    }else{
                        index=images.length - 1;
                    }
                    Message msg=new Message();
                    msg.what=112;
                    msg.arg1=index;
                    handler.sendMessage(msg);
                }

                if(lastX < downX){
                    if(index < images.length - 1){
                        index ++ ;
                    }else{
                        index=0;
                    }
                    Message msg=new Message();
                    msg.what=113;
                    msg.arg1=index;
                    handler.sendMessage(msg);
                }
            }

            break;
        }
        return true;
    }



}
