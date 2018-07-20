package com.example.yuechu.page_my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.yuechu.R;

import java.util.ArrayList;

public class MenuActivity extends Activity {

    ArrayList<Material> menu = new ArrayList<Material>();
    ArrayList<String> step_str = new ArrayList<String>();
    ArrayList<String> steppic_str = new ArrayList<String>();
    Menu Menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button write = (Button) findViewById(R.id.Write);
        ImageButton returnbtn = (ImageButton) findViewById(R.id.ImageButton);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.Write:
                        JumptoInsert();
                        break;
                    case R.id.ImageButton:
                        finish();
                        break;
                }
            }
        };

        write.setOnClickListener(listener);
        returnbtn.setOnClickListener(listener);

        ListView li = (ListView) findViewById(R.id.Menulistview);
        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent2 = new Intent(MenuActivity.this, DisplayActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("Menu", Menu);
                intent2.putExtras(bundle2);
                startActivity(intent2);
            }
        });

    }

    //    跳转到新建菜谱界面
    public void JumptoInsert() {
        Intent intent = new Intent(MenuActivity.this, InsertMenuActivity.class);
        startActivityForResult(intent, 100);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {

                Bundle bundle = data.getExtras();
                Menu = (Menu) bundle.getSerializable("Menu");

                Material menu1 = new Material(Menu.getStep_list().get(0), Menu.getSteppic_list().get(0));
                menu.add(menu1);
                MenuAdapter menuAdapter = new MenuAdapter(MenuActivity.this, R.layout.menuitem, menu);
                ListView menulistView = (ListView) findViewById(R.id.Menulistview);
                menulistView.setAdapter(menuAdapter);

                menuAdapter.setOnItemClickListener(new MenuAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, Material material, int position) {
                        Intent intent2 = new Intent(MenuActivity.this, DisplayActivity.class);
                        Bundle bundle2 = new Bundle();
                        bundle2.putSerializable("Menu", Menu);
                        intent2.putExtras(bundle2);
                        startActivity(intent2);
                    }
                });
            }
        }

    }
}
