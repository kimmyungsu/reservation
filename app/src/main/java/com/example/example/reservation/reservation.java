package com.example.example.reservation;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class reservation extends AppCompatActivity {

    Button btnEnd;
    RadioButton rdoCal, rdoTime, rda, s1, s2, s3, s4, s5, s6;
    DatePicker calView, datePicker;
    TimePicker tPicker;
    TextView tvYear, tvMonth, tvDay, tvHour, tvMinute, name, age, seat;
    Lc lc;
    Intent a;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);
        a = getIntent();
        setTitle("영화관 좌석 예약");
        lc = new Lc();
        rdoCal = (RadioButton) findViewById(R.id.rdoCal);
        rdoTime = (RadioButton) findViewById(R.id.rdoTime);
        rda = (RadioButton) findViewById(R.id.rda);
        s1 = (RadioButton) findViewById(R.id.s1);
        s2 = (RadioButton) findViewById(R.id.s2);
        s3 = (RadioButton) findViewById(R.id.s3);
        s4 = (RadioButton) findViewById(R.id.s4);
        s5 = (RadioButton) findViewById(R.id.s5);
        s6 = (RadioButton) findViewById(R.id.s6);
        tPicker = (TimePicker) findViewById(R.id.timePicker1);
        calView = (DatePicker) findViewById(R.id.datePicker1);
        tvYear = (TextView) findViewById(R.id.tvYear);
        tvMonth = (TextView) findViewById(R.id.tvMonth);
        tvDay = (TextView) findViewById(R.id.tvDay);
        tvHour = (TextView) findViewById(R.id.tvHour);
        tvMinute = (TextView) findViewById(R.id.tvMinute);
        name = (TextView) findViewById(R.id.name);
        age = (TextView) findViewById(R.id.age);
        seat = (TextView) findViewById(R.id.seat);
        btnEnd = (Button) findViewById(R.id.btnEnd);

        tPicker.setVisibility(View.INVISIBLE);
        calView.setVisibility(View.INVISIBLE);
        s1.setVisibility(View.INVISIBLE);
        s2.setVisibility(View.INVISIBLE);
        s3.setVisibility(View.INVISIBLE);
        s4.setVisibility(View.INVISIBLE);
        s5.setVisibility(View.INVISIBLE);
        s6.setVisibility(View.INVISIBLE);


        rdoCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tPicker.setVisibility(View.INVISIBLE);
                calView.setVisibility(View.VISIBLE);
                s1.setVisibility(View.INVISIBLE);
                s2.setVisibility(View.INVISIBLE);
                s3.setVisibility(View.INVISIBLE);
                s4.setVisibility(View.INVISIBLE);
                s5.setVisibility(View.INVISIBLE);
                s6.setVisibility(View.INVISIBLE);
            }
        });

        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tPicker.setVisibility(View.VISIBLE);
                calView.setVisibility(View.INVISIBLE);
                s1.setVisibility(View.INVISIBLE);
                s2.setVisibility(View.INVISIBLE);
                s3.setVisibility(View.INVISIBLE);
                s4.setVisibility(View.INVISIBLE);
                s5.setVisibility(View.INVISIBLE);
                s6.setVisibility(View.INVISIBLE);
            }
        });

        rda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tPicker.setVisibility(View.INVISIBLE);
                calView.setVisibility(View.INVISIBLE);
                s1.setVisibility(View.VISIBLE);
                s2.setVisibility(View.VISIBLE);
                s3.setVisibility(View.VISIBLE);
                s4.setVisibility(View.VISIBLE);
                s5.setVisibility(View.VISIBLE);
                s6.setVisibility(View.VISIBLE);
            }
        });

        calView.init(calView.getYear(), calView.getMonth(), calView.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tvYear.setText(String.format("%d", year));
                        tvMonth.setText(String.format("%d", monthOfYear + 1));
                        tvDay.setText(String.format("%d", dayOfMonth));
                    }
                });

        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seat.setText("B석");
            }
        });
        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seat.setText("C석");
            }
        });
        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seat.setText("D석");
            }
        });
        s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seat.setText("E석");
            }
        });
        s6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seat.setText("F석");
            }
        });
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String year = String.format("%d", calView.getYear());
                String month = String.format("%d", calView.getMonth() + 1);
                String day = String.format("%d", calView.getDayOfMonth());
                tvYear.setText(Integer.toString(Integer.parseInt(year)));
                tvMonth.setText(Integer.toString(Integer.parseInt(month)));
                tvDay.setText(Integer.toString(Integer.parseInt(day)));
                tvHour.setText(Integer.toString(tPicker.getCurrentHour()));
                tvMinute.setText(Integer.toString(tPicker.getCurrentMinute()));
                name.setText(a.getStringExtra("name"));
                age.setText(a.getStringExtra("age"));

                String na = name.getText().toString();
                int ag = Integer.parseInt(age.getText().toString());
                String se = seat.getText().toString();
                String ye = tvYear.getText().toString();
                String mo = tvMonth.getText().toString();
                String da = tvDay.getText().toString();
                String ho = tvHour.getText().toString();
                String mi = tvMinute.getText().toString();

                Db(na, ag, se, ye, mo, da, ho, mi);
                Toast.makeText(reservation.this, "서버저장완료", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Db(String name, int age, String seat, String year, String month, String day, String hour, String minute) {
        class ID extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                try {
                    String name = params[0];
                    String age = params[1];
                    String seat = params[2];
                    String year = params[3];
                    String month = params[4];
                    String day = params[5];
                    String hour = params[6];
                    String minute = params[7];
                    String link = "http://1.224.44.55/audtn/time.php";
                    String data = URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
                    data += "&" + URLEncoder.encode("Age", "UTF-8") + "=" + URLEncoder.encode(age, "UTF-8");
                    data += "&" + URLEncoder.encode("Seat", "UTF-8") + "=" + URLEncoder.encode(seat, "UTF-8");
                    data += "&" + URLEncoder.encode("Year", "UTF-8") + "=" + URLEncoder.encode(year, "UTF-8");
                    data += "&" + URLEncoder.encode("Month", "UTF-8") + "=" + URLEncoder.encode(month, "UTF-8");
                    data += "&" + URLEncoder.encode("Day", "UTF-8") + "=" + URLEncoder.encode(day, "UTF-8");
                    data += "&" + URLEncoder.encode("Hour", "UTF-8") + "=" + URLEncoder.encode(hour, "UTF-8");
                    data += "&" + URLEncoder.encode("Minute", "UTF-8") + "=" + URLEncoder.encode(minute, "UTF-8");
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
                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
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
        task.execute(name, String.valueOf(age), seat, year, month, day, hour, minute);
    }
}

