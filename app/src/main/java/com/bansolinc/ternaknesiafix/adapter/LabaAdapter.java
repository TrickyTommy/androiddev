package com.bansolinc.ternaknesiafix.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bansolinc.ternaknesiafix.R;
import com.bansolinc.ternaknesiafix.model.Laba;

import java.util.ArrayList;

public class LabaAdapter extends RecyclerView.Adapter<LabaAdapter.LabaViewHolder> {

    private ArrayList<Laba> dummyData;
    private Context context;

    public LabaAdapter(Context context) {
        this.context = context;
    }

    public void setDummyData(ArrayList<Laba> dummyData){
        this.dummyData = dummyData;
        notifyDataSetChanged();
    }

    @Override
    public LabaAdapter.LabaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_laba_rugi, viewGroup, false);
        return new LabaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LabaAdapter.LabaViewHolder holder, final int i) {
        String hargaJual="";
        String hargaBeli = "";
        holder.idItemTv.setText(dummyData.get(i).getId());
        if (!dummyData.get(i).getHargaJual().isEmpty()) {
            hargaJual = String.format("%,d", Long.parseLong(dummyData.get(i).getHargaJual()));
        }
        else{
            hargaJual = "";
        }

        if (!dummyData.get(i).getHargaBeli().isEmpty()) {
            hargaBeli = String.format("%,d", Long.parseLong(dummyData.get(i).getHargaBeli()));
        }
        else{
            hargaBeli = "";
        }

        holder.kategoriItemTv.setText(": "+dummyData.get(i).getKategori());
        holder.hargaJualTv.setText(": Rp."+hargaJual+",-");
        holder.hargaBeliTv.setText(": Rp."+hargaBeli+",-");
        holder.tglJualTv.setText(": "+dummyData.get(i).getTglJual());
        holder.tglBeliTv.setText(": "+dummyData.get(i).getTglBeli());
        if (!dummyData.get(i).getHargaJual().isEmpty() && !dummyData.get(i).getHargaBeli().isEmpty()){
            int nilai = Integer.parseInt(dummyData.get(i).getHargaJual()) - Integer.parseInt(dummyData.get(i).getHargaBeli());
            if (nilai >= 0) {
                holder.keteranganTv.setText("Laba");
            }
            else{
                holder.keteranganTv.setText("Rugi");
                nilai *= -1;
            }

            Long nilaiLong = Long.valueOf(nilai);
            String formattedNilai = String.format("%,d", nilaiLong);

            holder.nilaiTv.setText(": Rp."+formattedNilai+",-");
        }

    }

    @Override
    public int getItemCount() {
        return dummyData.size();
    }

    public class LabaViewHolder extends RecyclerView.ViewHolder {
        TextView idItemTv, kategoriItemTv, hargaJualTv, hargaBeliTv, tglJualTv, tglBeliTv, keteranganTv, nilaiTv;

        public LabaViewHolder(View itemView) {
            super(itemView);
            idItemTv = itemView.findViewById(R.id.tv_id_ternak);
            kategoriItemTv = itemView.findViewById(R.id.tv_kategori_ternak);
            hargaJualTv = itemView.findViewById(R.id.tv_har_jual_ternak);
            hargaBeliTv = itemView.findViewById(R.id.tv_har_bel_ternak);
            tglJualTv = itemView.findViewById(R.id.tv_tgl_jual_ternak);
            tglBeliTv = itemView.findViewById(R.id.tv_tgl_bel_ternak);
            keteranganTv = itemView.findViewById(R.id.tv_detail_txt);
            nilaiTv = itemView.findViewById(R.id.tv_value_ternak);
        }
    }
}
