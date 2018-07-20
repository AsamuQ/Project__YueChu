package com.example.yuechu.page_yuechu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.yuechu.R;

public class phbActivity extends Activity {
    private ImageView back;
    private LinearLayout la1;
    private LinearLayout la2;
    private LinearLayout la3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phb);

        back=(ImageView)findViewById(R.id.toolbar_back);
        //返回事件
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        la1=(LinearLayout)findViewById(R.id.la1);
        la2=(LinearLayout)findViewById(R.id.la2);
        la3=(LinearLayout)findViewById(R.id.la3);

        final Chief chief1=new Chief();
        final Chief chief2=new Chief();
        final Chief chief3=new Chief();
        chief1.setName("小当家");
        chief1.setImgsrc(R.drawable.phb1);
        chief1.setCheif_dec("      主人公刘昴星年仅13岁，却极具厨艺天分，拥有的超人的味觉和想象力，谨记母亲的指导以及一颗永不言败的心，在各种烹饪比赛和各方的挑战中不断成长，成为最年轻的\"特级厨师\"，并与邪恶的\"黑暗料理界\"开始了对抗。");
        chief2.setName("小福贵");
        chief2.setImgsrc(R.drawable.phb2);
        chief2.setCheif_dec("      主人公是一个山村少年，洪家菜第18代传人 ，老蔡头的徒弟，洪老头的孙子。他聪明、调皮，重亲情，对爷爷极为孝顺。他从小跟爷爷学做菜，左手食指能够测出菜的味道，味道最佳时，手指能变成金黄色。他为了拿到御赐的金厨具，救出被捕的爷爷，大胆创新，将祖传的厨艺发挥到了巅峰!与老佛爷的妹妹的女儿小飞蝶以及光绪皇帝结下深厚友谊，打败了前首席御膳大厨冷面，拿到18把金厨具。");
        chief3.setName("山治");
        chief3.setImgsrc(R.drawable.phb3);
        chief3.setCheif_dec("小时候跟随大海贼红脚哲普学习厨艺。踢技以快准狠被海军称之为\"黑足\"，但从不愿意伤害任何的女性，哪怕是敌人。在经过司法岛一战后也成了悬赏对象，首次悬赏就有7700万之高（但通缉令是画上去的）。梦想是找到传说之海All Blue而跟随路飞一同进入了伟大航路。是文斯莫克家族的第三子。");

        final Intent intent=new Intent(this,phb1Activity.class);
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.la1:
                        intent.putExtra("chief",chief1);
                        startActivity(intent);
                        break;
                    case R.id.la2:
                        intent.putExtra("chief",chief2);
                        startActivity(intent);
                        break;
                    case R.id.la3:
                        intent.putExtra("chief",chief3);
                        startActivity(intent);
                        break;
                }
            }
        };

        la1.setOnClickListener(listener);
        la2.setOnClickListener(listener);
        la3.setOnClickListener(listener);
    }
}
