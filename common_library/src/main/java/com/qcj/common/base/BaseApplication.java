package com.qcj.common.base;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.qcj.common.util.Anim;
import com.qcj.common.util.StringUtils;

public class BaseApplication extends Application {
    private static String PREF_NAME = "creativelocker.pref";
    private static String LAST_REFRESH_TIME = "last_refresh_time.pref";
    static Context _context;
    static Resources _resource;
    private static boolean sIsAtLeastGB;

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            sIsAtLeastGB = true;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _context = getApplicationContext();
        _resource = _context.getResources();
    }

    public static synchronized BaseApplication context() {
        return (BaseApplication) _context;
    }

    public static Resources resources() {
        return _resource;
    }

    /**
     * 放入已读列表中
     */
    public static void putReadedPostList(String prefFileName, String key,
                                         String value) {
        SharedPreferences preferences = getPreferences(prefFileName);
        int size = preferences.getAll().size();
        Editor editor = preferences.edit();
        if (size >= 100) {
            editor.clear();
        }
        editor.putString(key, value);
        apply(editor);
    }

    /**
     * 读取是否是已读的文章列表
     *
     * @return
     */
    public static boolean isOnReadedPostList(String prefFileName, String key) {
        return getPreferences(prefFileName).contains(key);
    }

    /**
     * 记录列表上次刷新时间
     *
     * @param key
     * @param value
     */
    public static void putToLastRefreshTime(String key, String value) {
        SharedPreferences preferences = getPreferences(LAST_REFRESH_TIME);
        Editor editor = preferences.edit();
        editor.putString(key, value);
        apply(editor);
    }

    /**
     * 获取列表的上次刷新时间
     *
     * @param key
     * @return
     */
    public static String getLastRefreshTime(String key) {
        return getPreferences(LAST_REFRESH_TIME).getString(key, StringUtils.getCurTimeStr());
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static void apply(Editor editor) {
        if (sIsAtLeastGB) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public static void set(String key, int value) {
        Editor editor = getPreferences().edit();
        editor.putInt(key, value);
        apply(editor);
    }

    public static void set(String key, boolean value) {
        Editor editor = getPreferences().edit();
        editor.putBoolean(key, value);
        apply(editor);
    }

    public static void set(String key, String value) {
        Editor editor = getPreferences().edit();
        editor.putString(key, value);
        apply(editor);
    }

    public static boolean get(String key, boolean defValue) {
        return getPreferences().getBoolean(key, defValue);
    }

    public static String get(String key, String defValue) {
        return getPreferences().getString(key, defValue);
    }

    public static int get(String key, int defValue) {
        return getPreferences().getInt(key, defValue);
    }

    public static long get(String key, long defValue) {
        return getPreferences().getLong(key, defValue);
    }

    public static float get(String key, float defValue) {
        return getPreferences().getFloat(key, defValue);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static SharedPreferences getPreferences() {
        SharedPreferences pre = context().getSharedPreferences(PREF_NAME,
                Context.MODE_MULTI_PROCESS);
        return pre;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static SharedPreferences getPreferences(String prefName) {
        return context().getSharedPreferences(prefName,
                Context.MODE_MULTI_PROCESS);
    }


    /************************
     * activity之间的跳转方法
     ********************************************/
    public void startActivity(Activity now, Class<? extends Activity> target,
                              Bundle data) {
        Intent intent = new Intent();
        intent.setClass(now, target);
        if (data != null) {
            if (intent.getExtras() != null) {
                intent.replaceExtras(data);
            } else {
                intent.putExtras(data);
            }
        }
        now.startActivity(intent);
        Anim.in(now);
    }

    public void startActivity(Activity now, Class<? extends Activity> target,
                              Bundle data, int flag) {
        Intent intent = new Intent();
        intent.setClass(now, target);
        intent.setFlags(flag); // 注意本行的FLAG设置
        if (data != null) {
            if (intent.getExtras() != null) {
                intent.replaceExtras(data);
            } else {
                intent.putExtras(data);
            }
        }
        now.startActivity(intent);
        Anim.in(now);
    }

    public void startActivityForResult(Activity now,
                                       Class<? extends Activity> target, Bundle data) {
        Intent intent = new Intent();
        intent.setClass(now, target);
        if (data != null) {
            if (intent.getExtras() != null) {
                intent.replaceExtras(data);
            } else {
                intent.putExtras(data);
            }
        }
        now.startActivityForResult(intent, BaseActivity.GET_DATA_FROM_ACTIVITY);
        Anim.in(now);
    }

}
