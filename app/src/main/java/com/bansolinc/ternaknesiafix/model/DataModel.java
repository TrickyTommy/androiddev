package com.bansolinc.ternaknesiafix.model;

/**
 * Created by KUNCORO on 09/08/2017.
 */

public class DataModel {
    private String id_hewan, nama_kategori, harga_beli, bobot_beli, tinggi_hewan, jenis_kelamin, tanggal_beli;


    public DataModel() {
    }

    public DataModel(String id_hewan, String nama_kategori, String harga_beli, String bobot_beli, String tinggi_hewan, String jenis_kelamin, String tanggal_beli) {
        this.id_hewan = id_hewan;
        this.nama_kategori = nama_kategori;
        this.nama_kategori = nama_kategori;
        this.harga_beli = harga_beli;
        this.bobot_beli = bobot_beli;
        this.tinggi_hewan = tinggi_hewan;
        this.jenis_kelamin = jenis_kelamin;
        this.tanggal_beli = tanggal_beli;
    }

    public String getId_hewan() {
        return id_hewan;
    }

    public void setId_hewan(String id_hewan) {
        this.id_hewan = id_hewan;
    }

    public String getNama_kategori() {
        return nama_kategori;
    }

    public void setNama_kategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
    }

    public String getHarga_beli() {
        return harga_beli;
    }

    public void setHarga_beli(String harga_beli) {
        this.harga_beli = harga_beli;
    }

    public String getBobot_beli() {
        return bobot_beli;
    }

    public void setBobot_beli(String bobot_beli) {
        this.bobot_beli = bobot_beli;
    }

    public String getTinggi_hewan() {
        return tinggi_hewan;
    }

    public void setTinggi_hewan(String tinggi_hewan) {
        this.tinggi_hewan = tinggi_hewan;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getTanggal_beli() {
        return tanggal_beli;
    }

    public void setTanggal_beli(String tanggal_beli) {
        this.tanggal_beli = tanggal_beli;
    }
}
