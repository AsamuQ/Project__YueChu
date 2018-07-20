package com.example.yuechu.Page_Index;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yuechu.R;
import com.example.yuechu.Recipe;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ItemDescriptionActivity extends Activity {
    private ImageView imageView_item;
    private TextView tv_des_item;
    private TextView tv_steps;
    private Recipe recipe;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_item_description);

        TextView title=(TextView)findViewById(R.id.txt_topbar);
        imageView_item=(ImageView)findViewById(R.id.imageView_item);
        tv_des_item=(TextView)findViewById(R.id.tv_des_item);
        tv_steps=(TextView)findViewById(R.id.tv_steps);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        recipe= (Recipe) bundle.getSerializable("recipe");
        String Url=recipe.getUrl();

        title.setText(recipe.getName());
        Glide.with(this).load(recipe.getPortrait()).into(imageView_item);
        tv_des_item.setText("__by"+recipe.getDescription());

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what==100){
                    tv_steps.setText("  "+(String)msg.obj);
                }
            }
        };
        getStepsText();
    }

    public void getStepsText(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String s="步骤：\n";
                Message msg=new Message();
                try {
                    Document doc = (Document) Jsoup.connect("https://home.meishichina.com/recipe-409076.html").get();
                    Elements stepsLinks=doc.select("div.wrap").select("div.w.clear").select("div.space_left").select("div.space_box_home").select("div.recipDetail").select("div.recipeStep").select("ul").select("li");
                    for (int i=0;i<stepsLinks.size();i++){
                        s=s+stepsLinks.get(i).select("div.recipeStep_word").text()+"\n";
                    }
                    msg.what=100;
                    msg.obj=s;

                    if (stepsLinks.size()==0)
                        msg.what=0;
                } catch (IOException e) {
                    msg.what=101;
                    e.printStackTrace();
                }
                handler.sendMessage(msg);
            }
        }).start();
    }
}
