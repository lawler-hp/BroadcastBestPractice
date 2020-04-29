package com.example.broadcastbestpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;

public class MainActivity extends BaseActivity {
    /**
     * 启动MainActivity活动
     * @param intent
     */
    public static void actionStart(Context context){
        Intent intent = new Intent(context,MainActivity.class);
         context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button forceOffline = (Button)findViewById(R.id.force_offline);
        forceOffline.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.broadcastbestpractice.FORCE_OFFLINE");
                intent.addFlags(0x01000000);    //突破限制
                sendBroadcast(intent);
            }
        });
    }
}
