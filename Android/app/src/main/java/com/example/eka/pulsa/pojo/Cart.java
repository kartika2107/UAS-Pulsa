package com.example.eka.pulsa.pojo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.eka.pulsa.R;

public class Cart {

    private String C_ID;
    private String V_PHONE;
    private int C_TYPE;
    private String namaProduk;
    private long N_SELL;

    private Bitmap imageProduk;


    public String getC_ID() {
        return C_ID;
    }

    public void setC_ID(String c_ID) {
        C_ID = c_ID;
    }

    public int getC_TYPE() {
        return C_TYPE;
    }

    public void setC_TYPE(int c_TYPE) {
        C_TYPE = c_TYPE;
    }

    public Bitmap getImageProduk() {
        return imageProduk;
    }

    public void setImageProduk(Bitmap imageProduk) {
        this.imageProduk = imageProduk;
    }

    public String getV_PHONE() {
        return V_PHONE;
    }

    public void setV_PHONE(String v_PHONE) {
        this.V_PHONE = v_PHONE;
    }

    public long getN_SELL() {
        return N_SELL;
    }

    public void setN_SELL(long n_SELL) {
        this.N_SELL = n_SELL;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }
}
