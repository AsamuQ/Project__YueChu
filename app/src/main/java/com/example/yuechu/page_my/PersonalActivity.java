package com.example.yuechu.page_my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.yuechu.R;

public class PersonalActivity extends Activity {
    private TextView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        menu=(TextView) findViewById(R.id.foodmenu);

        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId())
                {
                    case R.id.foodmenu:JumptoMenu();
                }
            }
        };

        menu.setOnClickListener(listener);
    }

    public void JumptoMenu()
    {
        Intent intent=new Intent(PersonalActivity.this,MenuActivity.class);
        startActivity(intent);
    }
}
