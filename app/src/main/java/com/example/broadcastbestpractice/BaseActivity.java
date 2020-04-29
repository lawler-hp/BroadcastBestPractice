package com.example.broadcastbestpractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    private ForceOffline offline;

    /**
     * 创建活动
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    /**
     * 活动恢复，在这注册广播接收器
     */
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcastbestpractice.FORCE_OFFLINE");
        offline = new ForceOffline();
        registerReceiver(offline,intentFilter);
    }

    /**
     * 活动停止，在这里注销广播接收器
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (offline != null) {
            unregisterReceiver(offline);
            offline = null;
        }
    }

    /**
     * 注销活动
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    /**
     * ForceOffline 广播接收器
     */
    class ForceOffline extends BroadcastReceiver{

        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are force to be offline, Please try to login again");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    LoginActivity.actionStart(context);
                    ActivityCollector.finishAll();
                }
            });
            builder.show();
        }
    }
}
