package com.example.applicationpbl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class StampActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp);

        /*//Buttonオブジェクトの取得
        Button btClick = findViewById(R.id.stamp_inc);
        //リスナクラスのインスタンス生成
        IncrementListener listener = new IncrementListener();
        //表示ボタンにリスナを設定
        btClick.setOnClickListener(listener);*/


        //Buttonオブジェクトの取得
        Button btClick = findViewById(R.id.stamp_inc);
        //リスナクラスのインスタンス生成
        ReadQrcodeListener listener_qr = new ReadQrcodeListener();
        //表示ボタンにリスナを設定
        btClick.setOnClickListener(listener_qr);

    }

    /*private class IncrementListener implements View.OnClickListener {
        int num = 0;
        public void onClick(View view){
            TextView output = findViewById(R.id.stamp);
            output.setText(num + "points");
            num++;
        }
    }*/


    //リスナクラスの定義
    private class ReadQrcodeListener implements View.OnClickListener {
        public void onClick(View view){
            scanQrCode();
        }
    }



    //QRコードリーダーを起動する
    public void scanQrCode() {
        new IntentIntegrator(this)
                //画面の向きを固定
                .setOrientationLocked(false)
                //バーコードのフォーマットをQRコードにする
                .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                .initiateScan();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // QRコードを読み込んだ後の処理
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null && result.getContents() != null) {
            // QRコードのデータを取得する
            Log.d("StampActivity", result.getContents());
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
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