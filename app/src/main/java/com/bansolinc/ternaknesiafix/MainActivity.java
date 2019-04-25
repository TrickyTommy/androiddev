package com.bansolinc.ternaknesiafix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Thread
        Thread timerThreaad = new Thread(){
            public void run(){
                try {
                    sleep(2000);//1000 sama dengan 1 detik
                }catch (InterruptedException e){

                    e.printStackTrace();

                }finally {
                    //intent
                    Intent intent = new Intent (MainActivity.this, Menuactivity.class);
                    startActivity(intent);
                    //menutup activity
                    finish();



                }
            }
        };

        timerThreaad.start();
    }
}
