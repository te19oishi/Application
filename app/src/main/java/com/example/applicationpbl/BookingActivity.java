package com.example.applicationpbl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.net.Uri;
import android.text.util.Linkify;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class BookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
    }
    
    //テキストを押すとコレカラダのリンクに飛ぶ
    public void onClickURL(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://corekarada.com/"));
        startActivity(intent);
    }

    //ボタンを押したときの動作
    public void onClickButton(View view){
        Uri uri = Uri.parse("tel:0963216370");
        Intent i = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(i);
    }

    public void onClickStamp(View v) {  //スタンプ画面への移動
        // ①インテントの作成
        Intent intent = new Intent(this, StampActivity.class);

        // ②遷移先画面の起動
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void onClickTimer(View v) {  //タイマー画面への移動
        // ①インテントの作成
        Intent intent = new Intent(this, TimerActivity.class);

        // ②遷移先画面の起動
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
