package com.example.applicationpbl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class ButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
    }

    public void onClickButton(View view){
        Uri number = Uri.parse("tel:1111111111");
        Intent intent;
        intent = new Intent(Intent.ACTION_DIAL, number);

        startActivity(intent);
    }

    //test
}