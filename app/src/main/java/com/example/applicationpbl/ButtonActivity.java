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

       // WebView myWebView = findViewById(R.id.linkText);
        //myWebView.setWebViewClient(new WebViewClient());
        //myWebView.loadUrl("https://corekarada.com/");
        //myWebView.getSettings().setJavaScriptEnabled(true);


    }

    //ボタンを押したときの動作
    public void onClickButton(View view){
        Button button = findViewById(R.id.button1);
        button.setText("Click");
    }
    //test
}