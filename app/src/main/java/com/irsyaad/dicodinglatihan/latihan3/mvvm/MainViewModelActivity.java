package com.irsyaad.dicodinglatihan.latihan3.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.irsyaad.dicodinglatihan.latihan3.R;
import com.irsyaad.dicodinglatihan.latihan3.mvvm.adapter.WeatherAdapter;
import com.irsyaad.dicodinglatihan.latihan3.mvvm.model.WeatherItems;
import com.irsyaad.dicodinglatihan.latihan3.mvvm.viewmodel.MainViewModel;

import java.util.ArrayList;

import cz.msebera.android.httpclient.util.TextUtils;

public class MainViewModelActivity extends AppCompatActivity {

    private WeatherAdapter adapter;
    private EditText edtCity;
    private ProgressBar progressBar;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_model);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getWeathers().observe(this, getWeather);

        adapter = new WeatherAdapter();
        adapter.notifyDataSetChanged();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        edtCity = findViewById(R.id.editCity);
        progressBar = findViewById(R.id.progressBar);

        findViewById(R.id.btnCity).setOnClickListener(myListener);
    }

    private Observer<ArrayList<WeatherItems>> getWeather = new Observer<ArrayList<WeatherItems>>() {
        @Override
        public void onChanged(ArrayList<WeatherItems> weatherItems) {
            if (weatherItems != null) {
                adapter.setData(weatherItems);
                showLoading(false);

            }
        }
    };

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String city = edtCity.getText().toString();
            if (TextUtils.isEmpty(city)) return;

            mainViewModel.setWeather(city);
            showLoading(true);
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
