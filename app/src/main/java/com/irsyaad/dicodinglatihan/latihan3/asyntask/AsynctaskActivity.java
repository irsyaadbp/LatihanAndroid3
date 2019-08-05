package com.irsyaad.dicodinglatihan.latihan3.asyntask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.irsyaad.dicodinglatihan.latihan3.R;

import java.lang.ref.WeakReference;

public class AsynctaskActivity extends AppCompatActivity implements MyAsyncCallback {

    TextView txtStatus, txtDesc;

    final static String INPUT_STRING = "Halo Ini Demo AsyncTask!!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyntask);

        txtStatus = findViewById(R.id.txt_status);
        txtDesc = findViewById(R.id.txt_desc);

        DemoAsyntask demoAsync = new DemoAsyntask(this);
        demoAsync.execute(INPUT_STRING);

    }

    private static class DemoAsyntask extends AsyncTask<String, Void, String> {
        static final String LOG_ASYNC = "DemoAsync";
        WeakReference<MyAsyncCallback> myListener;
        DemoAsyntask(MyAsyncCallback myListener){
            this.myListener = new WeakReference<>(myListener);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.d(LOG_ASYNC, "status : onPreExecute");

            MyAsyncCallback myListener = this.myListener.get();
            if (myListener != null) myListener.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {

            Log.d(LOG_ASYNC, "status : doInBackground");

            String output = null;
            try {
                String input = strings[0];
                output = input + " Selamat Belajar!!";
                Thread.sleep(5000);
            } catch (Exception e) {
                Log.d(LOG_ASYNC, e.getMessage());
            }
            return output;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d(LOG_ASYNC, "status : onPostExecute");

            MyAsyncCallback myListener = this.myListener.get();
            if (myListener != null) myListener.onPostExecute(s);
        }
    }

    @Override
    public void onPreExecute() {
        txtStatus.setText(R.string.status_pre);
        txtDesc.setText(INPUT_STRING);
    }

    @Override
    public void onPostExecute(String text) {
        txtStatus.setText(R.string.status_post);
        if (text != null) txtDesc.setText(text);

    }
}
