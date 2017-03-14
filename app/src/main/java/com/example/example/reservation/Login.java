package com.example.example.reservation;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button Login, sign;
    EditText id, pw;
    String ida, pa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        startActivity(new Intent(Login.this, screen.class));
        Login = (Button) findViewById(R.id.lgbtn);
        sign = (Button) findViewById(R.id.sigbtn);
        id = (EditText) findViewById(R.id.login);
        pw = (EditText) findViewById(R.id.password);


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in;
                in = new Intent(Login.this, sign.class);
                startActivity(in);
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String res = "";
                ida = id.getText().toString();
                pa = pw.getText().toString();
                Lc lc = new Lc();
                lc.aa(ida, pa);
                res = lc.getres();
                Log.e("idaida", ida + "," + pa + "," + res);
                if (res.equals("Su")) {
                    id.setText("");
                    pw.setText("");
                    Toast.makeText(Login.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, reservation.class);
                    startActivity(i);
                } else if (res.equals("fail")) {
                    id.setText("");
                    pw.setText("");
                    Toast.makeText(Login.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(ida) || TextUtils.isEmpty(pa)) {
                    Toast.makeText(Login.this, "ID와 PW를 다시 입력하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

