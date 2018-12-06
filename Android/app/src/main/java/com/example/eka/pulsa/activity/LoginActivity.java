package com.example.eka.pulsa.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.eka.pulsa.R;
import com.example.eka.pulsa.util.ConstantNetwork;
import com.example.eka.pulsa.util.SessionHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    public EditText email, password;
    SessionHandler handler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        handler =  new SessionHandler(this);

        if (handler.isLogin())
            startActivity(new Intent(this, MainActivity.class));
    }

    public void btn_Login (View view){
        final String mail = email.getText().toString();
        final String pass = password.getText().toString();
        AndroidNetworking.post(ConstantNetwork.LOGIN)
                .addBodyParameter("C_USERNAME", mail)
                .addBodyParameter("V_PASSWORD", pass)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("success")) {
                        Intent btn_Login = new Intent(LoginActivity.this, MainActivity.class);
                        handler.putString("C_USERNAME", mail);
                        handler.putString("C_PASSWORD", pass);
                        finish();
                        startActivity(btn_Login);

                    } else
                        Toast.makeText(getBaseContext(), "Username atau Password salah.", Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(getBaseContext(), "Gagal Login", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void tv_Daftar (View view){
        Intent tv_Daftar = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(tv_Daftar);
    }
}
