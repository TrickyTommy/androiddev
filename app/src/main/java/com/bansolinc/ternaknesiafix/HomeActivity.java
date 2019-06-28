package com.bansolinc.ternaknesiafix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    CardView ternaksapi, ternakkambing, ternakdomba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle("Peternakan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ternaksapi = (CardView) findViewById(R.id.ternaksapi);
        ternakkambing = (CardView) findViewById(R.id.ternakkambing);
        ternakdomba = (CardView) findViewById(R.id.ternakdomba);

        ternaksapi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SapiActivity.class));
//                finish();

            }
        });
        ternakkambing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, KambingActivity.class));
//                finish();

            }
        });
        ternakdomba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, DombaActivity.class));
//                finish();

            }
        });



    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
