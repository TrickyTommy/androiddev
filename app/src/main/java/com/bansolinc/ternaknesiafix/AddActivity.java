package com.bansolinc.ternaknesiafix;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.*;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<String> listitem = new ArrayList<>();
    ArrayAdapter<String> adapterKategori;
    ///
    ArrayList<String> listitemstatus = new ArrayList<>();
    ArrayAdapter<String> adapterstatus;


    private EditText txtharga_beli, txtbobot_beli, txttinggi_hewan, status_hewan;
    private Spinner spinnerkategori, spinnerstatus;
    private Button btn_simpan;
    private ProgressDialog progressDialog;
    private String JSON_STRING;
    private Spinner spNamen2;
    private String kategori_id;
     int id_kategori = 0;
    int id_status = 0;
    int id_bobot = 0;
    private String[] jenis_kelamin = {
            "Jantan",
            "Betina"
    };
    Spinner spkategori, spstatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.activity_add);
        txtharga_beli = (EditText) findViewById(R.id.harga_beli);
        txtbobot_beli = (EditText) findViewById(R.id.bobot_beli);
        txttinggi_hewan = (EditText) findViewById(R.id.tinggi_hewan);
        btn_simpan = (Button) findViewById(R.id.btn_simpan);
        progressDialog = new ProgressDialog(this);
        btn_simpan.setOnClickListener(this);
        ///
        spkategori = (Spinner) findViewById(R.id.spinner);
        adapterKategori = new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.txt, listitem);
        spkategori.setAdapter(adapterKategori);
        ///
        spstatus = (Spinner) findViewById(R.id.spinnerstatus);

        adapterstatus = new ArrayAdapter<String>(this, R.layout.spinnerstatus_layout, R.id.txtstatus, listitemstatus);
        spstatus.setAdapter(adapterstatus);
        ///

        spNamen2 = (Spinner) findViewById(R.id.spinner_jenis_kelamin);

        // inisialiasi Array Adapter dengan memasukkan string array di atas
        final ArrayAdapter<String> adapterjenkel = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, jenis_kelamin);

        // mengeset Array Adapter tersebut ke Spinner
        spNamen2.setAdapter(adapterjenkel);
        //


        // mengeset listener untuk mengetahui saat item dipilih
        spNamen2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // memunculkan toast + value Spinner yang dipilih (diambil dari adapter)
                Toast.makeText(AddActivity.this, "Selected " + adapterjenkel.getItem(i), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ///
        spkategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AddActivity.this, "Selected " + spkategori.getSelectedItem(), Toast.LENGTH_SHORT).show();
                kategori_id = spkategori.getSelectedItem().toString().trim();
                //final String id_kategori2 = String.valueOf(id_kategori);
                int id_kategori = 0;
                // String pilih = spkategori.getSelectedItem().toString();
                //Log.d(pilih, "kategori");
