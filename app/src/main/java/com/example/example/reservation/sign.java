package com.example.example.reservation;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class sign extends AppCompatActivity {
    EditText id, pw, name, cattle, age, phone;
    Button btn1, btn2;
    TextView sign;
    String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign);
        setTitle("reservation");
        sign = (TextView) findViewById(R.id.sign);
        id = (EditText) findViewById(R.id.id);
        pw = (EditText) findViewById(R.id.pw);
        name = (EditText) findViewById(R.id.name);
        cattle = (EditText) findViewById(R.id.cattle);
        age = (EditText) findViewById(R.id.age);
        phone = (EditText) findViewById(R.id.phone);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID = id.getText().toString();
                String PassWord = pw.getText().toString();
                String Name = name.getText().toString();
                String Gender = cattle.getText().toString();
                int Age = Integer.parseInt(age.getText().toString());
                int Phone = Integer.parseInt(phone.getText().toString());

                Jc jc = new Jc();

                res = jc.yy(ID);
                if (res.equals("same")) {
                    Toast.makeText(sign.this, "ID중복", Toast.LENGTH_SHORT).show();
                } else if (res.equals("ss")) {
                    Toast.makeText(sign.this, "회원 가입 완료", Toast.LENGTH_SHORT).show();
                    Db(ID, PassWord, Name, Gender, Age, Phone);
                    finish();

                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Db(String id, String pw, String name, String cattle, int age, int phone) {
        class ID extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                try {
                    String id = params[0];
                    String pw = params[1];
                    String name = params[2];
                    String cattle = params[3];
                    String age = params[4];
                    String phone = params[5];
                    String link = "http://inwon.zz.am/audtn/reservation.php";
                    String data = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
                    data += "&" + URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(pw, "UTF-8");
                    data += "&" + URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
                    data += "&" + URLEncoder.encode("Gender", "UTF-8") + "=" + URLEncoder.encode(cattle, "UTF-8");
                    data += "&" + URLEncoder.encode("Age", "UTF-8") + "=" + URLEncoder.encode(age, "UTF-8");
                    data += "&" + URLEncoder.encode("PhoneNumber", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8");
                    URL url = new URL(link);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDefaultUseCaches(false);
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");
                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                    wr.write(data);
                    wr.flush();
                    wr.close();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
                    StringBuffer sb = new StringBuffer();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                } catch (Exception e) {
                    return new String("Exception: " + e.getMessage());
                }
            }
        }
        ID task = new ID();
        task.execute(id, pw, name, cattle, String.valueOf(age), String.valueOf(phone));
    }
}
