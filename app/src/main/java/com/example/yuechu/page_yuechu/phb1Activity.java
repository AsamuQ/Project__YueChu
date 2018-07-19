package com.example.yuechu.page_yuechu;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuechu.R;

public class phb1Activity extends Activity {
    private TextView tv_name;
    private TextView tv_des;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phb1);

        tv_name=(TextView)findViewById(R.id.tv_name);
        tv_des=(TextView)findViewById(R.id.tv_des);
        imageView=(ImageView)findViewById(R.id.imageView);

        Chief chief= (Chief) getIntent().getExtras().getSerializable("chief");
        tv_name.setText(chief.getName());
        tv_des.setText(chief.getCheif_dec());
        imageView.setImageResource(chief.getImgsrc());

    }
}
