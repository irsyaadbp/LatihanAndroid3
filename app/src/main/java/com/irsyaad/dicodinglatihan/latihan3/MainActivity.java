package com.irsyaad.dicodinglatihan.latihan3;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.core.content.ContextCompat;

import com.irsyaad.dicodinglatihan.latihan3.alarm.AlarmManagerActivity;
import com.irsyaad.dicodinglatihan.latihan3.asyntask.AsynctaskActivity;
import com.irsyaad.dicodinglatihan.latihan3.backstack.DetailBackstackActivity;
import com.irsyaad.dicodinglatihan.latihan3.broadcast.BroadcastActivity;
import com.irsyaad.dicodinglatihan.latihan3.firebasedispatcher.FirebaseDispatcherActivity;
import com.irsyaad.dicodinglatihan.latihan3.gcmnetwork.GcmNetworkManagerActivity;
import com.irsyaad.dicodinglatihan.latihan3.jobscheduler.JobSchedullerActivity;
import com.irsyaad.dicodinglatihan.latihan3.mvvm.MainViewModelActivity;
import com.irsyaad.dicodinglatihan.latihan3.service.ServiceActivity;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AsyncCallback {
    Button btnAsyntask, btnService,btnBroadcast, btnAlarm, btnJob, btnGcm, btnFbDispatcher,
            btnBackstack, btnMvvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btnAsyntask.setOnClickListener(this);
        btnService.setOnClickListener(this);
        btnBroadcast.setOnClickListener(this);
        btnAlarm.setOnClickListener(this);
        btnJob.setOnClickListener(this);
        btnGcm.setOnClickListener(this);
        btnFbDispatcher.setOnClickListener(this);
        btnBackstack.setOnClickListener(this);
        btnMvvm.setOnClickListener(this);

        DelayAsync delayAsync = new DelayAsync(this);
        delayAsync.execute();
    }

    private void init(){
        btnAsyntask = findViewById(R.id.btn_asyntask);
        btnService = findViewById(R.id.btn_service);
        btnBroadcast = findViewById(R.id.btn_broadcast);
        btnAlarm = findViewById(R.id.btn_alarm);
        btnJob = findViewById(R.id.btn_job);
        btnGcm = findViewById(R.id.btn_gcm);
        btnFbDispatcher = findViewById(R.id.btn_fbdispatcher);
        btnBackstack = findViewById(R.id.btn_backstack);
        btnMvvm = findViewById(R.id.btn_mvvm);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_asyntask:
                Intent moveAsyntask = new Intent(MainActivity.this, AsynctaskActivity.class);
                startActivity(moveAsyntask);
                break;
            case R.id.btn_service:
                Intent moveService = new Intent(MainActivity.this, ServiceActivity.class);
                startActivity(moveService);
                break;
            case R.id.btn_broadcast:
                Intent moveBroadcast = new Intent(MainActivity.this, BroadcastActivity.class);
                startActivity(moveBroadcast);
                break;
            case R.id.btn_alarm:
                Intent moveAlarm = new Intent(MainActivity.this, AlarmManagerActivity.class);
                startActivity(moveAlarm);
                break;
            case R.id.btn_job:
                Intent moveJob = new Intent(MainActivity.this, JobSchedullerActivity.class);
                startActivity(moveJob);
                break;
            case R.id.btn_gcm:
                Intent moveGcm = new Intent(MainActivity.this, GcmNetworkManagerActivity.class);
                startActivity(moveGcm);
                break;
            case R.id.btn_fbdispatcher:
                Intent moveFbDispatcher = new Intent(MainActivity.this, FirebaseDispatcherActivity.class);
                startActivity(moveFbDispatcher);
                break;
            case R.id.btn_backstack:
                Intent detailIntent = new Intent(MainActivity.this, DetailBackstackActivity.class);
                detailIntent.putExtra(DetailBackstackActivity.EXTRA_TITLE, "Hola, Good News");
                detailIntent.putExtra(DetailBackstackActivity.EXTRA_MESSAGE, "Now you can learn android in Dicoding");
                startActivity(detailIntent);
                break;
            case R.id.btn_mvvm:
                Intent moveMvvm = new Intent(MainActivity.this, MainViewModelActivity.class);
                startActivity(moveMvvm);
                break;
        }
    }

    @Override
    public void postAsync() {
        showNotification(MainActivity.this, "Hi, how are you?", "Do you have any plan this weekend? Let's hangout", 110);
    }

    private static class DelayAsync extends AsyncTask<Void, Void, Void> {
        WeakReference<AsyncCallback> callback;
        DelayAsync(AsyncCallback callback) {
            this.callback = new WeakReference<>(callback);
        }
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            callback.get().postAsync();
        }
    }
    private void showNotification(Context context, String title, String message, int notifId) {
        String CHANNEL_ID = "Channel_1";
        String CHANNEL_NAME = "Navigation channel";
        Intent notifDetailIntent = new Intent(this, DetailBackstackActivity.class);
        notifDetailIntent.putExtra(DetailBackstackActivity.EXTRA_TITLE, title);
        notifDetailIntent.putExtra(DetailBackstackActivity.EXTRA_MESSAGE, message);
        PendingIntent pendingIntent = TaskStackBuilder.create(this)
                .addParentStack(DetailBackstackActivity.class)
                .addNextIntent(notifDetailIntent)
                .getPendingIntent(110, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_alarm_black)
                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.black))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(alarmSound)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});
            builder.setChannelId(CHANNEL_ID);
            if (notificationManagerCompat != null) {
                notificationManagerCompat.createNotificationChannel(channel);
            }
        }
        Notification notification = builder.build();
        if (notificationManagerCompat != null) {
            notificationManagerCompat.notify(notifId, notification);
        }
    }
}

interface AsyncCallback {
    void postAsync();
}
