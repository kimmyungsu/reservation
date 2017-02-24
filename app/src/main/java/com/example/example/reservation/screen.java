package com.example.example.reservation;



import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class screen extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen);
        Handler handler = new Handler() {
            public void handleMessage(Message msg){
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(0, 3000);
    }
}

