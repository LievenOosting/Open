package com.qcj.common.util;

import android.content.Context;
import android.content.DialogInterface;

/**
 * 界面帮助类
 *
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @version 创建时间：2014年10月10日 下午3:33:36
 */
public class UIHelper {

    /**
     * 发送通知广播
     *
     * @param context
     */
    public static void sendBroadcastForNotice(Context context) {
//        Intent intent = new Intent(NoticeService.INTENT_ACTION_BROADCAST);
//        context.sendBroadcast(intent);
    }

    /**
     * 发送App异常崩溃报告
     *
     * @param context
     * @param
     */
    public static void sendAppCrashReport(final Context context) {

        DialogHelp.getConfirmDialog(context, "程序发生异常", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 退出
                System.exit(-1);
            }
        }).show();
    }

}
