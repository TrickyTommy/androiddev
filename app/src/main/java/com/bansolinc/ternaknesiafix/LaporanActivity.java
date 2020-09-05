package com.bansolinc.ternaknesiafix;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bansolinc.ternaknesiafix.adapter.LabaAdapter;
import com.bansolinc.ternaknesiafix.model.Laba;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.bansolinc.ternaknesiafix.BuildConfig.BASE_URL;

public class LaporanActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    Button b_pick, submitBtn;
    Spinner spinnerBulan;
    EditText edTahun;
    TextView tv_result_total_ekor_jual, tv_result_total_harga_jual, tv_result_total_ekor_beli, tv_result_total_harga_beli, tv_title;
    private LabaAdapter labaAdapter;
    private RecyclerView rvLaba;
    private ArrayList<Laba> listLaba = new ArrayList<>();

    int day,month,year;
    int dayFinal,monthFinal,yearFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);

        b_pick = (Button) findViewById(R.id.b_pick);
        tv_result_total_ekor_jual = findViewById(R.id.tv_result_total_jual);
        tv_result_total_harga_jual = findViewById(R.id.tv_result_jual);
        tv_result_total_ekor_beli = findViewById(R.id.tv_result_total_beli);
        tv_result_total_harga_beli = findViewById(R.id.tv_result_beli);
        rvLaba = findViewById(R.id.rv_laba_rugi);
        tv_title = findViewById(R.id.tv_title_laporan);
        submitBtn = findViewById(R.id.submitBtn);
        edTahun = findViewById(R.id.ed_tahun);
        spinnerBulan = findViewById(R.id.spinner_bulan);
        labaAdapter = new LabaAdapter(this);
        labaAdapter.setDummyData(listLaba);
        rvLaba.setLayoutManager(new LinearLayoutManager(this));

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int bulan = spinnerBulan.getSelectedItemPosition();
                String tanggal = "";
                if (bulan > 0 && !TextUtils.isEmpty(edTahun.getText().toString())){
                    if (bulan > 9)
                        tanggal = edTahun.getText()+"-"+bulan;
                    else
                        tanggal = edTahun.getText()+"-0"+bulan;

                    tv_title.setText("Laporan bulan "+spinnerBulan.getSelectedItem());

                    searchLaporan(tanggal, "jual");
                    searchLaporan(tanggal, "beli");
                    searchLabaLaporan(String.valueOf(bulan));
                }else {
                    Toast.makeText(LaporanActivity.this, "Harap mengisi semua inputan", Toast.LENGTH_SHORT).show();
                }

            }
        });

        b_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(LaporanActivity.this, LaporanActivity.this, year, month, day);
                datePickerDialog.show();

            }
        });



    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearFinal = i;
        monthFinal = i1+1;
        dayFinal = i2;
        String bulan = getMonth(monthFinal);
        String tanggal = "";
        if (monthFinal > 9)
            tanggal = yearFinal+"-"+monthFinal;
        else
            tanggal = yearFinal+"-0"+monthFinal;

        tv_title.setText("Laporan bulan "+bulan);

        searchLaporan(tanggal, "jual");
        searchLaporan(tanggal, "beli");
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

    }

    private String getMonth(int month){
        String value = "";
        switch(month){
            case 1:
                value = "Januari";
                break;
            case 2:
                value = "Februari";
                break;
            case 3:
                value = "Maret";
                break;
            case 4:
                value = "April";
                break;
            case 5:
                value = "Mei";
                break;
            case 6:
                value = "Juni";
                break;
            case 7:
                value = "Juli";
                break;
            case 8:
                value = "Agustus";
                break;
            case 9:
                value = "September";
                break;
            case 10:
                value = "Oktober";
                break;
            case 11:
                value = "November";
                break;
            case 12:
                value = "Desember";
                break;
            default:
                break;
        }

        return value;
    }

    private void searchLaporan(final String tanggal, final String type){
        String url;
        url = BASE_URL + "cari_datalaporan_"+type+".php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        JSONArray data;
                        try {
                            JSONObject usersObj = new JSONObject(response);
                            data = usersObj.getJSONArray("results");
                            JSONObject status = data.getJSONObject(0);

                            if (type.equals("jual")){
                                tv_result_total_ekor_jual.setText(": "+status.getString("jumlah")+" ekor");
                                String totalHarga = status.getString("total");
                                if (totalHarga.isEmpty())
                                    totalHarga = "0";
                                String formattedPrice = String.format("%,d", Long.parseLong(totalHarga));
                                tv_result_total_harga_jual.setText(": Rp."+formattedPrice+",-");
                            }else {
                                tv_result_total_ekor_beli.setText(": "+status.getString("jumlah")+" ekor");
                                String totalHarga = status.getString("total");
                                if (totalHarga.isEmpty())
                                    totalHarga = "0";
                                String formattedPrice = String.format("%,d", Long.parseLong(totalHarga));
                                tv_result_total_harga_beli.setText(": Rp."+formattedPrice+",-");
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LaporanActivity.this, "Gagal mencari data, periksa koneksi internet anda", Toast.LENGTH_SHORT).show();
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("keyword", tanggal);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(LaporanActivity.this);
        requestQueue.add(postRequest);
    }

    private void searchLabaLaporan(final String bulan){
        listLaba.clear();
        labaAdapter.notifyDataSetChanged();
        String url;
        url = BASE_URL + "tampillabarugi.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        JSONArray data;
                        try {
                            JSONObject usersObj = new JSONObject(response);
                            data = usersObj.getJSONArray("results");
                            for (int i=0;i<data.length();i++){
                                JSONObject objLaba = data.getJSONObject(i);

                                Laba laba = new Laba(
                                        objLaba.getString("id_hewan"),
                                        objLaba.getString("nama_kategori"),
                                        objLaba.getString("harga_jual"),
                                        objLaba.getString("harga_beli"),
                                        objLaba.getString("tanggal_penjualan"),
                                        objLaba.getString("tanggal_beli")
                                );

                                listLaba.add(laba);
                            }

                            rvLaba.setAdapter(labaAdapter);
                            labaAdapter.setDummyData(listLaba);
                            labaAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LaporanActivity.this, "Gagal mencari data, periksa koneksi internet anda", Toast.LENGTH_SHORT).show();
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("keyword", bulan);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(LaporanActivity.this);
        requestQueue.add(postRequest);
    }

}
