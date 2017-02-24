package com.example.example.reservation;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Lc extends AppCompatActivity {
    String res;
    String id;
    String pw;
    String name;
    String cattle;
    int age;
    int phone;

    ArrayList Id = new ArrayList();
    ArrayList Pw = new ArrayList();


    public String getres() {
        return res;

    }


    public void aa(String cid, String cpw) {
        id = cid;
        pw = cpw;


        class lc extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... strings) {
                StringBuilder sb = new StringBuilder();
                try {
                    String link = "http://1.224.44.55/audtn/reservation2.php";
                    URL url = new URL(link);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDefaultUseCaches(false);
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                        break;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                res = ja(sb.toString());
                Log.e("resres", res);
                return res;

            }
        }
        try {
            res = new lc().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    String ja(String j) {
        String cid, cpw;
        String r="";
        try {
            JSONArray a = new JSONArray(j);
            for (int i = 0; i < a.length(); i++) {
                JSONObject o = a.getJSONObject(i);

                cid = o.getString("ID");
                cpw = o.getString("Password");
                Log.e("cidcid", cid);
                Id.add(cid);
                Log.e("ididid", Id.toString());
                Pw.add(cpw);
                Log.e("pwpwpw", Pw.toString());
            }
        } catch (JSONException e) {
        }
        for (int b = 0; b < Id.size(); b++) {
            Log.e("iiiddd", Id.get(b).toString());
            Log.e("pppwww", Pw.get(b).toString());
            if (id.equals(Id.get(b).toString().trim()) && pw.equals(Pw.get(b).toString().trim())) {
                res = "Su";
                break;
            } else {
                res = "fail";
            }
        }
        return res;
    }
}

