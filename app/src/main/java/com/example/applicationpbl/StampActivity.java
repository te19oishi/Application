package com.example.applicationpbl;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class StampActivity extends AppCompatActivity {

    //グローバル変数の定義
    int stamp, ticket;
    TextView stamp_label, ticket_label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp);

        SharedPreferences sp = getSharedPreferences("STAMP_POINT", MODE_PRIVATE);

        stamp_label = findViewById(R.id.stamp_label);
        ticket_label = findViewById(R.id.ticket_label);

        //stampとticketに値を読み込む
        stamp = sp.getInt("STAMP", 0);
        ticket = sp.getInt("TICKET", 3);

        //ChargePointListenerの設定
        //Buttonオブジェクトの取得
        Button stamp_charge = findViewById(R.id.stamp_charge);
        //リスナクラスのインスタンス生成
        ChargePointListener stamp_cs = new ChargePointListener();
        //表示ボタンにリスナを設定
        stamp_charge.setOnClickListener(stamp_cs);

        //UseTicketListenerの設定
        //Buttonオブジェクトの取得
        Button stamp_use = findViewById(R.id.stamp_use);
        //リスナクラスのインスタンス生成
        UseTicketListener listener_ut = new UseTicketListener();
        //表示ボタンにリスナを設定
        stamp_use.setOnClickListener(listener_ut);

        //読み込んだ値をTextViewに書き込む
        stamp_label.setText("スタンプ：" + stamp + " / 15");
        ticket_label.setText("チケット：" + ticket);
    }

    //リスナクラスの定義
    private class ChargePointListener implements View.OnClickListener {
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

        // QRコードを読み込んだ後の処理
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() != null) {
                //成功(pass一致)
                if(str.equals(result.getContents())) {
                    Toast.makeText(this, "QRコードが一致しました。ポイントを加算します。: ", Toast.LENGTH_LONG).show();

                    stamp++;
                    //stampが満タンになったらリセットして、ticketを増やす
                    if(stamp == 15)
                    {
                        Toast.makeText(this, "スタンプが15枚たまりました。チケットを一枚プレゼントします。 ", Toast.LENGTH_LONG).show();
                        stamp = 0;
                        ticket++;
                    }

                    SharedPreferences sp = getSharedPreferences("STAMP_POINT", MODE_PRIVATE);

                    //変えた値を保存して、TextViewを書き換える
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt("STAMP", stamp);
                    editor.putInt("TICKET", ticket);
                    editor.apply();
                    stamp_label.setText("スタンプ：" + stamp + " / 15");
                    ticket_label.setText("チケット：" + ticket);
                }

                //失敗(pass不一致)
                else {
                    Toast.makeText(this, "QRコードが違います。\n" + System.getProperty(str) + ":str=" + str + "\n"
                            + System.getProperty(result.getContents()) + ":scan=" + result.getContents(), Toast.LENGTH_LONG).show();
                }
            }

            else {
                Toast.makeText(this, "キャンセルされました。", Toast.LENGTH_LONG).show();
            }
        }

        else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    //チケットを使うボタンを押したときの処理
    public class UseTicketListener implements View.OnClickListener {
        //CHARGEボタンをクリックしたら実行
        String password = "korekarada";
        @Override
        public void onClick(View view) {
            ////付与されているチケットが0より多ければダイアログを表示
            if(ticket > 0) {
                //テキスト入力を受け付けるビューを作成します。
                final EditText editView = new EditText(StampActivity.this);
                //文字を伏せ字にする
                editView.setInputType( InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);
                new AlertDialog.Builder(StampActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle("パスワードを入力してください")
                        //setViewにてビューを設定します。
                        .setView(editView)
                        //OKボタン
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                //passwordが一致したらチケットを使う
                                if (editView.getText().toString().equals(password)) {
                                /*
                                Toast.makeText(StampActivity.this,
                                        editView.getText().toString(),
                                        Toast.LENGTH_LONG).show();
                                        */

                                    Toast.makeText(StampActivity.this, "チケットを使用しました", Toast.LENGTH_LONG).show();
                                    ticket--;

                                    //付与されているチケットが0より多ければ、チケットを使う
                                    //if(ticket > 0){

                                    //}

                                    //else{
                                    //Toast.makeText(this, "わら", Toast.LENGTH_LONG).show();
                                    //}

                                    SharedPreferences sp = getSharedPreferences("STAMP_POINT", MODE_PRIVATE);

                                    //変えた値を保存して、TextViewを書き換える
                                    SharedPreferences.Editor editor = sp.edit();
                                    editor.putInt("TICKET", ticket);
                                    editor.apply();
                                    ticket_label.setText("チケット：" + ticket);
                                } else {
                                    Toast.makeText(StampActivity.this, "パスワードが違います", Toast.LENGTH_LONG).show();
                                }
                            }
                        })

                        //キャンセルボタン
                        .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .show();

            }
            //チケット=0のときの処理
            else{
                Toast.makeText(StampActivity.this, "チケットの数が0です", Toast.LENGTH_LONG).show();
            }
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