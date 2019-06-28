package com.bansolinc.ternaknesiafix.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bansolinc.ternaknesiafix.R;
import com.bansolinc.ternaknesiafix.model.DataModel;

import java.util.List;

/**
 * Created by KUNCORO on 09/08/2017.
 */

public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataModel> item;

    public Adapter(Activity activity, List<DataModel> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int location) {
        return item.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_item_pembelian, null);

        TextView txt_nama = (TextView) convertView.findViewById(R.id.viewnama_kategori);
        TextView txt_hargaBeli = (TextView) convertView.findViewById(R.id.viewharga_beli);
        TextView txt_beratHewan = (TextView) convertView.findViewById(R.id.viewbobot_beli);
        TextView txt_tinggiHewan = (TextView) convertView.findViewById(R.id.viewtinggi_hewan);
        TextView txt_jenisKelamin = (TextView) convertView.findViewById(R.id.viewjenis_kelamin);
        TextView txt_tanggalPembelian = (TextView) convertView.findViewById(R.id.viewtanggal_beli);


        txt_nama.setText(item.get(position).getNama_kategori());
        txt_hargaBeli.setText(item.get(position).getHarga_beli());
        txt_beratHewan.setText(item.get(position).getBobot_beli());
        txt_tinggiHewan.setText(item.get(position).getTinggi_hewan());
        txt_jenisKelamin.setText(item.get(position).getJenis_kelamin());
        txt_tanggalPembelian.setText(item.get(position).getTanggal_beli());


        return convertView;
    }
}