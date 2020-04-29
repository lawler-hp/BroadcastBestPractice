package com.example.broadcastbestpractice;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动管理器
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    /**
     * 添加一个活动
     * @param activity
     */
    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    /**
     * 删除一个活动
     * @param activity
     */
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    /**
     * 关闭所有活动
     */
    public static void finishAll(){
        for (Activity activity : activities) {
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }

}
