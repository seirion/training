package com.hpcnt.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import rx.Subscription;

public class MainActivity extends AppCompatActivity {

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
