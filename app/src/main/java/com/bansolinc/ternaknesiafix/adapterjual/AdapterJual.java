package com.bansolinc.ternaknesiafix.adapterjual;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bansolinc.ternaknesiafix.R;
import com.bansolinc.ternaknesiafix.model.DataModel;
import com.bansolinc.ternaknesiafix.modeljual.DataModelJual;

import java.util.List;

/**
 * Created by KUNCORO on 09/08/2017.
 */

public class AdapterJual extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataModel> item;

    public AdapterJual(Activity activity, List<DataModel> item) {
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
            convertView = inflater.inflate(R.layout.list_item_penjualan, null);

        TextView txt_id = (TextView) convertView.findViewById(R.id.text_view_id);
        TextView txt_nama = (TextView) convertView.findViewById(R.id.viewnama_kategorijual);
        TextView txt_hargaBeli = (TextView) convertView.findViewById(R.id.viewharga_belijual);
        TextView txt_hargaJual = (TextView) convertView.findViewById(R.id.viewHarga_jual);
        TextView txt_beratHewan = (TextView) convertView.findViewById(R.id.viewbobot_belijual);
        TextView txt_beratJualHewan = (TextView) convertView.findViewById(R.id.viewBerat_hewan_jual);
        TextView txt_tinggiHewan = (TextView) convertView.findViewById(R.id.viewtinggi_hewanjual);
        TextView txt_tinggiHewanJual = (TextView) convertView.findViewById(R.id.viewTinggi_jual);
        TextView txt_tanggalPembelian = (TextView) convertView.findViewById(R.id.viewtanggal_belijual);
        TextView txt_tanggalPenjualan = (TextView) convertView.findViewById(R.id.viewTanggal_hewan_penjualan);
        TextView txt_jenisKelamin = (TextView) convertView.findViewById(R.id.viewjenis_kelaminjual);
        TextView txt_statusJual = (TextView) convertView.findViewById(R.id.viewstatus_jualjual);

        txt_id.setText(item.get(position).getId_hewan());
        txt_nama.setText(item.get(position).getNama_kategori());
        txt_hargaBeli.setText(item.get(position).getHarga_beli());
        txt_hargaJual.setText(item.get(position).getHarga_jual());
        txt_beratHewan.setText(item.get(position).getBobot_beli());
        txt_beratJualHewan.setText(item.get(position).getBobot_jual());
        txt_tinggiHewan.setText(item.get(position).getTinggi_hewan());
        txt_tinggiHewanJual.setText(item.get(position).getTinggi_hewan_jual());
        txt_jenisKelamin.setText(item.get(position).getJenis_kelamin());
        txt_tanggalPembelian.setText(item.get(position).getTanggal_beli());
        txt_tanggalPenjualan.setText(item.get(position).getTanggal_penjualan());
        txt_statusJual.setText(item.get(position).getStatus_jual());


        return convertView;
    }
}