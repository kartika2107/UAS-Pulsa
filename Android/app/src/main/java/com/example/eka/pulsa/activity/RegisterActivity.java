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

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private EditText username, password, fullname, nohp, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        fullname = findViewById(R.id.et_fullname);
        nohp = findViewById(R.id.et_phone);
        email = findViewById(R.id.et_email);

    }

    public void btn_Awal_Login (View view){
        AndroidNetworking.post(ConstantNetwork.REGISTER)
                .addBodyParameter("C_USERNAME", username.getText().toString())
                .addBodyParameter("V_PASSWORD", password.getText().toString())
                .addBodyParameter("V_FULLNAME", fullname.getText().toString())
                .addBodyParameter("V_NOHP", nohp.getText().toString())
                .addBodyParameter("V_MAIL", email.getText().toString())
                .build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("success")) {
                        finish();
                    } else
                        Toast.makeText(RegisterActivity.this, "Registrasi Gagal, Silahkan coba lagi.", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(RegisterActivity.this, "Registrasi Gagal, Silahkan coba lagi.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
