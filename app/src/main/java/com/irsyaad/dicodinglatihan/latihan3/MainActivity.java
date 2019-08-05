package com.irsyaad.dicodinglatihan.latihan3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.irsyaad.dicodinglatihan.latihan3.alarm.AlarmManagerActivity;
import com.irsyaad.dicodinglatihan.latihan3.asyntask.AsynctaskActivity;
import com.irsyaad.dicodinglatihan.latihan3.broadcast.BroadcastActivity;
import com.irsyaad.dicodinglatihan.latihan3.gcmnetwork.GcmNetworkManagerActivity;
import com.irsyaad.dicodinglatihan.latihan3.jobscheduler.JobSchedullerActivity;
import com.irsyaad.dicodinglatihan.latihan3.service.ServiceActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAsyntask, btnService,btnBroadcast, btnAlarm, btnJob, btnGcm;

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
    }

    private void init(){
        btnAsyntask = findViewById(R.id.btn_asyntask);
        btnService = findViewById(R.id.btn_service);
        btnBroadcast = findViewById(R.id.btn_broadcast);
        btnAlarm = findViewById(R.id.btn_alarm);
        btnJob = findViewById(R.id.btn_job);
        btnGcm = findViewById(R.id.btn_gcm);
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
        }
    }
}
