package com.example.eka.pulsa.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.eka.pulsa.R;

public class SplashActivity extends Activity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textView = findViewById(R.id.tv_splash);
        textView.post(runnable);

        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
        thread.start();

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (textView != null) {
                String text = textView.getText().toString();

                if (text.length()<15) {
                    text += ".";
                    textView.setText(text);
                } else
                    textView.setText("Mohon Tunggu");
                textView.postDelayed(runnable, 100);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        textView = null;
    }
}
