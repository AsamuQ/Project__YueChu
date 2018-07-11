package com.example.yuechu;

import android.app.Activity;
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
import android.widget.RelativeLayout;
import android.widget.ViewSwitcher;
import android.widget.ViewSwitcher.ViewFactory;

import static java.lang.Thread.sleep;

public class IndexActivity extends Activity implements ViewFactory,View.OnTouchListener {
    private ImageSwitcher imageSwitcher;
    private LinearLayout linearLayout;
    private int x=0;
    private int index=0;
    private int[] images;
    private float downX;                        //按下点的X坐标
    private ImageView[] tips;


    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what==111){
                imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(IndexActivity.this, R.anim.right_in));
                imageSwitcher.setImageResource(images[msg.arg1]);
            }
            else if (msg.what==112){
                imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(IndexActivity.this,R.anim.left_in));
                imageSwitcher.setImageResource(images[msg.arg1]);
            }
            else if (msg.what==113){
                imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.right_in));
                imageSwitcher.setImageResource(images[msg.arg1]);
            }
            setImageBackground(msg.arg1);
        }
    };


    private void init(){
        imageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher);
        linearLayout=(LinearLayout)findViewById(R.id.viewGroup);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        init();

        //ImageSwitcher初始化
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() { //设置工厂
            @Override
            public View makeView() { //重写makeView
                ImageView imageView=new ImageView(IndexActivity.this);
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
        imageSwitcher.setOnTouchListener(this);

        tips=new ImageView[images.length];
        for (int i=0;i<images.length;i++){
            ImageView mImageView = new ImageView(this);
            tips[i] = mImageView;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT));
            layoutParams.rightMargin = 10;
            layoutParams.leftMargin = 10;

            mImageView.setBackgroundResource(R.drawable.page_indicator_unfocused);
            linearLayout.addView(mImageView, layoutParams);
        }

        index = getIntent().getIntExtra("position", 0);
        imageSwitcher.setImageResource(images[index]);

        setImageBackground(index);


        Runnable r=new Runnable() {
            @Override
            public void run() {
                while (index <= images.length) {
                    Message msg = new Message();
                    msg.what = 111;
                    msg.arg1 = index;
                    handler.sendMessage(msg);
                    index++;
                    if (index >= images.length) {
                        index = 0;
                    }
                    try {
                        sleep(3000); //暂停3秒继续
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(r).start();
    }

    private void setImageBackground(int selectItems){
        for(int i=0; i<tips.length; i++){
            if(i == selectItems){
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            }else{
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
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
                Message msg=new Message();
                //抬起的时候的X坐标大于按下的时候就显示上一张图片
                if(lastX > downX){
                    msg.what=112;
                    if(index > 0){
                        index -- ;
                    }else{
                        index=images.length - 1;
                    }
                }
                else if(lastX < downX){
                    msg.what=113;
                    if(index < images.length - 1){
                        index ++ ;
                    }else{
                        index=0;
                    }
                }
                msg.arg1=index;
                handler.sendMessage(msg);
            }
            break;
        }
        return true;
    }


    @Override
    public View makeView() {
        final ImageView i = new ImageView(this);
        i.setBackgroundColor(0xff000000);
        i.setScaleType(ImageView.ScaleType.CENTER_CROP);
        i.setLayoutParams(new ImageSwitcher.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT));
        return i ;
    }
}
