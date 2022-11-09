package com.example.applicationpbl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

    }

    //テキストを押すとコレカラダのリンクに飛ぶ
    public void onClickURL(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://corekarada.com/"));
        startActivity(intent);
    }

    //ボタンを押したときの動作
    public void onClickButton(View view){
        Uri uri = Uri.parse("tel:08083753335");
        Intent i = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(i);
    }
    //test
}