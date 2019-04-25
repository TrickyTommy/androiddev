package com.bansolinc.ternaknesiafix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Menuactivity extends AppCompatActivity {


    CardView ternakcard, penjualanid, pembeliancard, laporanid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuactivity);

        ternakcard = (CardView) findViewById(R.id.ternakcard);
//        penjualanid = (CardView)findViewById(R.id.penjualanid);
        pembeliancard = (CardView)findViewById(R.id.pembeliancard);
//        laporanid = (CardView)findViewById(R.id.laporanid);

        ternakcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menuactivity.this, HomeActivity.class));
//                finish();

            }
        });
        pembeliancard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pembelian) {
                startActivity(new Intent(Menuactivity.this, PembelianActivity.class));
            }
        });


    }
}
