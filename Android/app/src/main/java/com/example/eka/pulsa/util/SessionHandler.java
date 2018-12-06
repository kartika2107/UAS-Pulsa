package com.example.eka.pulsa.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import java.util.Set;

public class SessionHandler {

    public static final String KEY_CUSERNAME = "KEY_CUSERNAME";
    public static final String KEY_CPASSWORD = "KEY_CPASSWORD";
    public static final String KEY_CNAME = "KEY_CNAME";
    public static final String KEY_CADDRESS = "KEY_CADDRESS";
    public static final String KEY_CMAIL = "KEY_CMAIL";
    public static final String KEY_CPHONE = "KEY_CPHONE";
    public static final String KEY_NSALDO = "KEY_NSALDO";
    public static final String KEY_CBACKGROUNDIMAGE = "KEY_CBACKGROUNDIMAGE";
    public static final String KEY_CPROFILEIMAGE = "KEY_CPROFILEIMAGE";



    public static final String KEY_CSELLERID = "KEY_CSELLERID";
    public static final String KEY_CSELLERNAME = "KEY_CSELLERNAME";
    public static final String KEY_CSELLERALIAS = "KEY_CSELLERALIAS";
    public static final String KEY_CSELLERADDR = "KEY_CSELLERADDR";



    private final SharedPreferences preferences;

    public SessionHandler(Context context) {
        this(context, context.getPackageName());
    }

    public SessionHandler(Context context, String name) {
        preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public void logout() {
        preferences.edit().clear().apply();
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(key, value);
        edit.apply();
    }

    public void put(String key, boolean value) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    public void put(String key, int value) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    public void put(String key, float value) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putFloat(key, value);
        edit.apply();
    }

    public void put(String key, Set<String> value) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putStringSet(key, value);
        edit.apply();
    }

    public void put(String key, long value) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putLong(key, value);
        edit.apply();
    }

    public boolean get(String key, boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    public String getString(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    public int get(String key, int defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    public float get(String key, float defaultValue) {
        return preferences.getFloat(key, defaultValue);
    }

    public long get(String key, long defaultValue) {
        return preferences.getLong(key, defaultValue);
    }

    public Set<String> get(String key, Set<String> defaultValue) {
        return preferences.getStringSet(key, defaultValue);
    }

    public void remove(String key) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.remove(key);
        edit.apply();
    }


    public boolean isLogin() {
        return getString("C_USERNAME", null) != null && getString("C_PASSWORD", null) != null;
    }
}
