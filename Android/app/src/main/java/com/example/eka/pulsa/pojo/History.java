package com.example.eka.pulsa.pojo;

import android.graphics.Bitmap;

import java.util.Date;

public class History {

    private String N_TRANSAKSI;
    private String C_ID;
    private int C_TYPE;
    private String V_PHONE;
    private Date D_TIME;
    private long N_SELL;
    private boolean C_STATUS;
    private String V_TOKEN_ID;

    public String getN_TRANSAKSI() {
        return N_TRANSAKSI;
    }

    public void setN_TRANSAKSI(String N_TRANSAKSI) {
        this.N_TRANSAKSI = N_TRANSAKSI;
    }

    public int getC_TYPE() {
        return C_TYPE;
    }

    public void setC_TYPE(int c_TYPE) {
        this.C_TYPE = c_TYPE;
    }

    public String getV_PHONE() {
        return V_PHONE;
    }

    public void setV_PHONE(String v_PHONE) {
        V_PHONE = v_PHONE;
    }

    public Date getD_TIME() {
        return D_TIME;
    }

    public void setD_TIME(Date d_TIME) {
        this.D_TIME = d_TIME;
    }

    public long getN_SELL() {
        return N_SELL;
    }

    public void setN_SELL(long n_SELL) {
        this.N_SELL = n_SELL;
    }

    public boolean isC_STATUS() {
        return C_STATUS;
    }

    public void setC_STATUS(boolean c_STATUS) {
        this.C_STATUS = c_STATUS;
    }

    public String getV_TOKEN_ID() {
        return V_TOKEN_ID;
    }

    public void setV_TOKEN_ID(String v_TOKEN_ID) {
        V_TOKEN_ID = v_TOKEN_ID;
    }

    public String getC_ID() {
        return C_ID;
    }

    public void setC_ID(String c_ID) {
        C_ID = c_ID;
    }
}