//                if (kategori_id == "Sapi") {
//                    id_kategori = 1;
//
//                } else if (kategori_id == "Kambing") {
//                    id_kategori = 2;
//
//
//                } else if (kategori_id == "Domba") {
//                    id_kategori = 3;
//
//
//                }
//
//                Toast.makeText(AddActivity.this, "id_kategori " + id_kategori, Toast.LENGTH_SHORT).show();
//                Log.d(String.valueOf(id_kategori), "id_kategori");


                // int kategori = id_kategori;


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    protected void onStart() {

        super.onStart();
        BackTask bt = new BackTask();
        status st = new status();
        st.execute();
        bt.execute();


    }
    ///


    ////
    private class BackTask extends AsyncTask<Void, Void, Void> {
        ArrayList<String> list;
        ArrayList<String> listid;

        protected void onPreExecute() {
            super.onPreExecute();
            list = new ArrayList<>();
            listid = new ArrayList<>();
        }

        protected Void doInBackground(Void... params) {
            InputStream is = null;
            String result = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://trickyserver.ddns.net/connectandroid/includes/tampilCombobox.php");
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                // Get our response as a String.
                is = entity.getContent();
                Log.d("fungsi", is.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            //convert response to string
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;

                }
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // param json string

            try {
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    list.add(jsonObject.getString("nama_kategori"));
                    listid.add(jsonObject.getString("id_kategori"));
                    Log.d("kom", jsonObject.getString("id_kategori"));

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            listitem.addAll(list);
            adapterKategori.notifyDataSetChanged();

        }
    }

    private class status extends AsyncTask<Void, Void, Void> {
        ArrayList<String> liststatus;

        protected void onPreExecute() {
            super.onPreExecute();
            liststatus = new ArrayList<>();
        }

        protected Void doInBackground(Void... params) {
            InputStream isstatus = null;
            String resultstatus = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://trickyserver.ddns.net/connectandroid/includes/combostatus.php");
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                // Get our response as a String.
                isstatus = entity.getContent();
                Log.d("fungsi", isstatus.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            //convert response to string
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(isstatus, "utf-8"));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    resultstatus += line;

                }
                isstatus.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // param json string

            try {
                JSONArray jsonArray = new JSONArray(resultstatus);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    liststatus.add(jsonObject.getString("status"));
                    Log.d("rom", jsonObject.getString("status"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            listitemstatus.addAll(liststatus);
            adapterstatus.notifyDataSetChanged();
            ///

            //

        }
    }



    private void tambahbeli() {

        final String bobot_beli = txtbobot_beli.getText().toString().trim();
        final String harga_beli = txtharga_beli.getText().toString().trim();
        final String tinggi_hewan = txttinggi_hewan.getText().toString().trim();


        switch (kategori_id){
            case "Sapi":
                id_kategori = 1;
                break;
            case "Kambing":
                id_kategori = 2;
                break;
            case "Domba":
                id_kategori = 3;
                break;
        }
        Toast.makeText(AddActivity.this, "id_kategori " + id_kategori, Toast.LENGTH_SHORT).show();

        String pilihstatus = spstatus.getSelectedItem().toString();

//        if (pilihstatus == "HIDUP") {
//            id_status = 1;
//        }
//        if (pilihstatus == "SAKIT") {
//            id_status = 2;
//        }
//        if (pilihstatus == "MATI") {
//            id_status = 3;
//        }

        switch (pilihstatus) {
            case "HIDUP":
                id_status = 1;
                break;
            case "SAKIT":
                id_status = 2;
                break;
            case "MATI":
                id_status = 3;
                break;
        }

        Toast.makeText(AddActivity.this, "Status " + id_status, Toast.LENGTH_SHORT).show();

        //
        int hasilbobot = Integer.parseInt(bobot_beli);
        if (hasilbobot <= 300) {
            id_bobot = 1;
        }
        if (hasilbobot > 300 && hasilbobot < 500) {
            id_bobot = 2;
        }
        if (hasilbobot > 500 && hasilbobot < 700) {
            id_bobot = 3;
        }
        if (hasilbobot > 700 && hasilbobot < 900) {
            id_bobot = 4;
        }
        //
        final String pilihjenis = spNamen2.getSelectedItem().toString();


        progressDialog.setMessage("Data ditambahkan ...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_ADD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();


                Log.d(tinggi_hewan, "masuk");
                params.put("bobot_beli", bobot_beli);
                params.put("harga_beli", harga_beli);
                params.put("tinggi_hewan", tinggi_hewan);
                params.put("id_kategori", String.valueOf((id_kategori)));
                //Log.d(String.valueOf(id_kategori2), String.valueOf(id_kategori2));
                params.put("id_status", String.valueOf(id_status));
                params.put("id_berat", String.valueOf(id_bobot));
                params.put("jenis_kelamin", pilihjenis);

                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
/////////////


    ///////////


    @Override
    public void onClick(View v) {
        if (v == btn_simpan) {

            Log.d("Array", listitem.toString());
            tambahbeli();

        }
    }
}
