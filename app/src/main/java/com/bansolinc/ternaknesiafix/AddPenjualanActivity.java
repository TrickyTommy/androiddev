package com.bansolinc.ternaknesiafix;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
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

import static com.bansolinc.ternaknesiafix.BuildConfig.BASE_URL;

public class AddPenjualanActivity extends AppCompatActivity{
    String ServerURL = BASE_URL+"tambahpenjualan.php/" ;
    EditText tinggijual_hewan,harga_beli,bobot_jualkg,kode_hewan,hargajual_kg ;
    Button btn_simpanjual;
    String Temptinggijual_hewan,Tempharga_beli,Tempbobot_jualkg,Tempkode_hewan,TempHargajual_kg,txtharga_beli ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_penjualan);
        kode_hewan = (EditText)findViewById(R.id.kode_hewan_jual);
        tinggijual_hewan = (EditText)findViewById(R.id.tinggijual_hewan);
        harga_beli = (EditText)findViewById(R.id.harga_beli);
        bobot_jualkg = (EditText)findViewById(R.id.bobot_jualkg);

        hargajual_kg = (EditText)findViewById(R.id.hargajual_kg);
        btn_simpanjual = (Button)findViewById(R.id.btn_simpanjual);

        btn_simpanjual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetData();

                InsertData(Tempkode_hewan,Tempharga_beli,Tempbobot_jualkg,Temptinggijual_hewan);

            }
        });

        harga_beli = (EditText) findViewById(R.id.harga_beli);


        hargajual_kg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!bobot_jualkg.getText().toString().isEmpty()&& !s.toString().isEmpty()){
                    Integer angka1 = Integer.parseInt(bobot_jualkg.getText().toString());
                    Integer angka2 = Integer.parseInt(s.toString());
                    Integer result = angka1 * angka2;

                    harga_beli.setText(Integer.toString(result));

                    txtharga_beli = (harga_beli.getText().toString());

                    /// Log.d("rom", String.valueOf(txtharga_beli.getText()));
                    Log.d("rom", txtharga_beli);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        bobot_jualkg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!hargajual_kg.getText().toString().isEmpty() && !s.toString().isEmpty()){
                    int angka1 = Integer.parseInt(hargajual_kg.getText().toString());
                    Integer angka2 = Integer.parseInt(s.toString());
                    Integer result = angka1 * angka2;
                    harga_beli.setText(Integer.toString(result));

                    txtharga_beli = (harga_beli.getText().toString());

                    /// Log.d("rom", String.valueOf(txtharga_beli.getText()));
                    Log.d("rom", txtharga_beli);
                    // txtharga_beli=hasil;
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void GetData(){

        Tempkode_hewan = kode_hewan.getText().toString();
        Temptinggijual_hewan = tinggijual_hewan.getText().toString();
        Tempharga_beli = harga_beli.getText().toString();
        Tempbobot_jualkg = bobot_jualkg.getText().toString();

      //  TempHargajual_kg = hargajual_kg.getText().toString();

    }

    public void InsertData(final String id_hewan, final String harga_jual, final String bobot_jual, final String tinggi_jual){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String IdhewanHolder = id_hewan ;
                String HargajualHolder = harga_jual ;
                String BobotJualHolder = bobot_jual ;
                String TinggiJualHolder = tinggi_jual ;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("id_hewan", IdhewanHolder));
                nameValuePairs.add(new BasicNameValuePair("harga_jual", HargajualHolder));
                nameValuePairs.add(new BasicNameValuePair("bobot_jual", BobotJualHolder));
                nameValuePairs.add(new BasicNameValuePair("tinggi_jual", TinggiJualHolder));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(ServerURL);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    HttpEntity httpEntity = httpResponse.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Inserted Successfully";
            }

            @Override
            protected void onPostExecute(String result) {

                super.onPostExecute(result);

                Toast.makeText(AddPenjualanActivity.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(id_hewan, harga_jual,bobot_jual,tinggi_jual);
    }

}
