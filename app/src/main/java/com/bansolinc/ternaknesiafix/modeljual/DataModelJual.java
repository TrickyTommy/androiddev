package com.bansolinc.ternaknesiafix.modeljual;

public class DataModelJual {
    private String id_hewan, nama_kategori, harga_beli,harga_jual, bobot_beli,bobot_jual, tinggi_hewan,tinggi_hewan_jual, jenis_kelamin, tanggal_beli,tanggal_penjualan,status_jual;

    public DataModelJual(){

    }

    public DataModelJual(String id_hewan, String nama_kategori, String harga_beli, String harga_jual, String bobot_beli, String bobot_jual, String tinggi_hewan, String tinggi_hewan_jual, String jenis_kelamin, String tanggal_beli, String tanggal_penjualan, String status_jual) {
        this.id_hewan = id_hewan;
        this.nama_kategori = nama_kategori;
        this.harga_beli = harga_beli;
        this.harga_jual = harga_jual;
        this.bobot_beli = bobot_beli;
        this.bobot_jual = bobot_jual;
        this.tinggi_hewan = tinggi_hewan;
        this.tinggi_hewan_jual = tinggi_hewan_jual;
        this.jenis_kelamin = jenis_kelamin;
        this.tanggal_beli = tanggal_beli;
        this.tanggal_penjualan = tanggal_penjualan;
        this.status_jual = status_jual;
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

    public String getHarga_jual() {
        return harga_jual;
    }

    public void setHarga_jual(String harga_jual) {
        this.harga_jual = harga_jual;
    }

    public String getBobot_beli() {
        return bobot_beli;
    }

    public void setBobot_beli(String bobot_beli) {
        this.bobot_beli = bobot_beli;
    }

    public String getBobot_jual() {
        return bobot_jual;
    }

    public void setBobot_jual(String bobot_jual) {
        this.bobot_jual = bobot_jual;
    }

    public String getTinggi_hewan() {
        return tinggi_hewan;
    }

    public void setTinggi_hewan(String tinggi_hewan) {
        this.tinggi_hewan = tinggi_hewan;
    }

    public String getTinggi_hewan_jual() {
        return tinggi_hewan_jual;
    }

    public void setTinggi_hewan_jual(String tinggi_hewan_jual) {
        this.tinggi_hewan_jual = tinggi_hewan_jual;
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

    public String getTanggal_penjualan() {
        return tanggal_penjualan;
    }

    public void setTanggal_penjualan(String tanggal_penjualan) {
        this.tanggal_penjualan = tanggal_penjualan;
    }

    public String getStatus_jual() {
        return status_jual;
    }

    public void setStatus_jual(String status_jual) {
        this.status_jual = status_jual;
    }
}
