package com.example.yuechu;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewSwitcher;

import org.jetbrains.annotations.Nullable;

import static java.lang.Thread.sleep;

public class FirstFragment extends Fragment implements ViewSwitcher.ViewFactory,View.OnTouchListener {
    private ImageSwitcher imageSwitcher;
    private LinearLayout linearLayout;
    private int x=0;
    private int currentPosition=0;
    private int[] images;                       //轮播图片
    private float downX;                        //按下点的X坐标
    private ImageView[] tips;                   //轮播图小圆点

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
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.activity_index,null);

        imageSwitcher=(ImageSwitcher)view.findViewById(R.id.imageSwitcher);
        linearLayout=(LinearLayout)view.findViewById(R.id.viewGroup);

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView=new ImageView(getActivity());
                imageView.setBackgroundColor(0xFFFFFFFF); //白色背景
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
                        sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
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
}
