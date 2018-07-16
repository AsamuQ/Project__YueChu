package com.example.yuechu.Page_Index;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuechu.R;
import com.example.yuechu.Recipe;

import java.net.URI;
import java.net.URL;

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

        Glide.with(this).load(recipe.getPortrait()).into(imageView_item);
        //imageView_item.setImageURI(Uri.parse(recipe.getPortrait()));
        tv_des_item.setText(recipe.getDescription());
    }
}
