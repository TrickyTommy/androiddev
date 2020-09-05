package com.bansolinc.ternaknesiafix;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.bansolinc.ternaknesiafix.adapterjual.AdapterJual;
import com.bansolinc.ternaknesiafix.adapterjual.AdapterJualBeli;
import com.bansolinc.ternaknesiafix.app.AppController;
import com.bansolinc.ternaknesiafix.model.DataModel;
import com.bansolinc.ternaknesiafix.modeljual.DataModelJual;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bansolinc.ternaknesiafix.BuildConfig.BASE_URL;

public class LabaRugiActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
        SearchView.OnQueryTextListener {

    ProgressDialog pDialog;
    List<DataModel> listData = new ArrayList<DataModel>();
    AdapterJualBeli adapter;
    SwipeRefreshLayout swipe;
    ListView list_view;

    /* 10.0.2.2 adalah IP Address localhost EMULATOR ANDROID STUDIO,
    Ganti IP Address tersebut dengan IP Laptop Apabila di RUN di HP. HP dan Laptop harus 1 jaringan */
//    public static final String url_data = "http://192.168.43.153/connectandroid/includes/tampilpenjualan.php";
//    public static final String url_cari = "http://192.168.43.153/connectandroid/includes/cari_data.php";
    public static final String url_data = BASE_URL+"tampilpenjualan.php/";
    public static final String url_cari = BASE_URL+"cari_datalabarugi.php/";
    // public static final String url_data = "http://trickyserver.ddns.net/connectandroid/includes/tampilpenjualan.php/";
    // public static final String url_cari = "http://trickyserver.ddns.net/connectandroid/includes/cari_data_penjualan.php/";

    private static final String TAG = LabaRugiActivity.class.getSimpleName();


    public static final String TAG_ID = "id_hewan";
    public static final String TAG_NAMA = "nama_kategori";
    public static final String TAG_harga_beli = "harga_beli";
    public static final String TAG_harga_jual = "harga_jual";
    public static final String TAG_bobot_beli = "bobot_beli";
    public static final String TAG_bobot_jual = "bobot_jual";
    public static final String TAG_tinggi_hewan = "tinggi_hewan";
    public static final String TAG_tinggi_hewan_jual = "tinggi_jual";
    public static final String TAG_jenis_kelamin = "jenis_kelamin";
    public static final String TAG_tanggal_beli = "tanggal_beli";
    public static final String TAG_tanggal_jual = "tanggal_penjualan";
    public static final String TAG_status_jual = "status_jual";
    public static final String TAG_RESULTS = "results";
    public static final String TAG_MESSAGE = "message";
    public static final String TAG_VALUE = "value";

    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laba_rugi);

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        list_view = (ListView) findViewById(R.id.list_viewlabarugi);

        adapter = new AdapterJualBeli(LabaRugiActivity.this, listData);
        list_view.setAdapter(adapter);

        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           callData();
                       }
                   }
        );

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.tambahpembelian);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(LabaRugiActivity.this, AddPenjualanActivity.class));
            }
        });

    }

    private void callData() {
        listData.clear();
        adapter.notifyDataSetChanged();
        swipe.setRefreshing(true);

        // Creating volley request obj
        JsonArrayRequest jArr = new JsonArrayRequest(url_data, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.e(TAG, response.toString());

                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        DataModel item = new DataModel();

                        item.setId_hewan(obj.getString(TAG_ID));
                        item.setNama_kategori(obj.getString(TAG_NAMA));
                        item.setHarga_beli(obj.getString(TAG_harga_beli));
                        Log.d("fungsi", item.toString());
                        item.setHarga_jual(obj.getString(TAG_harga_jual));
//                        item.setBobot_beli(obj.getString(TAG_bobot_beli));
//                        item.setBobot_jual(obj.getString(TAG_bobot_jual));
//                        item.setTinggi_hewan(obj.getString(TAG_tinggi_hewan));
//                        item.setTinggi_hewan_jual(obj.getString(TAG_tinggi_hewan_jual));
//                        item.setJenis_kelamin(obj.getString(TAG_jenis_kelamin));
//                        item.setTanggal_beli(obj.getString(TAG_tanggal_beli));
                        item.setTanggal_penjualan(obj.getString(TAG_tanggal_jual));
//                        item.setStatus_jual(obj.getString(TAG_status_jual));

                        listData.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                // notifying list adapter about data changes
                // so that it renders the list view with updated data
                adapter.notifyDataSetChanged();
                swipe.setRefreshing(false);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(LabaRugiActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                swipe.setRefreshing(false);
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jArr);
    }

    @Override
    public void onRefresh() {
        callData();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        cariData(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main_menulabarugi, menu);
        final MenuItem item = menu.findItem(R.id.action_search_labarugi);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint(getString(R.string.type_name_penjualan));
        searchView.setIconified(true);

        searchView.setOnQueryTextListener(this);
        return true;
    }

    private void cariData(final String keyword) {
        pDialog = new ProgressDialog(LabaRugiActivity.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST, url_cari, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("Response: ", response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);

                    int value = jObj.getInt(TAG_VALUE);

                    if (value == 1) {
                        listData.clear();
                        adapter.notifyDataSetChanged();

                        String getObject = jObj.getString(TAG_RESULTS);
                        JSONArray jsonArray = new JSONArray(getObject);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);

                            DataModel data = new DataModel();

//                            data.setId_hewan(obj.getString(TAG_ID));
//                            data.setNama_kategori(obj.getString(TAG_NAMA));
//                            data.setHarga_beli(obj.getString(TAG_harga_beli));
//                            data.setBobot_beli(obj.getString(TAG_bobot_beli));
//                            data.setTinggi_hewan(obj.getString(TAG_tinggi_hewan));
//                            data.setJenis_kelamin(obj.getString(TAG_jenis_kelamin));
//                            data.setTanggal_beli(obj.getString(TAG_tanggal_beli));
//                            data.setStatus_jual(obj.getString(TAG_status_jual));
                            //
                            data.setId_hewan(obj.getString(TAG_ID));
                            data.setNama_kategori(obj.getString(TAG_NAMA));
                            data.setHarga_beli(obj.getString(TAG_harga_beli));
                            data.setHarga_jual(obj.getString(TAG_harga_jual));
//                            data.setBobot_beli(obj.getString(TAG_bobot_beli));
//                            data.setBobot_jual(obj.getString(TAG_bobot_jual));
//                            data.setTinggi_hewan(obj.getString(TAG_tinggi_hewan));
//                            data.setTinggi_hewan_jual(obj.getString(TAG_tinggi_hewan_jual));
//                            data.setJenis_kelamin(obj.getString(TAG_jenis_kelamin));
//                            data.setTanggal_beli(obj.getString(TAG_tanggal_beli));
                            data.setTanggal_penjualan(obj.getString(TAG_tanggal_jual));
//                            data.setStatus_jual(obj.getString(TAG_status_jual));

                            listData.add(data);
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

                adapter.notifyDataSetChanged();
                pDialog.dismiss();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("keyword", keyword);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
        pDialog.dismiss();
    }

}
