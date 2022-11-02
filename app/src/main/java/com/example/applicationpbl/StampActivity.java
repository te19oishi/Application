package com.example.applicationpbl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        Button btClick = findViewById(R.id.stamp_scan);
        //リスナクラスのインスタンス生成
        ReadQrcodeListener listener_qr = new ReadQrcodeListener();
        //表示ボタンにリスナを設定
        btClick.setOnClickListener(listener_qr);

    }

    /*private class IncrementListener implements View.OnClickListener {
        int num = 1;
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

    int num = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String str = "コレカラダ";
        String pass = "20192024";
        int password = 3;
        int password2 = 3;

        // QRコードを読み込んだ後の処理
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            //成功
            if(str.equals(result.getContents())) {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                TextView output = findViewById(R.id.stamp);
                output.setText(num + "points");
                num++;
            }
            //失敗
            else {
                Toast.makeText(this, "Cancelled\n" + System.getProperty(str) + ":str=" + str + "\n"
                        + System.getProperty(result.getContents()) + ":scan=" + result.getContents(), Toast.LENGTH_LONG).show();
            }
        }

        else {
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