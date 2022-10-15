package com.example.applicationpbl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StampActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp);
    }

    public void onClickTImer(View v) {  //タイマー画面への移動
        // ①インテントの作成
        Intent intent = new Intent(this, TimerActivity.class);

        // ②遷移先画面の起動
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void onClickBooking(View v) {    //予約画面への移動
        // ①インテントの作成
        Intent intent = new Intent(this, BookingActivity.class);

        // ②遷移先画面の起動
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}