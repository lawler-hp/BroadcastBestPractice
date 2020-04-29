package com.example.broadcastbestpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox rememberPass;
    private SharedPreferences sp;

    /**
     * 启动LoginActivity活动
     * @param intent
     */
    public static void actionStart(Context context){
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        accountEdit = (EditText)findViewById(R.id.account);
        passwordEdit = (EditText)findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        rememberPass = (CheckBox)findViewById(R.id.remember_pass);

        sp = PreferenceManager.getDefaultSharedPreferences(this);
        boolean remember_pass = sp.getBoolean("rememberPass", false);
        if (remember_pass){
            String account = sp.getString("account", "");
            String password = sp.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CommitPrefEdits")
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if (account.equals("admin") && password.equals("123456")){
                    SharedPreferences.Editor edit = sp.edit();
                    if (rememberPass.isChecked()){
                        edit.putBoolean("rememberPass",true);
                        edit.putString("account",account);
                        edit.putString("password",password);
                    }else {
                        edit.clear();
                    }
                    edit.apply();
                    MainActivity.actionStart(LoginActivity.this);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,"account or password is invalid",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
