package com.example.administrator.warehousemanagementsystem.jpush;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.activity.LoginActivity;

import cn.jpush.android.api.JPushInterface;

/**
 * author: ZhongMing
 * DATE: 2019/1/9 0009
 * Description:极光推送
 **/
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JIGUANG";
    public static String regId;

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle bundle = intent.getExtras();
            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
                //send the Registration Id to your server...

            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息(内容为): " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
                // 收到消息 显示通知
                processCustomMessage(context, bundle);
                // 自定义消息不是通知，默认不会被SDK展示到通知栏上，极光推送仅负责透传给SDK。其内容和展示形式完全由开发者自己定义。
                // 自定义消息主要用于应用的内部业务逻辑和特殊展示需求
            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
                Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");

                String extra_json = bundle.getString(JPushInterface.EXTRA_EXTRA);
                if (!TextUtils.isEmpty(extra_json))
                    Log.d(TAG, "[MyReceiver] 接收到推送下来的通知附加字段" + extra_json);

                // 可以利用附加字段来区别Notication,指定不同的动作,extra_json是个json字符串
                // 通知（Notification），指在手机的通知栏（状态栏）上会显示的一条通知信息
            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
                // 跳转的Activity
                Intent intent1 = new Intent(context, LoginActivity.class);
                intent1.putExtras(bundle);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent1);
                // 在这里根据 JPushInterface.EXTRA_EXTRA(附加字段) 的内容处理代码，
                // 比如打开新的Activity， 打开一个网页等..

            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
                Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                Log.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
            } else {
                Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void processCustomMessage(Context context, Bundle bundle) {
        String channelID = "1";
        String channelName = "channel_name";
        // 跳转的Activity
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        // 获得系统推送的自定义消息
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //适配安卓8.0的消息渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder notification =
                new NotificationCompat.Builder(context, channelID);
        notification.setAutoCancel(true)
                .setContentText(message)
                .setContentTitle("您有一条待处理信息")
                .setSmallIcon(R.mipmap.logo)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(pendingIntent);
        notificationManager.notify((int) (System.currentTimeMillis() / 1000), notification.build());
        //振动事件
        Vibrator vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);
    }

}
