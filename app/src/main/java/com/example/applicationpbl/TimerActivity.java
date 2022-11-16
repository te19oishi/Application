package com.example.applicationpbl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




public class TimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        final String START_MESSAGE = "START";
        final String STOP_MESSAGE = "STOP";
        final String FINISH_MESSAGE = "カウント終了";
        Button StartButton = findViewById(R.id.StartButton);
        Button StopButton = findViewById(R.id.StopButton);
        final EditText editTime = findViewById(R.id.editTime);
        final EditText editTime2 = findViewById(R.id.editTime2);
        final EditText editNumber = findViewById(R.id.editNumber);
        final CountDownTimer[] cdt = new CountDownTimer[1];

        // スタートボタンイベントリスナー
        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // スタートトースト
                Toast toast = Toast.makeText(TimerActivity.this, START_MESSAGE, Toast.LENGTH_SHORT);
                toast.show();

                // 入力取得(運動時間)
                int inputTime = Integer.parseInt(String.valueOf(editTime.getText()));

                // 入力取得(インターバル)
                int inputTime2 = Integer.parseInt(String.valueOf(editTime2.getText()));

                // 入力取得(インターバル)
                int inputNumber = Integer.parseInt(String.valueOf(editNumber.getText()));

                // 回数用変数
                int i = 1;

                while (i < inputNumber){
                    // カウントダウンタイマー(運動時間)
                    cdt[0] = new CountDownTimer(inputTime * 1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            editTime.setText(Long.toString(millisUntilFinished / 1000));
                        }

                        @Override
                        public void onFinish() {

                            // 終了トースト
                            Toast toast = Toast.makeText(TimerActivity.this, FINISH_MESSAGE, Toast.LENGTH_SHORT);
                            toast.show();

                        }
                    }.start();

                    if (inputTime == 0) {
                        // カウントダウンタイマー(インターバル)
                        cdt[0] = new CountDownTimer(inputTime2 * 1000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                editTime2.setText(Long.toString(millisUntilFinished / 1000));
                            }

                            @Override
                            public void onFinish() {

                                // 終了トースト
                                Toast toast = Toast.makeText(TimerActivity.this, FINISH_MESSAGE, Toast.LENGTH_SHORT);
                                toast.show();

                            }
                        }.start();
                    }
                    if(inputTime2 == 0){
                        i++;
                    }
                }
            }
        });

        // ストップボタンイベントリスナー
        StopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // ストップトースト
                Toast toast = Toast.makeText(TimerActivity.this, STOP_MESSAGE, Toast.LENGTH_SHORT);
                toast.show();

                // カウントダウンタイマー停止
                cdt[0].cancel();
            }
        });
     
    }

    public void onClickStamp(View v) {  //スタンプ画面への移動
        // ①インテントの作成
        Intent intent = new Intent(this, StampActivity.class);

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
