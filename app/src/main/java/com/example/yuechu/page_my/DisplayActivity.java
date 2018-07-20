package com.example.yuechu.page_my;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yuechu.R;

import java.util.ArrayList;

public class DisplayActivity extends Activity {

    ArrayList<Material> main_Material=new ArrayList<Material>();
    ArrayList<Material> fu_Material=new ArrayList<Material>();
    ArrayList<String> step_str=new ArrayList<String>();
    ArrayList<String> steppic_str=new ArrayList<String>();
    ArrayList<String> other_str=new ArrayList<String>();
    ArrayList<Material> step_Material=new ArrayList<Material>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        Menu menu=(Menu)bundle.getSerializable("Menu");

        //主料的listview
        main_Material=menu.getMain_list();
        MaterialAdapter mainAdapter= new MaterialAdapter(
                DisplayActivity.this,
                R.layout.main_material,
                main_Material);
        ListView main_Listview=(ListView)findViewById(R.id.main_listview);
        main_Listview.setAdapter(mainAdapter);

        //辅料的listview
        fu_Material=menu.getFu_list();
        MaterialAdapter fuAdapter= new MaterialAdapter(
                DisplayActivity.this,
                R.layout.main_material,
                fu_Material);
        ListView fu_Listview=(ListView)findViewById(R.id.fu_listview);
        fu_Listview.setAdapter(fuAdapter);

        //成品图
        step_str=menu.getStep_list();
        steppic_str=menu.getSteppic_list();
        ImageView finished=(ImageView) findViewById(R.id.finished);
        finished.setImageURI(Uri.parse(steppic_str.get(0)));
        TextView menu_name=(TextView)findViewById(R.id.menu_name);
        menu_name.setText(step_str.get(0));

        //      步骤的listview
        for(int i=1;i<step_str.size();i++)
        {
            Material material=new Material(step_str.get(i),steppic_str.get(i));
            step_Material.add(material);
        }
        MenuAdapter menuAdapter= new MenuAdapter(
                DisplayActivity.this,
                R.layout.step_item,
                step_Material);
        ListView step_Listview=(ListView)findViewById(R.id.step_listview);
        step_Listview.setAdapter(menuAdapter);

        //添加其他属性
        other_str=menu.getOther();
        TextView prepare_t=(TextView)findViewById(R.id.prepare_t);
        prepare_t.setText(other_str.get(0));
        TextView cook_t=(TextView)findViewById(R.id.cook_t);
        cook_t.setText(other_str.get(1));
        TextView taste_t=(TextView)findViewById(R.id.taste_t);
        taste_t.setText(other_str.get(2));
        TextView difficulty_t=(TextView)findViewById(R.id.difficulty_t);
        difficulty_t.setText(other_str.get(3));
    }
}
