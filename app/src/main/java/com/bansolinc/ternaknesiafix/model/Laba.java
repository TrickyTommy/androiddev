package com.bansolinc.ternaknesiafix.model;

public class Laba {
    String id, kategori, hargaJual, hargaBeli, tglJual, tglBeli;

    public Laba(String id, String kategori, String hargaJual, String hargaBeli, String tglJual, String tglBeli) {
        this.id = id;
        this.kategori = kategori;
        this.hargaJual = hargaJual;
        this.hargaBeli = hargaBeli;
        this.tglJual = tglJual;
        this.tglBeli = tglBeli;
    }

    public String getId() {
        return id;
    }

    public String getKategori() {
        return kategori;
    }

    public String getHargaJual() {
        return hargaJual;
    }

    public String getHargaBeli() {
        return hargaBeli;
    }

    public String getTglJual() {
        return tglJual;
    }

    public String getTglBeli() {
        return tglBeli;
    }

}
