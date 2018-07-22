package com.example.yuechu.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yuechu.IndexActivity;
import com.example.yuechu.R;
import com.example.yuechu.data.Person;
import com.example.yuechu.db.PersonDAO;
import com.example.yuechu.register.RegisterActivity;

public class LoginActivity extends Activity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnGoRegister;
    private CheckBox rem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        pref= PreferenceManager.getDefaultSharedPreferences(this);

        etUsername=(EditText)findViewById(R.id.et_username);
        etPassword=(EditText)findViewById(R.id.et_password);
        btnLogin=(Button)findViewById(R.id.btnlogin);
        btnGoRegister=(Button)findViewById(R.id.btngoregister);
        rem=(CheckBox)findViewById(R.id.remember);

        boolean isRemenber=pref.getBoolean("remember_password",false);
        if(isRemenber){
            //将账号和密码都设置到文本中
            String username=pref.getString("username","");
            String password=pref.getString("password","");
            etUsername.setText(username);
            etPassword.setText(password);
            rem.setChecked(true);

        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goRegister();
            }
        });

    }
    private void login() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        PersonDAO personDAO = new PersonDAO(this);


        Person person = personDAO.find(username);

        if (person == null) {
            Toast.makeText(this, "不存在该用户！", Toast.LENGTH_SHORT).show();
        } else if (!person.password.equals(password)) {
            Toast.makeText(this, "密码错误，请输入正确的密码！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();

            editor=pref.edit();
            if(rem.isChecked()){
                editor.putBoolean("remember_password",true);
                editor.putString("username",username);
                editor.putString("password",password);
            }else {
                editor.clear();
            }
            editor.apply();

            Intent intent = new Intent(getApplicationContext(),IndexActivity.class);
            startActivity(intent);
            finish();
        }
    }


    private void goRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();

    }


}
