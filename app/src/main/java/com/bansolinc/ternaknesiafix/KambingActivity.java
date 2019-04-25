package com.bansolinc.ternaknesiafix;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;

public class KambingActivity extends AppCompatActivity {

    ListView listView;
    String urladdress="http://trickyserver.ddns.net/connectandroid/includes/tampilkambing.php";
    String[] id_hewan;
    String[] nama_kategori;
    String[] harga_beli;
    String[] bobot_beli;
    String[] tinggi_hewan;
    String[] jenis_kelamin;
    String[] tanggal_beli;
    //String[] jumlah;
    BufferedInputStream is;
    String line=null;
    String result=null;
    private final OkHttpClient client = new OkHttpClient();

    SwipeRefreshLayout swipe_refresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kambing);
        swipe_refresh   = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startActivity(new Intent(KambingActivity.this, KambingActivity.class));
                finish();


            }
        });

        listView=(ListView)findViewById(R.id.list_kambing);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        collectData();
        swipe_refresh.setRefreshing(false);
        CustomListView customListView=new CustomListView(this,id_hewan,nama_kategori,harga_beli,bobot_beli,tinggi_hewan,jenis_kelamin,tanggal_beli);
        listView.setAdapter(customListView);
    }
    private void collectData()
    {
//Connection
        try{

            URL url=new URL(urladdress);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            is=new BufferedInputStream(con.getInputStream());

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        //content
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            StringBuilder sb=new StringBuilder();
            while ((line=br.readLine())!=null){
                sb.append(line+"\n");
            }
            is.close();
            result=sb.toString();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();

        }

//JSON
        try{
            JSONArray ja=new JSONArray(result);
            JSONObject jo=null;
            id_hewan=new String[ja.length()];
            nama_kategori=new String[ja.length()];
            harga_beli=new String[ja.length()];
            bobot_beli=new String[ja.length()];
            tinggi_hewan=new String[ja.length()];
            jenis_kelamin=new String[ja.length()];
            tanggal_beli=new String[ja.length()];

            for(int i=0;i<=ja.length();i++){
                jo=ja.getJSONObject(i);
                id_hewan[i]=jo.getString("id_hewan");
                nama_kategori[i]=jo.getString("nama_kategori");
                harga_beli[i]=jo.getString("harga_beli");
                bobot_beli[i]=jo.getString("bobot_beli");
                tinggi_hewan[i]=jo.getString("tinggi_hewan");
                jenis_kelamin[i]=jo.getString("jenis_kelamin");
                tanggal_beli[i]=jo.getString("tanggal_beli");
            }
//            swipe_refresh.setRefreshing(false);
        }
        catch (Exception ex)
        {

            ex.printStackTrace();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menukambing, menu);
        return true;
    }


}
