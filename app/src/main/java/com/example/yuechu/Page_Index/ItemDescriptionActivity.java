package com.example.yuechu.Page_Index;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuechu.R;
import com.example.yuechu.Recipe;

public class ItemDescriptionActivity extends Activity {
    private ImageView imageView_item;
    private TextView tv_des_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        imageView_item=(ImageView)findViewById(R.id.imageView_item);
        tv_des_item=(TextView)findViewById(R.id.tv_des_item);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        Recipe recipe= (Recipe) bundle.getSerializable("recipe");

        imageView_item.setImageResource(recipe.getPortrait());
        tv_des_item.setText(recipe.getDescription());
    }
}
