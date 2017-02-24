package com.example.example.reservation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class reservation extends AppCompatActivity {

    Button btnEnd;
    DatePicker calView;
    RadioButton rdoCal, rdoTime, rda, s1, s2, s3, s4, s5;
    TimePicker tPicker;
    TextView tvYear, tvMonth, tvDay, tvHour, tvMinute, name, gen;
    EditText ede;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);
        setTitle("영화관 좌석 예약");


        rdoCal = (RadioButton) findViewById(R.id.rdoCal);
        rdoTime = (RadioButton) findViewById(R.id.rdoTime);
        rda = (RadioButton) findViewById(R.id.rda);
        s1 = (RadioButton) findViewById(R.id.s1);
        s2 = (RadioButton) findViewById(R.id.s2);
        s3 = (RadioButton) findViewById(R.id.s3);
        s4 = (RadioButton) findViewById(R.id.s4);
        s5 = (RadioButton) findViewById(R.id.s5);
        tPicker = (TimePicker) findViewById(R.id.timePicker1);
        calView = (DatePicker) findViewById(R.id.dp1);
        tvYear = (TextView) findViewById(R.id.tvYear);
        tvMonth = (TextView) findViewById(R.id.tvMv);
        tvDay = (TextView) findViewById(R.id.tvM);
        tvHour = (TextView) findViewById(R.id.tvMp);
        tvMinute = (TextView) findViewById(R.id.tvMw);
        gen = (TextView) findViewById(R.id.gen);
        name = (TextView) findViewById(R.id.name);
        tPicker.setVisibility(View.INVISIBLE);
        calView.setVisibility(View.INVISIBLE);
        btnEnd = (Button) findViewById(R.id.btnEnd);


        rdoCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tPicker.setVisibility(View.INVISIBLE);
                ede.setVisibility(View.INVISIBLE);
                calView.setVisibility(View.VISIBLE);
                s1.setVisibility(View.INVISIBLE);
                s2.setVisibility(View.INVISIBLE);
                s3.setVisibility(View.INVISIBLE);
                s4.setVisibility(View.INVISIBLE);
                s5.setVisibility(View.INVISIBLE);
            }
        });

        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tPicker.setVisibility(View.VISIBLE);
                ede.setVisibility(View.VISIBLE);
                calView.setVisibility(View.INVISIBLE);
                s1.setVisibility(View.INVISIBLE);
                s2.setVisibility(View.INVISIBLE);
                s3.setVisibility(View.INVISIBLE);
                s4.setVisibility(View.INVISIBLE);
                s5.setVisibility(View.INVISIBLE);
            }
        });
        rda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tPicker.setVisibility(View.INVISIBLE);
                calView.setVisibility(View.INVISIBLE);
                ede.setVisibility(View.INVISIBLE);
                s1.setVisibility(View.VISIBLE);
                s2.setVisibility(View.VISIBLE);
                s3.setVisibility(View.VISIBLE);
                s4.setVisibility(View.INVISIBLE);
                s5.setVisibility(View.INVISIBLE);
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
                s1.setText("A석");
            }
        });
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s2.setText("B석");
            }
        });
        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s3.setText("C석");
            }
        });
        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s4.setText("D석");
            }
        });
        s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s5.setText("E석");
            }
        });
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = "";
                String gette = String.valueOf(Integer.parseInt(ede.getText().toString()));
                if (gette.getBytes().length <= 0) {
                    Toast.makeText(reservation.this, "좌석을 설정 하세요", Toast.LENGTH_SHORT).show();
                    e = "";
                } else {
                    e = gette;
                }
                String year = String.format("%d", calView.getYear());
                String month = String.format("%d", calView.getMonth() + 1);
                String day = String.format("%d", calView.getDayOfMonth());
                tvYear.setText(Integer.toString(Integer.parseInt(year)));
                tvMonth.setText(Integer.toString(Integer.parseInt(month)));
                tvDay.setText(Integer.toString(Integer.parseInt(day)));
                tvHour.setText(Integer.toString(tPicker.getCurrentHour()));
                tvMinute.setText(Integer.toString(tPicker.getCurrentMinute()));


                int ty = Integer.parseInt(tvYear.getText().toString());
                int tm = Integer.parseInt(tvMonth.getText().toString());
                int td = Integer.parseInt(tvDay.getText().toString());
                int th = Integer.parseInt(tvHour.getText().toString());
                int tM = Integer.parseInt(tvMinute.getText().toString());


            }
        });
    }
}
