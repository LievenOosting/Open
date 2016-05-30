package com.qcj.common.base;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import com.loopj.android.http.AsyncHttpClient;
import com.qcj.common.AppConfig;
import com.qcj.common.AppException;
import com.qcj.common.cache.DataCleanManager;
import com.qcj.common.http.ApiHttpClient;
import com.qcj.common.util.MethodsCompat;
import com.qcj.common.util.StringUtils;
import com.qcj.common.util.UIHelper;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.Properties;
import java.util.UUID;

/**
 * 全局应用程序类：用于保存和调用全局应用配置及访问网络数据
 */
public class AppContext extends BaseApplication {

    public static final int PAGE_SIZE = 20;// 默认分页大小

    private static AppContext instance;

    @Override
    public void onCreate() {
        super.onCreate();
        //bug收集，托管到腾讯bugly里面用于后期的分析
        CrashReport.initCrashReport(getApplicationContext(), "900031727", false);
        instance = this;
        init();
//        Thread.setDefaultUncaughtExceptionHandler(AppException
//                .getAppExceptionHandler(this));
        UIHelper.sendBroadcastForNotice(this);
    }

    private void init() {
        // 初始化网络请求
        AsyncHttpClient client = new AsyncHttpClient();
        ApiHttpClient.setHttpClient(client);
    }

    /**
     * 获得当前app运行的AppContext
     *
     * @return
     */
    public static AppContext getInstance() {
        return instance;
    }

    /**
     * 获取App安装包信息
     *
     * @return
     */
    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }

    /**
     * 清除app缓存
     */
    public void clearAppCache() {
        DataCleanManager.cleanDatabases(this);
        // 清除数据缓存
        DataCleanManager.cleanInternalCache(this);
        // 2.2版本才有将应用缓存转移到sd卡的功能
        if (isMethodsCompat(android.os.Build.VERSION_CODES.FROYO)) {
            DataCleanManager.cleanCustomCache(MethodsCompat
                    .getExternalCacheDir(this));
        }
    }

    /**
     * 判断当前版本是否兼容目标版本的方法
     *
     * @param VersionCode
     * @return
     */
    public static boolean isMethodsCompat(int VersionCode) {
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        return currentVersion >= VersionCode;
    }

    public static boolean isFristStart() {
        return getPreferences().getBoolean(AppConfig.KEY_FRITST_START, true);
    }

    public static void setFristStart(boolean frist) {
        set(AppConfig.KEY_FRITST_START, frist);
    }

    //夜间模式
    public static boolean getNightModeSwitch() {
        return getPreferences().getBoolean(AppConfig.KEY_NIGHT_MODE_SWITCH, false);
    }

    // 设置夜间模式
    public static void setNightModeSwitch(boolean on) {
        set(AppConfig.KEY_NIGHT_MODE_SWITCH, on);
    }
}
