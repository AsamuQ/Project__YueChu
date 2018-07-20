package com.example.yuechu.page_my;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.yuechu.R;

import java.util.ArrayList;

public class InsertMenuActivity extends Activity {

    //    定义变量
    LinearLayout AddmainLinerLayout;
    LinearLayout AddfuLinerLayout;
    LinearLayout AddstepLinerLayout;

    ArrayList<View> main_view=new ArrayList<View>();
    ArrayList<View> fu_view=new ArrayList<View>();
    ArrayList<View> step_view=new ArrayList<View>();
    ArrayList<ImageView>step_imageview=new ArrayList<ImageView>();
    int index=-1;

    ArrayList<Material> main_Material=new ArrayList<Material>();
    ArrayList<Material> fu_Material=new ArrayList<Material>();
    ArrayList<String> step_str=new ArrayList<String>();
    ArrayList<String> steppic_str=new ArrayList<String>();
    ArrayList<String> other_str=new ArrayList<String>();

    ImageView chengpin;
    ImageView StepImage;
    Spinner prepare_s,cook_s,taste_s,difficulty_s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_menu);

        //      textview设置两种颜色
        TextView textname=(TextView) findViewById(R.id.menuname);
        TextView textstep=(TextView) findViewById(R.id.step);
        String food_name=String.format("菜谱名<font color=\"#ADADAD\">%s","（必填）");
        String food_step=String.format("做法步骤<font color=\"#ADADAD\">%s","（至少三步）");
        textname.setText(Html.fromHtml(food_name));
        textstep.setText(Html.fromHtml(food_step));

        //      设置监听器
        ImageButton Addmainp=(ImageButton)findViewById(R.id.addmainp);
        Button Addmainb=(Button)findViewById(R.id.addmainb);
        ImageButton Addfup=(ImageButton)findViewById(R.id.addfup);
        Button Addfub=(Button)findViewById(R.id.addfub);
        Button Addstep=(Button)findViewById(R.id.addstep);
        Button end=(Button)findViewById(R.id.menu_end);
        ImageButton returnbtn=(ImageButton)findViewById(R.id.imageButton);
        chengpin=(ImageView) findViewById(R.id.Chengpintu);
        Button Draft_btn=(Button)findViewById(R.id.draft_btn);
        StepImage=(ImageView) findViewById(R.id.stepimage);

        prepare_s=(Spinner)findViewById(R.id.prepare_s);
        taste_s=(Spinner)findViewById(R.id.taste_s);
        cook_s=(Spinner)findViewById(R.id.cook_s);
        difficulty_s=(Spinner)findViewById(R.id.difficulty_s);

        step_imageview.add(chengpin);
        step_imageview.add(StepImage);


        //监听器时间
        View.OnClickListener AddFoodlistener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId())
                {
                    case R.id.addmainp:Createmainitem();break;
                    case R.id.addmainb:Createmainitem();break;
                    case R.id.addfup:Createfuitem();break;
                    case R.id.addfub:Createfuitem();break;
                    case R.id.addstep:Createstepitem();break;
                    case R.id.menu_end:Publish();break;
                    case R.id.imageButton:finish();break;
                    case R.id.Chengpintu:ChoicePic();break;
                    //                    case R.id.draft_btn:JumpToDisplay();break;
                    case R.id.stepimage:ChoicePic();break;
                }
            }
        };

        Addmainp.setOnClickListener(AddFoodlistener);
        Addmainb.setOnClickListener(AddFoodlistener);
        Addfup.setOnClickListener(AddFoodlistener);
        Addfub.setOnClickListener(AddFoodlistener);
        Addstep.setOnClickListener(AddFoodlistener);
        end.setOnClickListener(AddFoodlistener);
        returnbtn.setOnClickListener(AddFoodlistener);
        chengpin.setOnClickListener(AddFoodlistener);
        //        Draft_btn.setOnClickListener(AddFoodlistener);
        StepImage.setOnClickListener(AddFoodlistener);
    }

    //    增加主料行
    public void Createmainitem()
    {
        AddmainLinerLayout = (LinearLayout) findViewById(R.id.main_food);
        View view=View.inflate(InsertMenuActivity.this,R.layout.main_food,null);
        main_view.add(view);
        AddmainLinerLayout.addView(view);
    }

    //    增加辅料行
    public void Createfuitem()
    {
        AddfuLinerLayout = (LinearLayout) findViewById(R.id.fu_food);
        View view=View.inflate(InsertMenuActivity.this,R.layout.fu_food,null);
        fu_view.add(view);
        AddfuLinerLayout.addView(view);
        return ;
    }

    //    增加步骤行
    public void Createstepitem()
    {
        AddstepLinerLayout=(LinearLayout)findViewById(R.id.step_food);
        View view=View.inflate(InsertMenuActivity.this,R.layout.step_food,null);
        step_view.add(view);
        AddstepLinerLayout.addView(view);

        ImageView step_image=view.findViewById(R.id.step_image);
        step_imageview.add(step_image);
        step_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChoicePic();
            }
        });
        return;
    }

    //    发布菜单
    public void Publish()
    {
        EditText Menu_name=(EditText) findViewById(R.id.menu_name);
        String menu_name=Menu_name.getText().toString();               //获取菜品名
        step_str.add(menu_name);

        EditText Main_name=(EditText) findViewById(R.id.mainnamebtn);
        String main_name1=Main_name.getText().toString();               //获取主料名
        EditText Main_num=(EditText) findViewById(R.id.mainnumbtn);
        String main_num1=Main_num.getText().toString();                 //获取主料数量
        Material main_material1=new Material(main_name1,main_num1);
        main_Material.add(main_material1);
        for(int i = 0;i < main_view.size(); ++i )
        {
            EditText name_edit=main_view.get(i).findViewById(R.id.m_name);
            String main_name=name_edit.getText() .toString();
            EditText num_edit=main_view.get(i).findViewById(R.id.m_num);
            String main_num=num_edit.getText().toString();
            Material main_material=new Material(main_name,main_num);
            main_Material.add(main_material);
        }

        EditText Fu_name=(EditText) findViewById(R.id.funamebtn);      //获取辅料名
        String fu_name1=Fu_name.getText().toString();
        EditText Fu_num=(EditText) findViewById(R.id.funumbtn);         //获取辅料数量
        String fu_num1=Fu_num.getText().toString();
        Material fu_material1=new Material(fu_name1,fu_num1);
        fu_Material.add(fu_material1);
        for(int i = 0;i < fu_view.size(); i++ )
        {
            EditText name_edit=fu_view.get(i).findViewById(R.id.fu_name);
            String fu_name=name_edit.getText().toString();
            EditText num_edit=fu_view.get(i).findViewById(R.id.fu_num);
            String fu_num= num_edit.getText().toString();
            Material fu_material=new Material(fu_name,fu_num);
            fu_Material.add(fu_material);
        }

        EditText Step_Edit=(EditText)findViewById(R.id.stepedit);        //步骤过程
        String step_edit=Step_Edit.getText().toString();
        step_str.add(step_edit);
        for(int j=0;j<step_view.size();j++)
        {
            EditText editText=step_view.get(j).findViewById(R.id.step_edit);
            step_str.add(editText.getText().toString());
        }

        GetSpinner(prepare_s);GetSpinner(cook_s);GetSpinner(taste_s);GetSpinner(difficulty_s);
        Menu menu=new Menu(main_Material,fu_Material,step_str,steppic_str,other_str);

        Intent intent=getIntent();
        Bundle bundle=new Bundle();
        bundle.putSerializable("Menu",menu);
        intent.putExtras(bundle);

        setResult(RESULT_OK,intent);
        finish();
    }


    //    上传成品图
    public void ChoicePic()
    {
        Intent intent=new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,200);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //在相册里面选择好相片之后调回到现在的这个activity中
        if(requestCode==200&&resultCode==RESULT_OK)
            if(data!=null)
            {
                Uri uri=data.getData();
                steppic_str.add(uri.toString());
                step_imageview.get(++index).setImageURI(uri);
            }
    }

    public void GetSpinner(Spinner sp)
    {
        String str=sp.getSelectedItem().toString();
        other_str.add(str);
    }
}
