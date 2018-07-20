package com.example.yuechu.page_search;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuechu.R;

public class SearchActivity extends Activity {
    private ImageView imageView;
    private TextView et_bg;
    private EditText et_content;
    private ImageView back;
    private TextView tv_search;
    private FrameLayout fl;
    private boolean finishing;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        et_bg=(TextView) findViewById(R.id.et_bg);
        et_content=(EditText) findViewById(R.id.et_content);
        fl=(FrameLayout)findViewById(R.id.fl);
        back=(ImageView)findViewById(R.id.iv_arrow);
        tv_search=(TextView) findViewById(R.id.search_btn);
        imageView1=(ImageView)findViewById(R.id.shuiguo);
        imageView2=(ImageView)findViewById(R.id.roulei) ;
        imageView3=(ImageView)findViewById(R.id.shuichan);
        imageView4=(ImageView)findViewById(R.id.shucai);

        et_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!finishing){
                    finishing=true;
                    outAnimation();
                }
            }
        });
        //监听布局是否发生变化
        et_bg.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                et_bg.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                inAnimation();
            }
        });


        imageView=(ImageView)findViewById(R.id.iv_arrow);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://list.tmall.com/search_product.htm?spm=a3204.24086.7219553831.1.e32b58e73iXubM&q=%BD%F8%BF%DA%CB%AE%B9%FB&user_id=725677994&pos=1&cat=52598012&style=g&acm=201509244.1003.2.1897626&scm=1003.2.201509244.OTHER_1513675242197_1897626&industryCatId=50514008"));
                startActivity(intent);
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://list.tmall.com/search_product.htm?spm=a3204.24086.1213532585.5.e32b58e7v3q5MW&active=1&acm=201509150.1003.2.448532&sort=s&tmhkmain=0&industryCatId=50514008&smAreaId=420100&user_id=725677994&pos=2&cat=52622013&style=g&search_condition=1&scm=1003.2.201509150.OTHER_1499481921225_448532"));
                startActivity(intent);
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://list.tmall.com/search_product.htm?spm=a3204.24086.1213532585.7.e32b58e7v3q5MW&q=&user_id=725677994&pos=3&cat=52636010&end_price=&start_price=&acm=201509150.1003.2.448532&scm=1003.2.201509150.OTHER_1496697933034_448532&brand=97097173%2C274838064"));
                startActivity(intent);
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://list.tmall.com/search_product.htm?spm=a3204.24086.1213532585.19.e32b58e7v3q5MW&acm=201509150.1003.2.448532&sort=s&industryCatId=52598012&smAreaId=420102&q=%CA%DF%B2%CB&user_id=725677994&pos=6&cat=52624008&style=g&from=chaoshi.index.pc_1_searchbutton&search_condition=23&scm=1003.2.201509150.OTHER_1496379106380_448532"));
                startActivity(intent);
            }
        });
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://list.tmall.com/search_product.htm?q=%CD%C1%B6%B9&user_id=725677994&type=p&cat=50514008&spm=a3204.7139825.a2227oh.d100&from=chaoshi.index.pc_1_searchbutton"));
                startActivity(intent);
            }
        });

        InputFilter filter=new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!isChinese(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };
        tv_search.setFilters(new InputFilter[]{filter});








    }


    private void inAnimation() {
        float originY=getIntent().getIntExtra("y",0);
        //获取到搜索框在TwoActivity界面的位置
        int[] location=new int[2];
        et_bg.getLocationOnScreen(location);
        //计算位置的差值
        final float translateY=originY-(float)location[1];
        //将第一个界面的位置设置给搜索框
        et_bg.setY(et_bg.getY()+translateY);
        //同步设置搜索框中的文字
        et_content.setY(et_bg.getY()+(et_bg.getHeight()-et_content.getHeight())/2);
        float top = getResources().getDisplayMetrics().density * 20;
        //ValueAnimator是一个很厉害的东西，你只需要给他初始值和结束值，他会自动计算中间的过度
        final ValueAnimator translateVa = ValueAnimator.ofFloat(et_bg.getY(), top);
        //这个是由下移动到上面的监听
        translateVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                et_bg.setY((Float) valueAnimator.getAnimatedValue());
                et_content.setY(et_bg.getY() + (et_bg.getHeight() - et_content.getHeight()) / 2);
                back.setY(et_bg.getY() + (et_bg.getHeight() - back.getHeight()) / 2);
                tv_search.setY(et_bg.getY() + (et_bg.getHeight() - tv_search.getHeight()) / 2);
            }
        });
        //这个是缩小搜索框的监听
        ValueAnimator scaleVa = ValueAnimator.ofFloat(1, 0.8f);
        scaleVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                et_bg.setScaleX((Float) valueAnimator.getAnimatedValue());
            }
        });
        //这个是设置透明度
        ValueAnimator alphaVa = ValueAnimator.ofFloat(0, 1f);
        alphaVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                back.setAlpha((Float) valueAnimator.getAnimatedValue());
                tv_search.setAlpha((Float) valueAnimator.getAnimatedValue());
                fl.setAlpha((Float) valueAnimator.getAnimatedValue());
            }
        });

        alphaVa.setDuration(500);
        translateVa.setDuration(500);
        scaleVa.setDuration(500);

        alphaVa.start();
        translateVa.start();
        scaleVa.start();
    }

    private void outAnimation() {
        float originY=getIntent().getIntExtra("y",0);

        int[] location=new int[2];
        et_bg.getLocationOnScreen(location);

        final float translateY=originY-(float)location[1];
        et_bg.setY(et_bg.getY()+translateY);
        et_content.setY(et_bg.getY()+(et_bg.getHeight()-et_content.getHeight())/2);
        float top = getResources().getDisplayMetrics().density * 20;
        final ValueAnimator translateVa = ValueAnimator.ofFloat(top, et_bg.getY());

        translateVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                et_bg.setY((Float) valueAnimator.getAnimatedValue());
                et_content.setY(et_bg.getY() + (et_bg.getHeight() - et_content.getHeight()) / 2);
                back.setY(et_bg.getY() + (et_bg.getHeight() - back.getHeight()) / 2);
                tv_search.setY(et_bg.getY() + (et_bg.getHeight() - tv_search.getHeight()) / 2);
            }
        });

        translateVa.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                finish();
                overridePendingTransition(0, 0);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        ValueAnimator scaleVa = ValueAnimator.ofFloat(0.8f, 1);
        scaleVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                et_bg.setScaleX((Float) valueAnimator.getAnimatedValue());
            }
        });

        ValueAnimator alphaVa = ValueAnimator.ofFloat(1f, 0);
        alphaVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                back.setAlpha((Float) valueAnimator.getAnimatedValue());
                tv_search.setAlpha((Float) valueAnimator.getAnimatedValue());
                fl.setAlpha((Float) valueAnimator.getAnimatedValue());
            }
        });

        alphaVa.setDuration(500);
        translateVa.setDuration(500);
        scaleVa.setDuration(500);

        alphaVa.start();
        translateVa.start();
        scaleVa.start();
    }

    @Override
    public void onBackPressed() {
        if(!finishing){
            finishing=true;
            outAnimation();
        }
    }


    public boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

}
