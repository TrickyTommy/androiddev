package com.bansolinc.ternaknesiafix;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

/**
 * Created by jaiso on 13-02-2018.
 */

public class CustomListView extends ArrayAdapter<String> {

    private String[] id_hewan;
    private String[] nama_kategori;
    private String[] harga_beli;
    private String[] bobot_beli;
    private String[] tinggi_hewan;
    private String[] jenis_kelamin;
    private String[] tanggal_beli;
    private String[] status_jual;
   // private String[] jumlah;
    private Activity context;
   // Bitmap bitmap;

    public CustomListView(Activity context, String[] id_hewan,String[] nama_kategori, String[] harga_beli, String[] bobot_beli, String[] tinggi_hewan, String[] jenis_kelamin, String[] tanggal_beli, String[] status_jual) {
        super(context, R.layout.layout,id_hewan);
        this.context=context;
        this.nama_kategori=nama_kategori;
        this.id_hewan=id_hewan;
        this.harga_beli=harga_beli;
        this.bobot_beli =bobot_beli;
        this.tinggi_hewan=tinggi_hewan;
        this.jenis_kelamin =jenis_kelamin;
        this.tanggal_beli=tanggal_beli;
        this.status_jual=status_jual;
       // this.jumlah=jumlah;
    }

    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r=convertView;
        ViewHolder viewHolder=null;
        if(r==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.layout,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)r.getTag();

        }

        viewHolder.viewnama_kategori.setText(nama_kategori[position]);
        viewHolder.viewharga_beli.setText(harga_beli[position]);
        viewHolder.viewbobot_beli.setText(bobot_beli[position]);
        viewHolder.viewtinggi_hewan.setText(tinggi_hewan[position]);
        viewHolder.viewjenis_kelamin.setText(jenis_kelamin[position]);
        viewHolder.viewtanggal_beli.setText(tanggal_beli[position]);
        viewHolder.viewstatus_jual.setText(status_jual[position]);
       // viewHolder.viewjumlah.setText(jumlah[position]);

        return r;
    }

    class ViewHolder{

        TextView viewnama_kategori;
        TextView viewharga_beli;
        TextView viewbobot_beli;
        TextView viewtinggi_hewan;
        TextView viewjenis_kelamin;
        TextView viewtanggal_beli;
        TextView viewstatus_jual;
       // TextView viewjumlah;


        ViewHolder(View v){
            viewnama_kategori=(TextView)v.findViewById(R.id.viewnama_kategori);
            viewharga_beli=(TextView)v.findViewById(R.id.viewharga_beli);
            viewbobot_beli=(TextView)v.findViewById(R.id.viewbobot_beli);
            viewtinggi_hewan=(TextView)v.findViewById(R.id.viewtinggi_hewan);
            viewjenis_kelamin=(TextView)v.findViewById(R.id.viewjenis_kelamin);
            viewtanggal_beli=(TextView)v.findViewById(R.id.viewtanggal_beli);
            viewstatus_jual=(TextView)v.findViewById(R.id.viewStatusJual);
           // viewtanggal_beli=(TextView)v.findViewById(R.id.view_jumlah);
        }

    }

//    public class GetImageFromURL extends AsyncTask<String,Void,Bitmap>
//    {
//
//        ImageView imgView;
//        public GetImageFromURL(ImageView imgv)
//        {
//            this.imgView=imgv;
//        }
//        @Override
//        protected Bitmap doInBackground(String... url) {
//            String urldisplay=url[0];
//            bitmap=null;
//
//            try{
//
//                InputStream ist=new java.net.URL(urldisplay).openStream();
//                bitmap= BitmapFactory.decodeStream(ist);
//            }
//            catch (Exception ex)
//            {
//                ex.printStackTrace();
//            }
//
//            return bitmap;
//        }

//        @Override
//        protected void onPostExecute(Bitmap bitmap){
//
//            super.onPostExecute(bitmap);
//            imgView.setImageBitmap(bitmap);
//        }
  //}


}
