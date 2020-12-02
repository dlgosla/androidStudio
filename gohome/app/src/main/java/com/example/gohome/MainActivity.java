package com.example.gohome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        PendingIntent contentIntent1 = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder1 = new NotificationCompat.Builder(this, "notification_ch_id");
        builder1.setSmallIcon(R.mipmap.ic_launcher);
        builder1.setContentTitle("홈화면으로 이동");
        builder1.setContentText("홈화면으로 이동하려면 누르세요");
        builder1.setContentIntent(contentIntent1);
        builder1.setAutoCancel(false);


        //OREO API 26 이상에서는 채널 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            builder1.setSmallIcon(R.drawable.ic_launcher_foreground); //mipmap 사용시 Oreo 이상에서 시스템 UI 에러남
            CharSequence channelName  = "notification channel";
            String description = "오레오 이상을 위한 것임";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel("notification_ch_id", channelName , importance);
            channel.setDescription(description);

            // notification channel을 시스템에 등록
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);

        }else builder1.setSmallIcon(R.mipmap.ic_launcher);
        //Oreo 이하에서 mipmap 사용하지 않으면 Couldn't create icon: StatusBarIcon 에러

        assert notificationManager != null;

        notificationManager.notify(0, builder1.build());
    }
}